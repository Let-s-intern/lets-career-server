package org.letscareer.letscareer.domain.price.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;

import java.util.Optional;

import static org.letscareer.letscareer.domain.price.entity.QPrice.price1;

@RequiredArgsConstructor
public class PriceQueryRepositoryImpl implements PriceQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<PriceDetailVo> findPriceDetailVoByPriceId(Long priceId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(PriceDetailVo.class,
                        price1.id,
                        price1.price,
                        price1.discount,
                        price1.deadline,
                        price1.accountType,
                        price1.accountNumber
                ))
                .from(price1)
                .where(
                        eqPriceId(priceId)
                )
                .fetchOne()
        );
    }

    private BooleanExpression eqPriceId(Long priceId) {
        return priceId != null ? price1.id.eq(priceId) : null;
    }
}
