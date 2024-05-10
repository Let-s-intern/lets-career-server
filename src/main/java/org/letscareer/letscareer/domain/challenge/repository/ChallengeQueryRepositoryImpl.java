package org.letscareer.letscareer.domain.challenge.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeDetailVo;

import java.util.Optional;

import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;

@RequiredArgsConstructor
public class ChallengeQueryRepositoryImpl implements ChallengeQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ChallengeDetailVo> findChallengeDetailById(Long challengeId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ChallengeDetailVo.class,
                        challenge.title,
                        challenge.shortDesc,
                        challenge.desc,
                        challenge.participationCount,
                        challenge.thumbnail,
                        challenge.startDate,
                        challenge.endDate,
                        challenge.deadline,
                        challenge.chatLink,
                        challenge.chatPassword,
                        challenge.challengeType
                ))
                .from(challenge)
                .where(
                        neChallengeId(challengeId)
                )
                .fetchOne()
        );
    }

    private BooleanExpression neChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.ne(challengeId) : null;
    }
}
