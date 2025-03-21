package org.letscareer.letscareer.domain.price.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.price.entity.ChallengePrice;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.price.entity.QChallengePrice.challengePrice;
import static org.letscareer.letscareer.domain.price.entity.QPrice.price1;

@RequiredArgsConstructor
public class ChallengePriceQueryRepositoryImpl implements ChallengePriceQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ChallengePriceDetailVo> findChallengePriceDetailVos(Long challengeId) {
        return jpaQueryFactory
                .select(Projections.constructor(ChallengePriceDetailVo.class,
                        challengePrice.id,
                        challengePrice.price,
                        challengePrice.refund,
                        challengePrice.discount,
                        challengePrice.accountNumber,
                        challengePrice.deadline,
                        challengePrice.accountType,
                        challengePrice.challengePriceType,
                        challengePrice.challengeUserType,
                        challengePrice.challengeParticipationType
                ))
                .from(challengePrice)
                .where(
                        eqChallengeId(challengeId)
                )
                .fetch();
    }

    @Override
    public Optional<PriceDetailVo> findPriceDetailVoByChallengeId(Long programId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(PriceDetailVo.class,
                        challengePrice.id,
                        challengePrice.price,
                        challengePrice.discount,
                        challengePrice.refund
                ))
                .from(challengePrice)
                .where(
                        challengePrice.challenge.id.eq(programId)
                )
                .fetchFirst());
    }

    @Override
    public Optional<Integer> findPriceRefundByChallengeId(Long challengeId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(challengePrice.refund)
                .from(challengePrice)
                .where(challengePrice.challenge.id.eq(challengeId))
                .fetchFirst());
    }

    @Override
    public Optional<ChallengePrice> findByPriceId(Long priceId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(challengePrice)
                .from(challengePrice)
                .where(
                        challengePrice._super.id.eq(priceId)
                ).fetchFirst());
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challengePrice.challenge.id.eq(challengeId) : null;
    }
}
