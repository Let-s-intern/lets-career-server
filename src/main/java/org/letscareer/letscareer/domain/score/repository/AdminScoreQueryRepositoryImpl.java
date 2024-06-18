package org.letscareer.letscareer.domain.score.repository;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.score.entity.AdminScore;

import java.util.Optional;

import static org.letscareer.letscareer.domain.application.entity.QApplication.application;
import static org.letscareer.letscareer.domain.application.entity.QChallengeApplication.challengeApplication;
import static org.letscareer.letscareer.domain.score.entity.QAdminScore.adminScore;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminScoreQueryRepositoryImpl implements AdminScoreQueryRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<AdminScore> findAdminScoreByChallengeIdAndApplicationId(Long challengeId, Long applicationId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(adminScore)
                .leftJoin(adminScore.application, challengeApplication)
                .where(
                        eqChallengeApplicationId(applicationId)
                )
                .fetchOne()
        );
    }

    private BooleanExpression eqChallengeApplicationId(Long applicationId) {
        return applicationId != null ? challengeApplication._super.id.eq(applicationId) : null;
    }
}
