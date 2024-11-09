package org.letscareer.letscareer.domain.faq.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.faq.type.FaqProgramType;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;

import java.util.List;

import static org.letscareer.letscareer.domain.faq.entity.QFaq.faq;
import static org.letscareer.letscareer.domain.faq.entity.QFaqChallenge.faqChallenge;
import static org.letscareer.letscareer.domain.faq.entity.QFaqLive.faqLive;

@RequiredArgsConstructor
public class FaqQueryRepositoryImpl implements FaqQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<FaqDetailVo> findChallengeFaqDetailVos(Long challengeId) {
        return jpaQueryFactory
                .select(Projections.constructor(FaqDetailVo.class,
                        faq.id,
                        faq.question,
                        faq.answer,
                        faq.category,
                        faq.faqProgramType
                ))
                .from(faq)
                .leftJoin(faq.faqChallengeList, faqChallenge)
                .where(
                        eqChallengeId(challengeId)
                )
                .fetch();
    }

    @Override
    public List<FaqDetailVo> findLiveFaqDetailVos(Long liveId) {
        return jpaQueryFactory
                .select(Projections.constructor(FaqDetailVo.class,
                        faq.id,
                        faq.question,
                        faq.answer,
                        faq.category,
                        faq.faqProgramType
                ))
                .from(faq)
                .leftJoin(faq.faqLiveList, faqLive)
                .where(
                        eqLiveId(liveId)
                )
                .fetch();
    }

    @Override
    public List<FaqDetailVo> findFaqDetailVosForType(FaqProgramType type) {
        return jpaQueryFactory
                .select(Projections.constructor(FaqDetailVo.class,
                        faq.id,
                        faq.question,
                        faq.answer,
                        faq.category,
                        faq.faqProgramType
                ))
                .from(faq)
                .where(
                        eqFaqProgramType(type)
                )
                .fetch();
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? faqChallenge.challenge.id.eq(challengeId) : null;
    }

    private BooleanExpression eqLiveId(Long liveId) {
        return liveId != null ? faqLive.live.id.eq(liveId) : null;
    }

    private BooleanExpression eqFaqProgramType(FaqProgramType type) {
        return type != null ? faq.faqProgramType.eq(type) : null;
    }
}
