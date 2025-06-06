package org.letscareer.letscareer.domain.price.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengeoption.vo.ChallengeOptionVo;
import org.letscareer.letscareer.domain.price.entity.ChallengePrice;
import org.letscareer.letscareer.domain.price.type.ChallengePricePlanType;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.letscareer.letscareer.domain.application.entity.QChallengeApplication.challengeApplication;
import static org.letscareer.letscareer.domain.challengeoption.entity.QChallengeOption.challengeOption;
import static org.letscareer.letscareer.domain.challengeoption.entity.QChallengePriceOption.challengePriceOption;
import static org.letscareer.letscareer.domain.payment.entity.QPayment.payment;
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
                        challengePrice.description,
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
    public Optional<PriceDetailVo> findPriceDetailVoByChallengeId(Long programId, Long applicationId) {
        ChallengePricePlanType planType = jpaQueryFactory
                .select(payment.challengePricePlanType)
                .from(challengeApplication)
                .leftJoin(challengeApplication.payment, payment)
                .where(challengeApplication.id.eq(applicationId))
                .fetchOne();

        Tuple optionPriceTuple = jpaQueryFactory
                .select(
                        challengeOption.price.sum(),
                        challengeOption.discountPrice.sum()
                )
                .from(challengePrice)
                .leftJoin(challengePriceOption).on(challengePriceOption.challengePrice.id.eq(challengePrice.id))
                .leftJoin(challengeOption).on(challengeOption.id.eq(challengePriceOption.challengeOption.id))
                .where(
                        challengePrice.challenge.id.eq(programId),
                        challengePrice.challengePricePlanType.eq(planType)
                )
                .fetchOne();

        Integer optionPriceSum = optionPriceTuple != null ? optionPriceTuple.get(0, Integer.class) : 0;
        Integer optionDiscountSum = optionPriceTuple != null ? optionPriceTuple.get(1, Integer.class) : 0;

        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(PriceDetailVo.class,
                        challengePrice.id,
                        challengePrice.price,
                        challengePrice.discount,
                        challengePrice.refund,
                        Expressions.constant(optionPriceSum != null ? optionPriceSum : 0),            // option (정가 총합)
                        Expressions.constant(optionDiscountSum != null ? optionDiscountSum : 0)       // optionDiscount (할인 총합)
                ))
                .from(challengePrice)
                .where(
                        challengePrice.challenge.id.eq(programId),
                        challengePrice.challengePricePlanType.eq(planType)
                )
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
