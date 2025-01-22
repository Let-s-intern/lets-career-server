package org.letscareer.letscareer.domain.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.letscareer.letscareer.domain.application.vo.AdminLiveApplicationVo;
import org.letscareer.letscareer.domain.application.vo.ReviewNotificationUserVo;
import org.letscareer.letscareer.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.application.entity.QLiveApplication.liveApplication;
import static org.letscareer.letscareer.domain.coupon.entity.QCoupon.coupon;
import static org.letscareer.letscareer.domain.live.entity.QLive.live;
import static org.letscareer.letscareer.domain.payment.entity.QPayment.payment;
import static org.letscareer.letscareer.domain.price.entity.QLivePrice.livePrice;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class LiveApplicationQueryRepositoryImpl implements LiveApplicationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<LiveApplication> findLiveApplicationByLiveIdAndUserId(Long liveId, Long userId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(liveApplication)
                .leftJoin(liveApplication.live, live)
                .leftJoin(liveApplication.user, user)
                .leftJoin(liveApplication.payment, payment)
                .where(
                        eqLiveId(liveId),
                        eqUserId(userId),
                        eqIsCanceled(false)
                )
                .fetchFirst());
    }

    @Override
    public List<AdminLiveApplicationVo> findAdminLiveApplicationVos(Long liveId, Boolean isCanceled) {
        return queryFactory
                .select(Projections.constructor(AdminLiveApplicationVo.class,
                        liveApplication.id,
                        payment.id,
                        user.name,
                        user.contactEmail,
                        user.phoneNum,
                        user.university,
                        user.major,
                        user.grade,
                        liveApplication.motivate,
                        liveApplication.question,
                        coupon.name,
                        coupon.discount,
                        payment.finalPrice,
                        payment.programPrice,
                        payment.programDiscount,
                        payment.orderId,
                        liveApplication._super.isCanceled,
                        liveApplication.createDate
                ))
                .from(liveApplication)
                .leftJoin(liveApplication.live, live)
                .leftJoin(liveApplication.live.priceList, livePrice)
                .leftJoin(liveApplication.user, user)
                .leftJoin(liveApplication.payment, payment)
                .leftJoin(liveApplication.payment.coupon, coupon)
                .orderBy(liveApplication.id.desc())
                .where(
                        eqLiveId(liveId)
                )
                .fetch();
    }

    @Override
    public Optional<Long> findLiveApplicationIdByUserIdAndLiveId(Long userId, Long liveId) {
        return Optional.ofNullable(queryFactory
                .select(
                        liveApplication.id
                )
                .from(liveApplication)
                .leftJoin(liveApplication.user, user)
                .leftJoin(liveApplication.live, live)
                .where(
                        eqUserId(userId),
                        eqLiveId(liveId),
                        eqIsCanceled(false)
                )
                .fetchFirst());
    }

    @Override
    public List<String> findQuestionListByLiveId(Long liveId) {
        return queryFactory
                .select(
                        liveApplication.question
                )
                .from(liveApplication)
                .leftJoin(liveApplication.live, live)
                .where(
                        eqLiveId(liveId),
                        liveApplication.question.isNotEmpty()
                )
                .fetch();
    }

    @Override
    public List<String> findMotivateListByLiveId(Long liveId) {
        return queryFactory
                .select(
                        liveApplication.motivate
                )
                .from(liveApplication)
                .leftJoin(liveApplication.live, live)
                .where(
                        eqLiveId(liveId),
                        liveApplication.motivate.isNotEmpty()
                )
                .fetch();
    }

    @Override
    public Long countByLiveId(Long liveId) {
        return queryFactory
                .select(liveApplication.id.countDistinct())
                .from(liveApplication)
                .where(
                        eqLiveId(liveId),
                        eqIsCanceled(false)
                )
                .fetchOne();
    }

    @Override
    public List<ReviewNotificationUserVo> findAllReviewNotificationUserVo(Long liveId) {
        return queryFactory
                .select(Projections.constructor(ReviewNotificationUserVo.class,
                        liveApplication._super.user.name,
                        liveApplication._super.user.phoneNum,
                        liveApplication._super.id))
                .from(liveApplication)
                .where(
                        eqLiveId(liveId),
                        eqIsCanceled(false),
                        reviewIsNull()
                )
                .fetch();
    }

    @Override
    public List<User> findAllRemindNotificationUser(Long liveId) {
        return queryFactory
                .select(liveApplication._super.user)
                .from(liveApplication)
                .leftJoin(liveApplication.live, live)
                .where(
                        eqLiveId(liveId),
                        eqIsCanceled(false)
                )
                .fetch();
    }

    private BooleanExpression reviewIsNull() {
        return liveApplication._super.review.isNull();
    }

    private BooleanExpression eqApplicationId(Long applicationId) {
        return applicationId != null ? liveApplication.id.eq(applicationId) : null;
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }


    private BooleanExpression eqLiveId(Long liveId) {
        return liveId != null ? live.id.eq(liveId) : null;
    }

    private BooleanExpression eqIsCanceled(Boolean isCanceled) {
        return isCanceled != null ? liveApplication._super.isCanceled.eq(isCanceled) : null;
    }

    private BooleanExpression eqIsRefunded(Boolean isRefunded) {
        return isRefunded != null ? payment.isRefunded.eq(isRefunded) : null;
    }
}
