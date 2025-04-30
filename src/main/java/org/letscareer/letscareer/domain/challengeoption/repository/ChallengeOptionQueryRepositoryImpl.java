package org.letscareer.letscareer.domain.challengeoption.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengeoption.vo.ChallengeOptionAdminVo;

import java.util.List;

import static org.letscareer.letscareer.domain.challengeoption.entity.QChallengeOption.challengeOption;

@RequiredArgsConstructor
public class ChallengeOptionQueryRepositoryImpl implements ChallengeOptionQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ChallengeOptionAdminVo> findAllChallengeOptionAdminVos() {
        return queryFactory
                .select(Projections.constructor(ChallengeOptionAdminVo.class,
                        challengeOption.id,
                        challengeOption.title,
                        challengeOption.code,
                        challengeOption.price,
                        challengeOption.discountPrice)
                )
                .from(challengeOption)
                .orderBy(
                        challengeOption.id.desc()
                )
                .fetch();
    }
}
