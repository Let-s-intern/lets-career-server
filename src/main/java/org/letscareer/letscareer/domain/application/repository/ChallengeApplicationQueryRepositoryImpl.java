package org.letscareer.letscareer.domain.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;

import java.util.List;

import static org.letscareer.letscareer.domain.application.entity.QChallengeApplication.challengeApplication;
import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.coupon.entity.QCoupon.coupon;
import static org.letscareer.letscareer.domain.price.entity.QChallengePrice.challengePrice;
import static org.letscareer.letscareer.domain.payment.entity.QPayment.payment;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class ChallengeApplicationQueryRepositoryImpl implements ChallengeApplicationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AdminChallengeApplicationVo> findAdminChallengeApplicationVos(Long challengeId, Boolean isConfirmed) {
        return queryFactory
                .select(Projections.constructor(AdminChallengeApplicationVo.class,
                        user.name,
                        user.email,
                        user.phoneNum,
                        user.university,
                        user.grade,
                        user.major,
                        coupon.name,
                        calculateTotalCost(),
                        payment.isConfirmed,
                        challengeApplication.createDate
                ))
                .from(challengeApplication)
                .leftJoin(challengeApplication.challenge, challenge)
                .leftJoin(challenge, challengePrice.challenge)
                .leftJoin(challengeApplication.user, user)
                .leftJoin(challengeApplication.payment, payment)
                .leftJoin(challengeApplication.payment.coupon, coupon)
                .orderBy(challengeApplication.id.desc())
                .where(
                        eqChallengeId(challengeId),
                        eqIsConfirmed(isConfirmed)
                )
                .fetch();
    }

    private NumberExpression<Integer> calculateTotalCost() {
        return challengePrice.price.subtract(challengePrice.discount).subtract(coupon.discount);
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }

    private BooleanExpression eqIsConfirmed(Boolean isConfirmed) {
        return isConfirmed != null ? payment.isConfirmed.eq(isConfirmed) : null;
    }
}
