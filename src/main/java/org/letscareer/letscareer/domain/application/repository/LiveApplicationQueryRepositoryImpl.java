package org.letscareer.letscareer.domain.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.vo.AdminLiveApplicationVo;

import java.util.List;

import static org.letscareer.letscareer.domain.application.entity.QLiveApplication.liveApplication;
import static org.letscareer.letscareer.domain.coupon.entity.QCoupon.coupon;
import static org.letscareer.letscareer.domain.live.entity.QLive.live;
import static org.letscareer.letscareer.domain.price.entity.QLivePrice.livePrice;
import static org.letscareer.letscareer.domain.price.entity.QUserPayment.userPayment;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class LiveApplicationQueryRepositoryImpl implements LiveApplicationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AdminLiveApplicationVo> findAdminLiveApplicationVos(Long liveId, Boolean isConfirmed) {
        return queryFactory
                .select(Projections.constructor(AdminLiveApplicationVo.class,
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
                        userPayment.isConfirmed,
                        liveApplication.createDate
                ))
                .from(liveApplication)
                .leftJoin(liveApplication.live, live)
                .leftJoin(live, livePrice.live)
                .leftJoin(liveApplication.user, user)
                .leftJoin(liveApplication.userPayment, userPayment)
                .leftJoin(liveApplication.userPayment.coupon, coupon)
                .orderBy(liveApplication.id.desc())
                .where(
                        eqLiveId(liveId),
                        eqIsConfirmed(isConfirmed)
                )
                .fetch();
    }

    private NumberExpression<Integer> calculateTotalCost() {
        return livePrice.price.subtract(livePrice.discount).subtract(coupon.discount);
    }

    private BooleanExpression eqLiveId(Long liveId) {
        return liveId != null ? live.id.eq(liveId) : null;
    }

    private BooleanExpression eqIsConfirmed(Boolean isConfirmed) {
        return isConfirmed != null ? userPayment.isConfirmed.eq(isConfirmed) : null;
    }
}
