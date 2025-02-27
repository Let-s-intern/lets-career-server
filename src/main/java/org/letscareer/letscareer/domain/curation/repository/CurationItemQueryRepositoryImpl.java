package org.letscareer.letscareer.domain.curation.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.type.BlogType;
import org.letscareer.letscareer.domain.curation.type.CurationItemProgramType;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationItemVo;
import org.letscareer.letscareer.domain.curation.vo.CurationItemVo;
import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;
import java.util.List;

import static org.letscareer.letscareer.domain.blog.entity.QBlog.blog;
import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.live.entity.QLive.live;
import static org.letscareer.letscareer.domain.curation.entity.QCurationItem.curationItem;
import static org.letscareer.letscareer.domain.report.entity.QReport.report;
import static org.letscareer.letscareer.domain.vod.entity.QVod.vod;

@RequiredArgsConstructor
public class CurationItemQueryRepositoryImpl implements CurationItemQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AdminCurationItemVo> findAllAdminCurationItemVosByCurationId(Long curationId) {
        return queryFactory.select(Projections.constructor(AdminCurationItemVo.class,
                        curationItem.id,
                        curationItem.programType,
                        curationItem.programId,
                        programCreateDateExpression(),
                        reportTypeExpression(),
                        curationItem.tag,
                        titleExpression(),
                        urlExpression(),
                        thumbnailExpression()))
                .from(curationItem)
                .leftJoin(challenge).on(
                        curationItem.programType.eq(CurationItemProgramType.CHALLENGE)
                                .and(curationItem.programId.eq(challenge.id))
                )
                .leftJoin(live).on(
                        curationItem.programType.eq(CurationItemProgramType.LIVE)
                                .and(curationItem.programId.eq(live.id))
                )
                .leftJoin(vod).on(
                        curationItem.programType.eq(CurationItemProgramType.VOD)
                                .and(curationItem.programId.eq(vod.id))
                )
                .leftJoin(report).on(
                        curationItem.programType.eq(CurationItemProgramType.REPORT)
                                .and(curationItem.programId.eq(report.id))
                )
                .leftJoin(blog).on(
                        curationItem.programType.eq(CurationItemProgramType.BLOG)
                                .and(curationItem.programId.eq(blog.id))
                )
                .where(
                        eqCurationId(curationId)
                )
                .fetch();
    }

    @Override
    public List<CurationItemVo> findAllCurationItemVosByCurationId(Long curationId) {
        return queryFactory.select(Projections.constructor(CurationItemVo.class,
                        curationItem.id,
                        curationItem.programType,
                        curationItem.programId,
                        programCreateDateExpression(),
                        startDateExpression(),
                        endDateExpression(),
                        deadlineExpression(),
                        blogTypeExpression(),
                        reportTypeExpression(),
                        curationItem.tag,
                        titleExpression(),
                        urlExpression(),
                        thumbnailExpression()))
                .from(curationItem)
                .leftJoin(challenge).on(
                        curationItem.programType.eq(CurationItemProgramType.CHALLENGE)
                        .and(curationItem.programId.eq(challenge.id))
                )
                .leftJoin(live).on(
                        curationItem.programType.eq(CurationItemProgramType.LIVE)
                                .and(curationItem.programId.eq(live.id))
                )
                .leftJoin(vod).on(
                        curationItem.programType.eq(CurationItemProgramType.VOD)
                                .and(curationItem.programId.eq(vod.id))
                )
                .leftJoin(report).on(
                        curationItem.programType.eq(CurationItemProgramType.REPORT)
                                .and(curationItem.programId.eq(report.id))
                )
                .leftJoin(blog).on(
                        curationItem.programType.eq(CurationItemProgramType.BLOG)
                                .and(curationItem.programId.eq(blog.id))
                )
                .where(
                        eqCurationId(curationId)
                )
                .fetch();
    }

    private BooleanExpression eqCurationId(Long curationId) {
        return curationId != null ? curationItem.curation.id.eq(curationId) : null;
    }

    private Expression<LocalDateTime> startDateExpression() {
        return new CaseBuilder()
                .when(curationItem.programType.eq(CurationItemProgramType.CHALLENGE)).then(challenge.startDate)
                .when(curationItem.programType.eq(CurationItemProgramType.LIVE)).then(live.startDate)
                .otherwise((LocalDateTime) null);
    }

    private Expression<LocalDateTime> endDateExpression() {
        return new CaseBuilder()
                .when(curationItem.programType.eq(CurationItemProgramType.CHALLENGE)).then(challenge.endDate)
                .when(curationItem.programType.eq(CurationItemProgramType.LIVE)).then(live.endDate)
                .otherwise((LocalDateTime) null);
    }

    private Expression<LocalDateTime> deadlineExpression() {
        return new CaseBuilder()
                .when(curationItem.programType.eq(CurationItemProgramType.CHALLENGE)).then(challenge.deadline)
                .when(curationItem.programType.eq(CurationItemProgramType.LIVE)).then(live.deadline)
                .otherwise((LocalDateTime) null);
    }

    private Expression<LocalDateTime> programCreateDateExpression() {
        return new CaseBuilder()
                .when(curationItem.programType.eq(CurationItemProgramType.CHALLENGE)).then(challenge.createDate)
                .when(curationItem.programType.eq(CurationItemProgramType.LIVE)).then(live.createDate)
                .when(curationItem.programType.eq(CurationItemProgramType.REPORT)).then(report.createDate)
                .when(curationItem.programType.eq(CurationItemProgramType.BLOG)).then(blog.displayDate)
                .otherwise(curationItem.createDate);
    }

    private Expression<BlogType> blogTypeExpression() {
        return new CaseBuilder()
                .when(curationItem.programType.eq(CurationItemProgramType.BLOG)).then(blog.category)
                .otherwise((BlogType) null);
    }

    private Expression<ReportType> reportTypeExpression() {
        return new CaseBuilder()
                .when(curationItem.programType.eq(CurationItemProgramType.REPORT)).then(report.type)
                .otherwise((ReportType) null);
    }

    private Expression<String> titleExpression() {
        return new CaseBuilder()
                .when(curationItem.programType.eq(CurationItemProgramType.CHALLENGE)).then(challenge.title)
                .when(curationItem.programType.eq(CurationItemProgramType.LIVE)).then(live.title)
                .when(curationItem.programType.eq(CurationItemProgramType.VOD)).then(vod.title)
                .when(curationItem.programType.eq(CurationItemProgramType.REPORT)).then(report.title)
                .when(curationItem.programType.eq(CurationItemProgramType.BLOG)).then(blog.title)
                .otherwise(curationItem.title);
    }

    private Expression<String> urlExpression() {
        return new CaseBuilder()
                .when(curationItem.programType.eq(CurationItemProgramType.VOD)).then(vod.link)
                .when(curationItem.programType.eq(CurationItemProgramType.ETC)).then(curationItem.url)
                .otherwise((String) null);
    }

    private Expression<String> thumbnailExpression() {
        return new CaseBuilder()
                .when(curationItem.programType.eq(CurationItemProgramType.CHALLENGE)).then(challenge.thumbnail)
                .when(curationItem.programType.eq(CurationItemProgramType.LIVE)).then(live.thumbnail)
                .when(curationItem.programType.eq(CurationItemProgramType.VOD)).then(vod.thumbnail)
                .when(curationItem.programType.eq(CurationItemProgramType.BLOG)).then(blog.thumbnail)
                .otherwise(curationItem.thumbnail);
    }
}
