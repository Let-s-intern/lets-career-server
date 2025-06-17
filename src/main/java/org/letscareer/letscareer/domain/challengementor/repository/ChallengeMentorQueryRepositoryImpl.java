package org.letscareer.letscareer.domain.challengementor.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengementor.vo.ChallengeMentorAdminVo;
import org.letscareer.letscareer.domain.challengementor.vo.MyChallengeMentorVo;

import java.util.List;

import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.challengementor.entity.QChallengeMentor.challengeMentor;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;

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

    @Override
    public List<MyChallengeMentorVo> findAllMyChallengeMentorVosByMentorId(Long mentorId) {
        return queryFactory
                .select(Projections.constructor(MyChallengeMentorVo.class,
                        challenge.id,
                        challenge.title,
                        challenge.shortDesc,
                        challenge.thumbnail,
                        challenge.startDate,
                        challenge.endDate))
                .from(challengeMentor)
                .leftJoin(challengeMentor.challenge, challenge)
                .leftJoin(challengeMentor.mentor, user)
                .where(eqMentorId(mentorId))
                .orderBy(challenge.endDate.desc())
                .fetch();
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }

    private BooleanExpression eqMentorId(Long mentorId) {
        return mentorId != null ? user.id.eq(mentorId) : null;
    }
}
