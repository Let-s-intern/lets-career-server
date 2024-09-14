package org.letscareer.letscareer.domain.report.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;

import java.util.Optional;

import static org.letscareer.letscareer.domain.report.entity.QReportPrice.reportPrice;

@RequiredArgsConstructor
public class ReportPriceQueryRepositoryImpl implements ReportPriceQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<PriceDetailVo> findPriceDetailVoByReportId(Long reportId) {
        return Optional.ofNullable(
                queryFactory.select(Projections.constructor(PriceDetailVo.class,
                        reportPrice.id,
                        reportPrice.price,
                        reportPrice.discountPrice))
                        .from(reportPrice)
                        .where(
                                reportPrice.report.id.eq(reportId)
                        )
                        .fetchFirst()
        );
    }
}
