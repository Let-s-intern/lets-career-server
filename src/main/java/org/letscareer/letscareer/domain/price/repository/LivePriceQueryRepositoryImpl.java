package org.letscareer.letscareer.domain.price.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.price.vo.LivePriceDetailVo;

import java.util.Optional;

import static org.letscareer.letscareer.domain.price.entity.QLivePrice.livePrice;

@RequiredArgsConstructor
public class LivePriceQueryRepositoryImpl implements LivePriceQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<LivePriceDetailVo> findLivePriceDetailVo(Long liveId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(LivePriceDetailVo.class,
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

    private BooleanExpression eqLiveId(Long liveId) {
        return liveId != null ? livePrice.id.eq(liveId) : null;
    }
}
