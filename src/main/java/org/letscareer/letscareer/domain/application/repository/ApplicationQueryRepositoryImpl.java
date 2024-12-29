package org.letscareer.letscareer.domain.application.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;
import org.letscareer.letscareer.domain.payment.vo.PaymentProgramVo;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.vo.ProgramSimpleVo;
import org.letscareer.letscareer.domain.user.dto.response.UserApplicationInfo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.letscareer.letscareer.domain.application.entity.QApplication.application;
import static org.letscareer.letscareer.domain.application.entity.QChallengeApplication.challengeApplication;
import static org.letscareer.letscareer.domain.application.entity.QLiveApplication.liveApplication;
import static org.letscareer.letscareer.domain.application.entity.QVWApplication.vWApplication;
import static org.letscareer.letscareer.domain.application.entity.report.QReportApplication.reportApplication;
import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.live.entity.QLive.live;
import static org.letscareer.letscareer.domain.payment.entity.QPayment.payment;
import static org.letscareer.letscareer.domain.report.entity.QReport.report;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class ApplicationQueryRepositoryImpl implements ApplicationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MyApplicationVo> findMyApplications(Long userId, ApplicationStatus status) {
        return queryFactory
                .select(Projections.constructor(MyApplicationVo.class,
                        vWApplication.applicationId,
                        vWApplication.paymentCreateDate,
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
                        neProgramType(ProgramType.REPORT),
                        eqStatus(status)
                )
                .fetch();
    }

    @Override
    public List<MyApplicationVo> findMyReviewApplications(Long userId, ApplicationStatus status) {
        return queryFactory
                .select(Projections.constructor(MyApplicationVo.class,
                        vWApplication.applicationId,
                        vWApplication.paymentCreateDate,
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

    private BooleanExpression neProgramType(ProgramType programType) {
        return vWApplication.programType.ne(programType);
    }

    @Override
    public List<PaymentProgramVo> findPaymentProgramVos(Long userId) {
        return queryFactory
                .select(Projections.constructor(PaymentProgramVo.class,
                        payment.id,
                        application.id,
                        programTypeEnumExpression(),
                        programTitleExpression(),
                        programThumbnailExpression(),
                        payment.programPrice,
                        payment.finalPrice,
                        payment.paymentKey,
                        application.isCanceled,
                        payment.isRefunded,
                        payment.createDate
                ))
                .from(application)
                .leftJoin(application.user, user)
                .leftJoin(application.payment, payment)
                .leftJoin(challengeApplication).on(application.id.eq(challengeApplication.id))
                .leftJoin(challengeApplication.challenge, challenge)
                .leftJoin(liveApplication).on(application.id.eq(liveApplication.id))
                .leftJoin(liveApplication.live, live)
                .leftJoin(reportApplication).on(application.id.eq(reportApplication.id))
                .leftJoin(reportApplication.report, report)
                .where(
                        eqUserEntityId(userId)
                )
                .orderBy(payment.createDate.desc())
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
                        vWApplication.isCanceled,
                        vWApplication.programStartDate,
                        vWApplication.programEndDate))
                .from(vWApplication)
                .where(
                        eqApplicationId(applicationId)
                )
                .fetchFirst();
    }

    private StringExpression programTypeEnumExpression() {
        return new CaseBuilder()
                .when(challenge.id.isNotNull()).then(ProgramType.CHALLENGE.name())
                .when(live.id.isNotNull()).then(ProgramType.LIVE.name())
                .when(report.id.isNotNull()).then(ProgramType.REPORT.name())
                .otherwise(ProgramType.VOD.name());
    }

    private StringExpression programTitleExpression() {
        return new CaseBuilder()
                .when(challenge.id.isNotNull()).then(challenge.title)
                .when(live.id.isNotNull()).then(live.title)
                .when(report.id.isNotNull()).then(report.title)
                .otherwise((String) null);
    }

    private StringExpression programThumbnailExpression() {
        return new CaseBuilder()
                .when(challenge.id.isNotNull()).then(challenge.thumbnail)
                .when(live.id.isNotNull()).then(live.thumbnail)
                .otherwise((String) null);
    }

    private BooleanExpression eqApplicationId(Long applicationId) {
        return applicationId != null ? vWApplication.applicationId.eq(applicationId) : null;
    }

    private BooleanExpression eqUserId(Long userId) {
        return vWApplication.userId.eq(userId);
    }

    private BooleanExpression eqUserEntityId(Long userId) {
        return user.id.eq(userId);
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
