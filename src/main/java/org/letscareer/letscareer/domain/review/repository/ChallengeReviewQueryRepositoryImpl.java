package org.letscareer.letscareer.domain.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChallengeReviewQueryRepositoryImpl implements ChallengeReviewQueryRepository {
    private final JPAQueryFactory queryFactory;
}
