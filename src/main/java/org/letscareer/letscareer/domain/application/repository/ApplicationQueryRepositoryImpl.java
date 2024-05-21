package org.letscareer.letscareer.domain.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;

import java.util.List;

import static org.letscareer.letscareer.domain.application.entity.QVWApplication.vWApplication;

@RequiredArgsConstructor
public class ApplicationQueryRepositoryImpl implements ApplicationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MyApplicationVo> findMyApplications(Long userId) {
        return queryFactory.select(Projections.constructor(MyApplicationVo.class,
                        vWApplication.applicationId,
                        vWApplication.paymentIsConfirmed,
                        vWApplication.programTitle,
                        vWApplication.programStartDate,
                        vWApplication.programEndDate))
                .from(vWApplication)
                .where(
                        eqUserId(userId)
                )
                .fetch();
    }

    private BooleanExpression eqUserId(Long userId) {
        return vWApplication.userId.eq(userId);
    }
}
