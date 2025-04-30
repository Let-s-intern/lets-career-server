package org.letscareer.letscareer.domain.challengeoption.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengeoption.vo.ChallengeOptionAdminVo;

import java.util.List;

import static org.letscareer.letscareer.domain.challengeoption.entity.QChallengeOption.challengeOption;

@RequiredArgsConstructor
public class ChallengePriceOptionQueryRepositoryImpl implements ChallengePriceOptionQueryRepository {
    private final JPAQueryFactory queryFactory;
}
