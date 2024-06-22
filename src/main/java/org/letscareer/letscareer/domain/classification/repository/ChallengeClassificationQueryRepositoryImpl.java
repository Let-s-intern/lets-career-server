package org.letscareer.letscareer.domain.classification.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.vo.ChallengeClassificationDetailVo;

import java.util.List;

import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.classification.entity.QChallengeClassification.challengeClassification;

@RequiredArgsConstructor
public class ChallengeClassificationQueryRepositoryImpl implements ChallengeClassificationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ChallengeClassificationDetailVo> findClassificationDetailVos(Long challengeId) {
        return queryFactory
                .select(Projections.constructor(ChallengeClassificationDetailVo.class,
                        challengeClassification.programClassification
                ))
                .from(challengeClassification)
                .where(
                        eqChallengeId(challengeId)
                )
                .fetch();
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challengeClassification.challenge.id.eq(challengeId) : null;
    }
}
