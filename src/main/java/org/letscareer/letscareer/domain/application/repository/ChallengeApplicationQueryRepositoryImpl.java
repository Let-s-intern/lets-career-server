package org.letscareer.letscareer.domain.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.application.vo.UserChallengeApplicationVo;

import java.util.List;

import static org.letscareer.letscareer.domain.application.entity.QChallengeApplication.challengeApplication;
import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.coupon.entity.QCoupon.coupon;
import static org.letscareer.letscareer.domain.payment.entity.QPayment.payment;
import static org.letscareer.letscareer.domain.price.entity.QChallengePrice.challengePrice;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class ChallengeApplicationQueryRepositoryImpl implements ChallengeApplicationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AdminChallengeApplicationVo> findAdminChallengeApplicationVos(Long challengeId, Boolean isConfirmed) {
        return queryFactory
                .select(Projections.constructor(AdminChallengeApplicationVo.class,
                        challengeApplication.id,
                        payment.id,
                        user.name,
                        user.email,
                        user.phoneNum,
                        user.university,
                        user.grade,
                        user.major,
                        coupon.name,
                        calculateTotalCost(),
                        payment.isConfirmed,
                        user.wishJob,
                        user.wishCompany,
                        user.inflowPath,
                        challengeApplication.createDate
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
                        eqIsConfirmed(isConfirmed)
                )
                .fetch();
    }

    @Override
    public List<UserChallengeApplicationVo> findUserChallengeApplicationVo(Long challengeId) {
        return queryFactory
                .select(Projections.constructor(UserChallengeApplicationVo.class,
                        user.id,
                        user.name,
                        user.contactEmail,
                        user.phoneNum,
                        user.accountType,
                        user.accountNum
                ))
                .from(challengeApplication)
                .leftJoin(challengeApplication.challenge, challenge)
                .leftJoin(challengeApplication.user, user)
                .where(
                        eqChallengeId(challengeId)
                )
                .fetch();
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

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }

    private BooleanExpression eqIsConfirmed(Boolean isConfirmed) {
        return isConfirmed != null ? payment.isConfirmed.eq(isConfirmed) : null;
    }
}
