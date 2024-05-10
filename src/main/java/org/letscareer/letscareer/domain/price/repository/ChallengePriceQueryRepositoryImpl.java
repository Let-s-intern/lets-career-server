package org.letscareer.letscareer.domain.price.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;

import java.util.List;

import static org.letscareer.letscareer.domain.price.entity.QChallengePrice.challengePrice;

@RequiredArgsConstructor
public class ChallengePriceQueryRepositoryImpl implements ChallengePriceQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ChallengePriceDetailVo> findChallengePriceDetailVos(Long challengeId) {
        return jpaQueryFactory
                .select(Projections.constructor(ChallengePriceDetailVo.class,
                        challengePrice.price,
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

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challengePrice.id.eq(challengeId) : null;
    }
}
