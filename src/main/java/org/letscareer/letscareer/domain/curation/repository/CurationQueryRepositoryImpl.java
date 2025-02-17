package org.letscareer.letscareer.domain.curation.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CurationQueryRepositoryImpl implements CurationQueryRepository {
    private final JPAQueryFactory queryFactory;
}
