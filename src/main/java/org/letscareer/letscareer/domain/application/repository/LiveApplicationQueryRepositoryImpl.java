package org.letscareer.letscareer.domain.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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
import static org.letscareer.letscareer.domain.user.repository.UserRepositoryImpl.activeEmail;

@RequiredArgsConstructor
public class LiveApplicationQueryRepositoryImpl implements LiveApplicationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AdminLiveApplicationVo> findAdminLiveApplicationVos(Long liveId, Boolean isConfirmed) {
        return queryFactory
                .select(Projections.constructor(AdminLiveApplicationVo.class,
                        liveApplication.id,
                        payment.id,
                        user.name,
                        user.email,
                        user.phoneNum,
                        user.university,
                        user.major,
                        user.grade,
                        liveApplication.motivate,
                        liveApplication.question,
                        coupon.name,
                        calculateTotalCost(),
                        payment.isConfirmed,
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
                        eqIsConfirmed(isConfirmed)
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
                        activeEmail(liveApplication.user)
                )
                .from(liveApplication)
                .leftJoin(liveApplication.user, user)
                .leftJoin(liveApplication.payment, payment)
                .where(
                        eqLiveId(liveId),
                        eqIsConfirmed(true),
                        eqIsRefunded(false)
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
                        eqLiveId(liveId)
                )
                .fetchFirst());
    }


    private NumberExpression<Integer> calculateTotalCost() {
        NumberExpression<Integer> safePrice = new CaseBuilder()
                .when(livePrice.price.isNull())
                .then(0)
                .otherwise(livePrice.price);

        NumberExpression<Integer> safeDiscount = new CaseBuilder()
                .when(livePrice.discount.isNull())
                .then(0)
                .otherwise(livePrice.discount);

        NumberExpression<Integer> safeCouponDiscount = new CaseBuilder()
                .when(coupon.discount.isNull())
                .then(0)
                .otherwise(coupon.discount);

        return safePrice.subtract(safeDiscount).subtract(safeCouponDiscount);
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

    private BooleanExpression eqIsConfirmed(Boolean isConfirmed) {
        return isConfirmed != null ? payment.isConfirmed.eq(isConfirmed) : null;
    }

    private BooleanExpression eqIsRefunded(Boolean isRefunded) {
        return isRefunded != null ? payment.isRefunded.eq(isRefunded) : null;
    }
}
