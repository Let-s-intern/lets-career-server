package org.letscareer.letscareer.domain.report.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.entity.ReportOption;

import java.util.List;
import java.util.Objects;

import static org.letscareer.letscareer.domain.report.entity.QReport.report;
import static org.letscareer.letscareer.domain.report.entity.QReportOption.reportOption;

@RequiredArgsConstructor
public class ReportOptionQueryRepositoryImpl implements ReportOptionQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReportOption> findReportOptionsByReportIdAndOptionIds(Long reportId, List<Long> optionIds) {
        return queryFactory
                .select(reportOption)
                .from(report)
                .leftJoin(report.optionList, reportOption)
                .where(
                        eqReportId(reportId),
                        inOptionIds(optionIds)
                )
                .fetch();
    }

    private BooleanExpression eqReportId(Long reportId) {
        return reportId != null ? report.id.eq(reportId) : null;
    }

    private BooleanExpression inOptionIds(List<Long> optionIds) {
        if (Objects.isNull(optionIds) || optionIds.isEmpty()) return null;
        return reportOption.id.in(optionIds);
    }
}
