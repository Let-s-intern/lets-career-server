package org.letscareer.letscareer.domain.application.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.type.ApplicationReviewStatus;
import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;
import org.letscareer.letscareer.domain.payment.vo.PaymentProgramVo;
import org.letscareer.letscareer.domain.program.vo.ProgramSimpleVo;

import java.time.LocalDateTime;
import java.util.List;

import static org.letscareer.letscareer.domain.application.entity.QVWApplication.vWApplication;

@RequiredArgsConstructor
public class ApplicationQueryRepositoryImpl implements ApplicationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MyApplicationVo> findMyApplications(Long userId, ApplicationStatus status) {
        return queryFactory
                .select(Projections.constructor(MyApplicationVo.class,
                        vWApplication.applicationId,
                        vWApplication.paymentIsConfirmed,
                        vWApplication.programId,
                        vWApplication.programType,
                        vWApplication.programTitle,
                        vWApplication.programShortDesc,
                        vWApplication.programThumbnail,
                        vWApplication.programStartDate,
                        vWApplication.programEndDate,
                        vWApplication.reviewId,
                        vWApplication.paymentId))
                .from(vWApplication)
                .where(
                        eqUserId(userId),
                        eqStatus(status),
                        checkEndDateOrIsConfirmed(),
                        eqPaymentIsRefunded(false)
                )
                .fetch();
    }

    @Override
    public List<PaymentProgramVo> findPaymentProgramVos(Long userId) {
        return queryFactory
                .select(Projections.constructor(PaymentProgramVo.class,
                        vWApplication.paymentId,
                        vWApplication.programTitle,
                        vWApplication.programThumbnail,
                        vWApplication.programPrice,
                        vWApplication.paymentKey,
                        vWApplication.paymentCreateDate
                ))
                .from(vWApplication)
                .where(
                        eqUserId(userId)
                )
                .orderBy(vWApplication.paymentCreateDate.desc())
                .fetch();
    }

    @Override
    public ProgramSimpleVo findVWApplicationProgramIdById(Long applicationId) {
        return queryFactory
                .select(Projections.constructor(ProgramSimpleVo.class,
                        vWApplication.programId,
                        vWApplication.programType))
                .from(vWApplication)
                .where(
                        eqApplicationId(applicationId)
                )
                .fetchFirst();
    }

    private BooleanExpression eqApplicationId(Long applicationId) {
        return applicationId != null ? vWApplication.applicationId.eq(applicationId) : null;
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
        if (status != null) {
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

    private BooleanExpression checkEndDateOrIsConfirmed() {
        return eqPaymentIsConfirmed(true).or(beforeEnd());
    }
}
