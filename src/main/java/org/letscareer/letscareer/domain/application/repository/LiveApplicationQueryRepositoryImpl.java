package org.letscareer.letscareer.domain.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.letscareer.letscareer.domain.application.vo.AdminLiveApplicationVo;
import org.letscareer.letscareer.domain.live.vo.LiveEmailVo;

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
                        eqLiveId(liveId),
                        eqIsCanceled(isCanceled)
                )
                .fetch();
    }

    @Override
    public LiveEmailVo findLiveEmailVoByApplicationId(Long applicationId) {
        return queryFactory
                .select(Projections.constructor(LiveEmailVo.class,
                        live.id,
                        live.title,
                        live.startDate,
                        live.endDate,
                        live.progressType,
                        live.place,
                        live.zoomLink,
                        live.zoomPassword))
                .from(liveApplication)
                .leftJoin(liveApplication.live, live)
                .where(
                        eqApplicationId(applicationId)
                )
                .fetchFirst();
    }

    @Override
    public List<String> findEmailListByLiveId(Long liveId) {
        return queryFactory
                .select(
                        liveApplication.user.contactEmail
                )
                .from(liveApplication)
                .leftJoin(liveApplication.user, user)
                .leftJoin(liveApplication.payment, payment)
                .where(
                        eqLiveId(liveId),
                        eqIsCanceled(false)
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
