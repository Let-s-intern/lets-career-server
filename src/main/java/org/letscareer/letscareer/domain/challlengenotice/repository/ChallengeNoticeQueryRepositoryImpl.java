package org.letscareer.letscareer.domain.challlengenotice.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challlengenotice.vo.ChallengeNoticeVo;

import java.util.List;

import static org.letscareer.letscareer.domain.challlengenotice.entity.QChallengeNotice.challengeNotice;

@RequiredArgsConstructor
public class ChallengeNoticeQueryRepositoryImpl implements ChallengeNoticeQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ChallengeNoticeVo> findAllChallengeNoticeVos(Long challengeId) {
        return queryFactory
                .select(Projections.constructor(ChallengeNoticeVo.class,
                        challengeNotice.id,
                        challengeNotice.type,
                        challengeNotice.title,
                        challengeNotice.link,
                        challengeNotice.createDate))
                .from(challengeNotice)
                .where(
                        eqChallenge(challengeId)
                )
                .orderBy(challengeNotice.id.desc())
                .fetch();
    }

    private BooleanExpression eqChallenge(Long challengeId) {
        return challengeId != null ? challengeNotice.challenge.id.eq(challengeId) : null;
    }
}
