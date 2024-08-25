package org.letscareer.letscareer.domain.application.repository.report;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReportApplicationQueryRepositoryImpl implements ReportApplicationQueryRepository {
    private final JPAQueryFactory queryFactory;
}
