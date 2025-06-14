package org.letscareer.letscareer.domain.challengementor.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengementor.vo.ChallengeMentorAdminVo;

import java.util.List;

import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.challengementor.entity.QChallengeMentor.challengeMentor;

@RequiredArgsConstructor
public class ChallengeMentorQueryRepositoryImpl implements ChallengeMentorQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ChallengeMentorAdminVo> findChallengeMentorAdminVosByChallengeId(Long challengeId) {
        return queryFactory
                .select(Projections.constructor(ChallengeMentorAdminVo.class,
                        challengeMentor.id,
                        challengeMentor.mentor.id,
                        challengeMentor.mentor.name))
                .from(challengeMentor)
                .leftJoin(challengeMentor.challenge, challenge)
                .where(eqChallengeId(challengeId))
                .fetch();
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }
}
