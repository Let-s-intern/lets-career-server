package org.letscareer.letscareer.domain.challengeoption.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static org.letscareer.letscareer.domain.challengeoption.entity.QChallengeOption.challengeOption;
import static org.letscareer.letscareer.domain.challengeoption.entity.QChallengePriceOption.challengePriceOption;

@RequiredArgsConstructor
public class ChallengePriceOptionQueryRepositoryImpl implements ChallengePriceOptionQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<String> findAllByChallengePriceId(Long challengePriceId) {
        return queryFactory
                .select(challengeOption.title)
                .from(challengePriceOption)
                .leftJoin(challengePriceOption.challengeOption, challengeOption)
                .where(
                        eqChallengePriceId(challengePriceId)
                )
                .fetch();
    }

    private BooleanExpression eqChallengePriceId(Long challengePriceId) {
        return challengePriceId != null ? challengePriceOption.challengePrice.id.eq(challengePriceId) : null;
    }
}
