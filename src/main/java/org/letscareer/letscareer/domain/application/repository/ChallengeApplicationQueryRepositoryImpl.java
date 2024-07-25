package org.letscareer.letscareer.domain.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.application.vo.UserChallengeApplicationVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.application.entity.QChallengeApplication.challengeApplication;
import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
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
    public List<AdminChallengeApplicationVo> findAdminChallengeApplicationVos(Long challengeId, Boolean isCanceled) {
        return queryFactory
                .select(Projections.constructor(AdminChallengeApplicationVo.class,
                        challengeApplication.id,
                        payment.id,
                        user.name,
                        user.contactEmail,
                        user.phoneNum,
                        user.university,
                        user.grade,
                        user.major,
                        coupon.name,
                        payment.finalPrice,
                        challengeApplication._super.isCanceled,
                        user.wishJob,
                        user.wishCompany,
                        user.inflowPath,
                        challengeApplication.createDate,
                        user.accountType,
                        user.accountNum
                ))
                .from(challengeApplication)
                .leftJoin(challengeApplication.challenge, challenge)
                .leftJoin(challengeApplication.challenge.priceList, challengePrice)
                .leftJoin(challengeApplication.user, user)
                .leftJoin(challengeApplication.payment, payment)
                .leftJoin(challengeApplication.payment.coupon, coupon)
                .orderBy(challengeApplication.id.desc())
                .where(
                        eqChallengeId(challengeId),
                        eqIsCanceled(isCanceled)
                )
                .fetch();
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
                        eqUserId(userId)
                )
                .fetchOne();
    }

    @Override
    public Long countByChallengeId(Long challengeId) {
        return queryFactory
                .select(challengeApplication.id.countDistinct())
                .from(challengeApplication)
                .where(
                        eqChallengeId(challengeId),
                        eqIsCanceled(false)
                )
                .fetchOne();
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

    private BooleanExpression eqIsCanceled(Boolean isCanceled) {
        return isCanceled != null ? challengeApplication._super.isCanceled.eq(isCanceled) : null;
    }
}
