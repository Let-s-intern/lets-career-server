package org.letscareer.letscareer.domain.application.repository.report;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplicationOption;
import org.letscareer.letscareer.domain.report.vo.ReportApplicationOptionPriceVo;

import java.util.List;

import static org.letscareer.letscareer.domain.application.entity.report.QReportApplicationOption.reportApplicationOption;
import static org.letscareer.letscareer.domain.application.entity.report.QReportApplication.reportApplication;

@RequiredArgsConstructor
public class ReportApplicationOptionQueryRepositoryImpl implements ReportApplicationOptionQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReportApplicationOptionPriceVo> findAllReportApplicationOptionPriceVosByReportApplicationId(Long reportApplicationId) {
        return queryFactory
                .select(Projections.constructor(ReportApplicationOptionPriceVo.class,
                        reportApplicationOption.price,
                        reportApplicationOption.discountPrice))
                .from(reportApplicationOption)
                .leftJoin(reportApplicationOption.reportApplication, reportApplication)
                .where(
                        eqReportApplicationId(reportApplicationId)
                )
                .fetch();
    }

    @Override
    public List<ReportApplicationOption> findAllReportApplicationOptionsByApplicationId(Long applicationId) {
        return queryFactory
                .select(reportApplicationOption)
                .from(reportApplicationOption)
                .where(eqReportApplicationId(applicationId))
                .fetch();
    }

    private BooleanExpression eqReportApplicationId(Long reportApplicationId) {
        return reportApplicationId != null ? reportApplication.id.eq(reportApplicationId) : null;
    }
}
