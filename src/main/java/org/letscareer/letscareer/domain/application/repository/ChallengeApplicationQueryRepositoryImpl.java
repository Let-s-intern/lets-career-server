package org.letscareer.letscareer.domain.application.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationWithOptionsVo;
import org.letscareer.letscareer.domain.application.vo.ReviewNotificationUserVo;
import org.letscareer.letscareer.domain.application.vo.UserChallengeApplicationVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static org.letscareer.letscareer.domain.application.entity.QChallengeApplication.challengeApplication;
import static org.letscareer.letscareer.domain.attendance.entity.QAttendance.attendance;
import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.challengeoption.entity.QChallengeOption.challengeOption;
import static org.letscareer.letscareer.domain.challengeoption.entity.QChallengePriceOption.challengePriceOption;
import static org.letscareer.letscareer.domain.coupon.entity.QCoupon.coupon;
import static org.letscareer.letscareer.domain.payment.entity.QPayment.payment;
import static org.letscareer.letscareer.domain.price.entity.QChallengePrice.challengePrice;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;
import static org.letscareer.letscareer.domain.user.repository.UserQueryRepositoryImpl.activeEmail;

@RequiredArgsConstructor
public class ChallengeApplicationQueryRepositoryImpl implements ChallengeApplicationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ChallengeApplication> findChallengeApplicationByChallengeIdAndUserId(Long challengeId, Long userId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(challengeApplication)
                .leftJoin(challengeApplication.challenge, challenge)
                .leftJoin(challengeApplication.user, user)
                .leftJoin(challengeApplication.payment, payment)
                .where(
                        eqChallengeId(challengeId),
                        eqUserId(userId),
                        eqIsCanceled(false)
                )
                .fetchFirst());
    }

    @Override
    public List<AdminChallengeApplicationWithOptionsVo> findAdminChallengeApplicationVo(Long challengeId, Boolean isCanceled) {
        List<Tuple> tuples = queryFactory
                .select(
                        Projections.constructor(AdminChallengeApplicationVo.class,
                                challengeApplication.id,
                                payment.id,
                                user.name,
                                user.contactEmail,
                                user.phoneNum,
                                user.university,
                                user.grade,
                                user.major,
                                coupon.name,
                                coupon.discount,
                                payment.finalPrice,
                                payment.programPrice,
                                payment.programDiscount,
                                challengePrice.refund,
                                payment.orderId,
                                challengeApplication._super.isCanceled,
                                user.wishJob,
                                user.wishCompany,
                                user.inflowPath,
                                challengeApplication.createDate,
                                user.accountType,
                                user.accountNum,
                                payment.challengePricePlanType
                        ),
                        challengeOption.code
                )
                .from(challengeApplication)
                .leftJoin(challengeApplication.challenge, challenge)
                .leftJoin(challengeApplication.user, user)
                .leftJoin(challengeApplication.payment, payment)
                .leftJoin(payment.coupon, coupon)

                // challengeId + pricePlanType 로 challengePrice 연결
                .leftJoin(challengePrice)
                .on(challengePrice.challenge.id.eq(challenge.id)
                        .and(challengePrice.challengePricePlanType.eq(payment.challengePricePlanType)))

                .leftJoin(challengePriceOption).on(challengePriceOption.challengePrice.id.eq(challengePrice.id))
                .leftJoin(challengeOption).on(challengeOption.id.eq(challengePriceOption.challengeOption.id))

                .where(eqChallengeId(challengeId))
                .orderBy(challengeApplication.id.desc())
                .fetch();

        // 신청자별 옵션 코드 리스트 구성
        Map<AdminChallengeApplicationVo, List<String>> grouped = new LinkedHashMap<>();

        for (Tuple tuple : tuples) {
            AdminChallengeApplicationVo vo = tuple.get(0, AdminChallengeApplicationVo.class);
            String optionCode = tuple.get(1, String.class);

            grouped.computeIfAbsent(vo, k -> new ArrayList<>());

            if (optionCode != null) {
                grouped.get(vo).add(optionCode);
            }
        }

        return grouped.entrySet().stream()
                .map(entry -> new AdminChallengeApplicationWithOptionsVo(
                        entry.getKey(),
                        entry.getValue()
                ))
                .collect(Collectors.toList());
    }




    @Override
    public Page<UserChallengeApplicationVo> findUserChallengeApplicationVo(Long challengeId, Pageable pageable) {
        List<UserChallengeApplicationVo> contents = queryFactory
                .select(Projections.constructor(UserChallengeApplicationVo.class,
                        challengeApplication.id,
                        user.name,
                        user.contactEmail,
                        user.phoneNum,
                        user.accountType,
                        user.accountNum
                ))
                .from(challengeApplication)
                .leftJoin(challengeApplication.challenge, challenge)
                .leftJoin(challengeApplication.user, user)
                .leftJoin(challengeApplication.payment, payment)
                .where(
                        eqChallengeId(challengeId),
                        eqIsCanceled(false)
                )
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(challengeApplication.id.countDistinct())
                .from(challengeApplication)
                .leftJoin(challengeApplication.challenge, challenge)
                .leftJoin(challengeApplication.user, user)
                .leftJoin(challengeApplication.payment, payment)
                .where(
                        eqChallengeId(challengeId),
                        eqIsCanceled(false)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public Optional<Long> findChallengeApplicationIdByUserIdAndChallengeId(Long userId, Long challengeId) {
        return Optional.ofNullable(queryFactory
                .select(
                        challengeApplication.id
                )
                .from(challengeApplication)
                .leftJoin(challengeApplication.user, user)
                .leftJoin(challengeApplication.challenge, challenge)
                .where(
                        eqUserId(userId),
                        eqChallengeId(challengeId),
                        eqIsCanceled(false)
                )
                .fetchFirst());
    }

    @Override
    public Optional<Long> findChallengeApplicationIdByChallengeIdAndUserIdAndIsCanceled(Long challengeId, Long userId, Boolean isCanceled) {
        return Optional.ofNullable(queryFactory
                .select(challengeApplication.id)
                .from(challengeApplication)
                .leftJoin(challengeApplication.user, user)
                .leftJoin(challengeApplication.payment, payment)
                .where(
                        eqChallengeId(challengeId),
                        eqUserId(userId),
                        eqIsCanceled(isCanceled)
                )
                .fetchFirst());
    }

    @Override
    public List<String> findAllEmailByChallengeIdAndIsCanceled(Long challengeId, Boolean isCanceled) {
        return queryFactory
                .select(activeEmail(challengeApplication.user))
                .from(challengeApplication)
                .leftJoin(challengeApplication.challenge, challenge)
                .leftJoin(challengeApplication.user, user)
                .leftJoin(challengeApplication.payment, payment)
                .where(
                        eqChallengeId(challengeId),
                        eqIsCanceled(isCanceled)
                )
                .fetch();
    }

    @Override
    public Long findApplicationIdByChallengeIdAndUserId(Long challengeId, Long userId) {
        return queryFactory
                .select(challengeApplication.id)
                .from(challengeApplication)
                .leftJoin(challengeApplication.challenge, challenge)
                .leftJoin(challengeApplication.user, user)
                .where(
                        eqChallengeId(challengeId),
                        eqUserId(userId),
                        eqIsCanceled(false)
                )
                .fetchFirst();
    }

    @Override
    public Long countByChallengeId(Long challengeId) {
        return queryFactory
                .select(challengeApplication.id.countDistinct())
                .from(challengeApplication)
                .leftJoin(challengeApplication.payment, payment)
                .where(
                        eqChallengeId(challengeId),
                        eqIsCanceled(false)
                )
                .fetchOne();
    }

    @Override
    public List<ReviewNotificationUserVo> findAllReviewNotificationUserVo(Long challengeId) {
        return queryFactory
                .select(Projections.constructor(ReviewNotificationUserVo.class,
                        challengeApplication._super.user.name,
                        challengeApplication._super.user.phoneNum,
                        challengeApplication._super.id))
                .from(challengeApplication)
                .where(
                        eqChallengeId(challengeId),
                        eqIsCanceled(false),
                        reviewIsNull()
                )
                .groupBy(user.id)
                .fetch();
    }

    @Override
    public List<User> findAllNotificationUser(Long challengeId) {
        return queryFactory
                .select(challengeApplication._super.user)
                .from(challengeApplication)
                .leftJoin(challengeApplication.challenge, challenge)
                .where(
                        eqChallengeId(challengeId),
                        eqIsCanceled(false)
                )
                .groupBy(user.id)
                .fetch();
    }

    @Override
    public List<User> findAllAttendanceNullNotificationUser(Long challengeId, Long missionId) {
        return queryFactory
                .select(challengeApplication._super.user)
                .from(challengeApplication)
                .leftJoin(challengeApplication.challenge, challenge)
                .leftJoin(challengeApplication.user, user)
                .where(
                        eqChallengeId(challengeId),
                        eqIsCanceled(false),
                        attendanceIsNull(missionId)
                )
                .groupBy(user.id)
                .fetch();
    }

    @Override
    public String findGoalByApplicationId(Long applicationId) {
        return queryFactory
                .select(challengeApplication.goal)
                .from(challengeApplication)
                .where(
                        eqApplicationId(applicationId)
                )
                .fetchFirst();
    }

    @Override
    public Long findReviewByApplicationId(Long applicationId) {
        return queryFactory
                .select(challengeApplication.review.id)
                .from(challengeApplication)
                .where(
                        eqApplicationId(applicationId)
                )
                .fetchFirst();
    }

    private BooleanExpression attendanceIsNull(Long missionId) {
        return missionId != null ? user.id.notIn(JPAExpressions.select(attendance.user.id).from(attendance).where(eqMissionId(missionId))) : null;
    }

    private BooleanExpression eqMissionId(Long missionId) {
        return missionId != null ? attendance.mission.id.eq(missionId) : null;
    }


    private BooleanExpression reviewIsNull() {
        return challengeApplication._super.review.isNull();
    }

    private NumberExpression<Integer> calculateTotalCost() {
        NumberExpression<Integer> safePrice = new CaseBuilder()
                .when(challengePrice.price.isNull())
                .then(0)
                .otherwise(challengePrice.price);

        NumberExpression<Integer> safeDiscount = new CaseBuilder()
                .when(challengePrice.discount.isNull())
                .then(0)
                .otherwise(challengePrice.discount);

        NumberExpression<Integer> safeCouponDiscount = new CaseBuilder()
                .when(coupon.discount.isNull())
                .then(0)
                .otherwise(coupon.discount);

        return safePrice.subtract(safeDiscount).subtract(safeCouponDiscount);
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }

    private BooleanExpression eqApplicationId(Long applicationId) {
        return applicationId != null ? challengeApplication.id.eq(applicationId) : null;
    }

    private BooleanExpression eqIsCanceled(Boolean isCanceled) {
        return isCanceled != null ? challengeApplication._super.isCanceled.eq(isCanceled) : null;
    }

    private BooleanExpression eqIsRefunded(Boolean isRefunded) {
        return isRefunded != null ? payment.isRefunded.eq(isRefunded) : null;
    }
}
