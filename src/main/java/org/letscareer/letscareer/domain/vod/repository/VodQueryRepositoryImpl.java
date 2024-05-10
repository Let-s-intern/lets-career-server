package org.letscareer.letscareer.domain.vod.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.vod.vo.VodDetailVo;

import java.util.Optional;

import static org.letscareer.letscareer.domain.vod.entity.QVod.vod;

@RequiredArgsConstructor
public class VodQueryRepositoryImpl implements VodQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<VodDetailVo> findVodDetailVo(Long vodId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(VodDetailVo.class,
                        vod.title,
                        vod.shortDesc,
                        vod.thumbnail,
                        vod.job
                ))
                .from(vod)
                .where(
                        neVodId(vodId)
                )
                .fetchOne()
        );
    }

    private BooleanExpression neVodId(Long vodId) {
        return vodId != null ? vod.id.ne(vodId) : null;
    }
}
