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
import static org.letscareer.letscareer.domain.price.entity.QUserPayment.userPayment;
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
                        userPayment.isConfirmed,
                        challengeApplication.createDate
                ))
                .from(challengeApplication)
                .leftJoin(challengeApplication.challenge, challenge)
                .leftJoin(challenge, challengePrice.challenge)
                .leftJoin(challengeApplication.user, user)
                .leftJoin(challengeApplication.userPayment, userPayment)
                .leftJoin(challengeApplication.userPayment.coupon, coupon)
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
        return isConfirmed != null ? userPayment.isConfirmed.eq(isConfirmed) : null;
    }
}
