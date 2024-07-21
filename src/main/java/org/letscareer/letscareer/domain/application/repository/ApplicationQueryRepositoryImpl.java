package org.letscareer.letscareer.domain.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;
import org.letscareer.letscareer.domain.payment.vo.PaymentProgramVo;
import org.letscareer.letscareer.domain.program.vo.ProgramSimpleVo;
import org.letscareer.letscareer.domain.user.dto.response.UserApplicationInfo;

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
                        vWApplication.isCanceled,
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
                        eqIsCanceled(false),
                        eqStatus(status)
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
                        vWApplication.isCanceled,
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
    public List<UserApplicationInfo> findUserApplicationInfo(Long userId) {
        return queryFactory
                .select(Projections.constructor(UserApplicationInfo.class,
                        vWApplication.programId,
                        vWApplication.programTitle
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
                        vWApplication.applicationId,
                        vWApplication.programTitle,
                        vWApplication.programThumbnail,
                        vWApplication.programType,
                        vWApplication.progressType,
                        vWApplication.programStartDate,
                        vWApplication.programEndDate))
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

    private BooleanExpression eqIsCanceled(Boolean isCanceled) {
        return vWApplication.isCanceled.eq(isCanceled);
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
                    return beforeStart();
                }
                case IN_PROGRESS -> {
                    return beforeEnd();
                }
                case DONE -> {
                    return afterEnd();
                }
            }
        }
        return null;
    }
}
