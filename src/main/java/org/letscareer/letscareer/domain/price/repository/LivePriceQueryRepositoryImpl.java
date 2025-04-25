package org.letscareer.letscareer.domain.price.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.price.vo.LivePriceDetailVo;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;

import java.util.Optional;

import static org.letscareer.letscareer.domain.price.entity.QLivePrice.livePrice;

@RequiredArgsConstructor
public class LivePriceQueryRepositoryImpl implements LivePriceQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<LivePriceDetailVo> findLivePriceDetailVo(Long liveId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(LivePriceDetailVo.class,
                        livePrice.id,
                        livePrice.price,
                        livePrice.discount,
                        livePrice.accountNumber,
                        livePrice.deadline,
                        livePrice.accountType,
                        livePrice.livePriceType
                ))
                .from(livePrice)
                .where(
                        eqLiveId(liveId)
                )
                .fetchOne()
        );
    }

    @Override
    public Optional<PriceDetailVo> findPriceDetailVoByLiveId(Long programId) {
        return Optional.ofNullable(
                jpaQueryFactory.select(Projections.constructor(PriceDetailVo.class,
                        livePrice.id,
                        livePrice.price,
                        livePrice.discount,
                        Expressions.constant(0),
                        Expressions.constant(0)))
                        .from(livePrice)
                        .where(
                                livePrice.live.id.eq(programId)
                        )
                        .fetchFirst()
        );
    }

    private BooleanExpression eqLiveId(Long liveId) {
        return liveId != null ? livePrice.live.id.eq(liveId) : null;
    }
}
