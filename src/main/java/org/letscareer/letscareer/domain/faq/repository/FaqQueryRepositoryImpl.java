package org.letscareer.letscareer.domain.faq.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;

import java.util.List;

import static org.letscareer.letscareer.domain.faq.entity.QFaq.faq;
import static org.letscareer.letscareer.domain.faq.entity.QFaqChallenge.faqChallenge;
import static org.letscareer.letscareer.domain.faq.entity.QFaqLive.faqLive;
import static org.letscareer.letscareer.domain.price.entity.QLivePrice.livePrice;

@RequiredArgsConstructor
public class FaqQueryRepositoryImpl implements FaqQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<FaqDetailVo> findChallengeFaqDetailVos(Long challengeId) {
        return jpaQueryFactory
                .select(Projections.constructor(FaqDetailVo.class,
                        faq.question,
                        faq.answer,
                        faq.faqProgramType
                ))
                .from(faq)
                .leftJoin(faq, faqChallenge.faq)
                .where(
                        neChallengeId(challengeId)
                )
                .fetch();
    }

    @Override
    public List<FaqDetailVo> findLiveFaqDetailVos(Long liveId) {
        return jpaQueryFactory
                .select(Projections.constructor(FaqDetailVo.class,
                        faq.question,
                        faq.answer,
                        faq.faqProgramType
                ))
                .from(faq)
                .leftJoin(faq, faqLive.faq)
                .where(
                        neLiveId(liveId)
                )
                .fetch();
    }

    private BooleanExpression neChallengeId(Long challengeId) {
        return challengeId != null ? faqChallenge.challenge.id.ne(challengeId) : null;
    }

    private BooleanExpression neLiveId(Long liveId) {
        return liveId != null ? faqLive.id.ne(liveId) : null;
    }
}
