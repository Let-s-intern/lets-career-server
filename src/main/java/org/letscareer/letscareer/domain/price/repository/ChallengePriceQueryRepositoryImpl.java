package org.letscareer.letscareer.domain.price.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengeoption.vo.ChallengeOptionVo;
import org.letscareer.letscareer.domain.price.entity.ChallengePrice;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.letscareer.letscareer.domain.challengeoption.entity.QChallengeOption.challengeOption;
import static org.letscareer.letscareer.domain.challengeoption.entity.QChallengePriceOption.challengePriceOption;
import static org.letscareer.letscareer.domain.price.entity.QChallengePrice.challengePrice;
import static org.letscareer.letscareer.domain.price.entity.QPrice.price1;

@RequiredArgsConstructor
public class ChallengePriceQueryRepositoryImpl implements ChallengePriceQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ChallengePriceDetailVo> findChallengePriceDetailVos(Long challengeId) {
        List<ChallengePriceDetailVo> challengePriceDetailVos = jpaQueryFactory
                .select(Projections.constructor(ChallengePriceDetailVo.class,
                        challengePrice.id,
                        challengePrice.title,
                        challengePrice.price,
                        challengePrice.refund,
                        challengePrice.discount,
                        challengePrice.accountNumber,
                        challengePrice.deadline,
                        challengePrice.accountType,
                        challengePrice.challengePriceType,
                        challengePrice.challengePricePlanType,
                        challengePrice.challengeParticipationType
                ))
                .from(challengePrice)
                .where(
                        eqChallengeId(challengeId)
                )
                .fetch();

        Map<Long, List<ChallengeOptionVo>> challengeOptionListMap = jpaQueryFactory
                .select(challengePrice.id,
                        Projections.constructor(ChallengeOptionVo.class,
                                challengeOption.id,
                                challengeOption.title,
                                challengeOption.price,
                                challengeOption.discountPrice)
                )
                .from(challengePriceOption)
                .join(challengePriceOption.challengeOption, challengeOption)
                .stream()
                .collect(
                        Collectors.groupingBy(
                                tuple -> tuple.get(0, Long.class),
                                Collectors.mapping(tuple -> tuple.get(1, ChallengeOptionVo.class), Collectors.toList())
                        )
                );

        for (ChallengePriceDetailVo vo : challengePriceDetailVos) {
            vo.setChallengeOptionList(challengeOptionListMap.getOrDefault(vo.getPriceId(), Collections.emptyList()));
        }

        return challengePriceDetailVos;
    }

    @Override
    public Optional<PriceDetailVo> findPriceDetailVoByChallengeId(Long programId) {
        Integer optionTotalPrice = jpaQueryFactory
                .select(challengeOption.price.sum())
                .from(challengePriceOption)
                .leftJoin(challengeOption).on(challengePriceOption.challengeOption.id.eq(challengeOption.id))
                .leftJoin(challengePrice).on(challengePriceOption.challengePrice.id.eq(challengePrice.id))
                .where(challengePrice.challenge.id.eq(programId))
                .fetchOne();

        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(PriceDetailVo.class,
                        challengePrice.id,
                        challengePrice.price,
                        challengePrice.discount,
                        challengePrice.refund,
                        Expressions.constant(optionTotalPrice != null ? optionTotalPrice : 0) // 옵션 없으면 0!
                ))
                .from(challengePrice)
                .where(challengePrice.challenge.id.eq(programId))
                .fetchFirst());
    }

    @Override
    public Optional<Integer> findPriceRefundByChallengeId(Long challengeId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(challengePrice.refund)
                .from(challengePrice)
                .where(challengePrice.challenge.id.eq(challengeId))
                .fetchFirst());
    }

    @Override
    public Optional<ChallengePrice> findByPriceId(Long priceId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(challengePrice)
                .from(challengePrice)
                .where(
                        challengePrice._super.id.eq(priceId)
                ).fetchFirst());
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challengePrice.challenge.id.eq(challengeId) : null;
    }
}
