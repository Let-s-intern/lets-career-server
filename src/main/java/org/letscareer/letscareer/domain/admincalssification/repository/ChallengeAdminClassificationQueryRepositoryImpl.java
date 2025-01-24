package org.letscareer.letscareer.domain.admincalssification.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.admincalssification.vo.ChallengeAdminClassificationDetailVo;

import java.util.List;

import static org.letscareer.letscareer.domain.admincalssification.entity.QChallengeAdminClassification.challengeAdminClassification;

@RequiredArgsConstructor
public class ChallengeAdminClassificationQueryRepositoryImpl implements ChallengeAdminClassificationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ChallengeAdminClassificationDetailVo> findClassificationDetailVos(Long challengeId) {
        return queryFactory
                .select(Projections.constructor(ChallengeAdminClassificationDetailVo.class,
                        challengeAdminClassification.programAdminClassification
                ))
                .from(challengeAdminClassification)
                .where(
                        eqChallengeId(challengeId)
                )
                .fetch();
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challengeAdminClassification.challenge.id.eq(challengeId) : null;
    }
}
