package org.letscareer.letscareer.domain.challlengenotice.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challlengenotice.entity.ChallengeNotice;
import org.letscareer.letscareer.domain.challlengenotice.vo.ChallengeNoticeVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static org.letscareer.letscareer.domain.challlengenotice.entity.QChallengeNotice.challengeNotice;

@RequiredArgsConstructor
public class ChallengeNoticeQueryRepositoryImpl implements ChallengeNoticeQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ChallengeNoticeVo> findAllChallengeNoticeVos(Long challengeId, Pageable pageable) {
        List<ChallengeNoticeVo> challengeNoticeVoList = queryFactory
                .select(Projections.constructor(ChallengeNoticeVo.class,
                        challengeNotice.id,
                        challengeNotice.type,
                        challengeNotice.title,
                        challengeNotice.link,
                        challengeNotice.createDate))
                .from(challengeNotice)
                .where(
                        eqChallenge(challengeId)
                )
                .orderBy(challengeNotice.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        JPAQuery<ChallengeNotice> countQuery = queryFactory
                .selectFrom(challengeNotice)
                .where(
                        eqChallenge(challengeId)
                );

        return PageableExecutionUtils.getPage(challengeNoticeVoList, pageable, countQuery::fetchCount);
    }

    private BooleanExpression eqChallenge(Long challengeId) {
        return challengeId != null ? challengeNotice.challenge.id.eq(challengeId) : null;
    }
}
