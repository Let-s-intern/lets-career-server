package org.letscareer.letscareer.domain.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;

import java.time.LocalDateTime;
import java.util.List;

import static org.letscareer.letscareer.domain.application.entity.QVWApplication.vWApplication;

@RequiredArgsConstructor
public class ApplicationQueryRepositoryImpl implements ApplicationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MyApplicationVo> findMyApplications(Long userId, ApplicationStatus status) {
        return queryFactory.select(Projections.constructor(MyApplicationVo.class,
                        vWApplication.applicationId,
                        vWApplication.paymentIsConfirmed,
                        vWApplication.programType,
                        vWApplication.programTitle,
                        vWApplication.programStartDate,
                        vWApplication.programEndDate))
                .from(vWApplication)
                .where(
                        eqUserId(userId),
                        eqPaymentIsRefunded(false),
                        eqStatus(status)
                )
                .fetch();
    }

    private BooleanExpression eqUserId(Long userId) {
        return vWApplication.userId.eq(userId);
    }

    private BooleanExpression eqPaymentIsConfirmed(Boolean isConfirmed) {
        return vWApplication.paymentIsConfirmed.eq(isConfirmed);
    }

    private BooleanExpression eqPaymentIsRefunded(Boolean isRefunded) {
        return vWApplication.paymentIsRefunded.eq(isRefunded);
    }

    private BooleanExpression beforeStart() {
        LocalDateTime now = LocalDateTime.now();
        return vWApplication.programStartDate.before(now);
    }

    private BooleanExpression beforeEnd() {
        LocalDateTime now = LocalDateTime.now();
        return vWApplication.programEndDate.after(now);
    }

    private BooleanExpression afterEnd() {
        LocalDateTime now = LocalDateTime.now();
        return vWApplication.programEndDate.before(now);
    }

    private BooleanExpression eqStatus(ApplicationStatus status) {
        if(status != null) {
            switch (status) {
                case WAITING -> {
                    return beforeStart().and(eqPaymentIsConfirmed(false));
                }
                case IN_PROGRESS -> {
                    return beforeEnd().and(eqPaymentIsConfirmed(true));
                }
                case DONE -> {
                    return afterEnd().and(eqPaymentIsConfirmed(true));
                }
            }
        }
        return null;
    }
}
