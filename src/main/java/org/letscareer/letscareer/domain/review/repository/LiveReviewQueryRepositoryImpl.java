package org.letscareer.letscareer.domain.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LiveReviewQueryRepositoryImpl implements LiveReviewQueryRepository {
    private final JPAQueryFactory queryFactory;
}
