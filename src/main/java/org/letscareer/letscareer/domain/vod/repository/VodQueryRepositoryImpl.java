package org.letscareer.letscareer.domain.vod.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.vod.entity.Vod;
import org.letscareer.letscareer.domain.vod.vo.VodDetailVo;
import org.letscareer.letscareer.domain.vod.vo.VodProfileVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.classification.entity.QVodClassification.vodClassification;
import static org.letscareer.letscareer.domain.vod.entity.QVod.vod;

@RequiredArgsConstructor
public class VodQueryRepositoryImpl implements VodQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<VodDetailVo> findVodDetailVo(Long vodId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(VodDetailVo.class,
                        vod.id,
                        vod.title,
                        vod.shortDesc,
                        vod.thumbnail,
                        vod.job,
                        vod.link,
                        vod.isVisible
                ))
                .from(vod)
                .where(
                        eqVodId(vodId)
                )
                .fetchOne()
        );
    }

    @Override
    public Page<VodProfileVo> findVodProfileVos(ProgramClassification type, Pageable pageable) {
        List<VodProfileVo> contents = jpaQueryFactory
                .select(Projections.constructor(VodProfileVo.class,
                        vod.id,
                        vod.title,
                        vod.shortDesc,
                        vod.thumbnail,
                        vod.link
                ))
                .from(vod)
                .leftJoin(
                        vod.classificationList, vodClassification
                )
                .where(
                        eqVodClassification(type)
                )
                .orderBy(vod.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Vod> countQuery = jpaQueryFactory
                .selectFrom(vod)
                .where(
                        eqVodClassification(type)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    private BooleanExpression eqVodId(Long vodId) {
        return vodId != null ? vod.id.eq(vodId) : null;
    }

    private BooleanExpression eqVodClassification(ProgramClassification type) {
        return type != null ? vodClassification.programClassification.eq(type) : null;
    }
}
