package org.letscareer.letscareer.domain.challengeguide.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengeguide.vo.ChallengeGuideVo;

import java.util.List;

import static org.letscareer.letscareer.domain.challengeguide.entity.QChallengeGuide.challengeGuide;

@RequiredArgsConstructor
public class ChallengeGuideQueryRepositoryImpl implements ChallengeGuideQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ChallengeGuideVo> findAllChallengeGuideAdminVos(Long challengeId) {
        return queryFactory
                .select(Projections.constructor(ChallengeGuideVo.class,
                        challengeGuide.id,
                        challengeGuide.title,
                        challengeGuide.link,
                        challengeGuide.createDate))
                .from(challengeGuide)
                .where(
                        eqChallenge(challengeId)
                )
                .orderBy(challengeGuide.id.desc())
                .fetch();
    }

    private BooleanExpression eqChallenge(Long challengeId) {
        return challengeId != null ? challengeGuide.challenge.id.eq(challengeId) : null;
    }
}
