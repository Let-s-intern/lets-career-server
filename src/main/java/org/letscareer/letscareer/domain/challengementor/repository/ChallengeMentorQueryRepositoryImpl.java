package org.letscareer.letscareer.domain.challengementor.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChallengeMentorQueryRepositoryImpl implements ChallengeMentorQueryRepository {
    private final JPAQueryFactory queryFactory;
}
