package org.letscareer.letscareer.domain.challenge.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeDetailVo;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.application.entity.QChallengeApplication.challengeApplication;
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
                        challenge.description,
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
                        eqChallengeId(challengeId)
                )
                .fetchOne()
        );
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }
}
