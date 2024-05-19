package org.letscareer.letscareer.domain.challenge.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeDetailVo;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeProfileVo;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.classification.entity.QChallengeClassification.challengeClassification;

@RequiredArgsConstructor
public class ChallengeQueryRepositoryImpl implements ChallengeQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ChallengeDetailVo> findChallengeDetailById(Long challengeId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ChallengeDetailVo.class,
                        challenge.id,
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

    @Override
    public Page<ChallengeProfileVo> findChallengeProfiles(ProgramClassification type, Pageable pageable) {
        List<ChallengeProfileVo> contents = queryFactory
                .select(Projections.constructor(ChallengeProfileVo.class,
                        challenge.id,
                        challenge.title,
                        challenge.shortDesc,
                        challenge.thumbnail,
                        challenge.startDate,
                        challenge.endDate,
                        challenge.deadline
                ))
                .from(challenge)
                .leftJoin(challenge.classificationList, challengeClassification)
                .orderBy(challenge.id.desc())
                .where(
                        eqChallengeClassification(type)
                )
                .fetch();

        JPAQuery<Challenge> countQuery = queryFactory
                .selectFrom(challenge)
                .where(
                        eqChallengeClassification(type)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }

    private BooleanExpression eqChallengeClassification(ProgramClassification type) {
        return type != null ? challengeClassification.programClassification.eq(type) : null;
    }
}
