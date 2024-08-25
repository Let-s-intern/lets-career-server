package org.letscareer.letscareer.domain.report.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReportFeedbackQueryRepositoryImpl implements ReportFeedbackQueryRepository {
    private final JPAQueryFactory queryFactory;
}
