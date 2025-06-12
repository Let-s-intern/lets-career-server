package org.letscareer.letscareer.domain.attendance.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;
import org.letscareer.letscareer.domain.attendance.vo.*;
import org.letscareer.letscareer.domain.user.entity.QUser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.letscareer.letscareer.domain.application.entity.QChallengeApplication.challengeApplication;
import static org.letscareer.letscareer.domain.attendance.entity.QAttendance.attendance;
import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.challengeoption.entity.QChallengeOption.challengeOption;
import static org.letscareer.letscareer.domain.challengeoption.entity.QChallengePriceOption.challengePriceOption;
import static org.letscareer.letscareer.domain.mission.entity.QMission.mission;
import static org.letscareer.letscareer.domain.payment.entity.QPayment.payment;
import static org.letscareer.letscareer.domain.price.entity.QChallengePrice.challengePrice;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;
import static org.letscareer.letscareer.domain.user.repository.UserQueryRepositoryImpl.activeEmail;

@RequiredArgsConstructor
public class AttendanceQueryRepositoryImpl implements AttendanceQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AttendanceAdminVo> findAllAttendanceByChallengeId(Long challengeId) {
        return queryFactory
                .select(Projections.constructor(AttendanceAdminVo.class,
                        attendance.id,
                        attendance.user.name,
                        activeEmail(attendance.user),
                        attendance.user.accountType,
                        attendance.user.accountNum,
                        attendance.status,
                        attendance.link,
                        attendance.result,
                        attendance.comments))
                .from(attendance)
                .where(
                        eqChallengeId(challengeId)
                )
                .orderBy(
                        resultOrder().asc(),
                        attendance.id.desc()
                )
                .fetch();
    }

    @Override
    public List<MissionScoreVo> findAttendanceScoreVos(Long applicationId, Long challengeId) {
        return queryFactory
                .select(Projections.constructor(MissionScoreVo.class,
                        mission.id,
                        mission.th
                ))
                .from(mission)
                .leftJoin(mission.challenge, challenge)
                .where(
                        eqChallengeId(challengeId)
                )
                .orderBy(mission.th.asc())
                .fetch();
    }

    @Override
    public List<MissionAttendanceWithOptionsVo> findMissionAttendanceVos(Long challengeId, Long missionId) {
        List<Tuple> tuples = queryFactory
                .select(
                        Projections.constructor(MissionAttendanceVo.class,
                                attendance.id,
                                attendance.user.name,
                                activeEmail(attendance.user),
                                attendance.status,
                                attendance.link,
                                attendance.result,
                                attendance.comments,
                                attendance.createDate,
                                attendance.lastModifiedDate,
                                attendance.review,
                                attendance.reviewIsVisible,
                                payment.challengePricePlanType
                        ),
                        challengeOption.code
                )
                .from(attendance)
                .leftJoin(attendance.mission, mission)
                .leftJoin(mission.challenge, challenge)
                .leftJoin(attendance.user, user)

                // ChallengeApplication 조인 (userId + challengeId 기준)
                .leftJoin(challengeApplication)
                .on(challengeApplication.user.id.eq(user.id)
                        .and(challengeApplication.challenge.id.eq(challenge.id)))

                // ChallengeApplication → Payment
                .leftJoin(challengeApplication.payment, payment)

                // ChallengePrice: challengeId + planType
                .leftJoin(challengePrice)
                .on(challengePrice.challenge.id.eq(challenge.id)
                        .and(challengePrice.challengePricePlanType.eq(payment.challengePricePlanType)))

                // 옵션 조인
                .leftJoin(challengePriceOption).on(challengePriceOption.challengePrice.id.eq(challengePrice.id))
                .leftJoin(challengeOption).on(challengeOption.id.eq(challengePriceOption.challengeOption.id))

                .where(
                        eqChallengeId(challengeId),
                        eqMissionId(missionId),
                        eqChallengeApplicationIsCanceled(false)
                )
                .orderBy(
                        resultOrder().asc(),
                        attendance.id.desc()
                )
                .fetch();

        Map<MissionAttendanceVo, List<String>> grouped = new LinkedHashMap<>();

        for (Tuple tuple : tuples) {
            MissionAttendanceVo vo = tuple.get(0, MissionAttendanceVo.class);
            String optionCode = tuple.get(1, String.class);

            grouped.computeIfAbsent(vo, k -> new ArrayList<>());
            if (optionCode != null) {
                grouped.get(vo).add(optionCode);
            }
        }

        return grouped.entrySet().stream()
                .map(entry -> new MissionAttendanceWithOptionsVo(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackMissionAttendanceVo> findFeedbackMissionAttendanceVos(Long challengeId, Long missionId, Long challengeOptionId) {
        QUser mentor = new QUser("mentor");
        return queryFactory
                .select(Projections.constructor(FeedbackMissionAttendanceVo.class,
                        attendance.id,
                        mentor.name,
                        user.name,
                        user.major,
                        user.wishJob,
                        user.wishCompany,
                        attendance.link,
                        attendance.status,
                        attendance.result,
                        payment.challengePricePlanType))
                .from(attendance)
                .leftJoin(attendance.user, user)
                .leftJoin(attendance.mentor, mentor)
                .leftJoin(attendance.mission, mission)
                .join(mission.challenge, challenge)
                .join(challengeApplication)
                    .on(challengeApplication.user.id.eq(user.id).and(challengeApplication.challenge.id.eq(challenge.id)))
                .join(challengeApplication.payment, payment)
                .join(challengePrice)
                .on(challengePrice.challenge.id.eq(challenge.id).and(challengePrice.challengePricePlanType.eq(payment.challengePricePlanType)))
                .join(challengePrice.challengePriceOptionList, challengePriceOption)
                .join(challengePriceOption.challengeOption, challengeOption)
                .where(
                        eqChallengeId(challengeId),
                        eqMissionId(missionId),
                        eqAttendanceStatus(AttendanceStatus.PRESENT),
                        eqChallengeApplicationIsCanceled(false),
                        eqChallengeOptionId(challengeOptionId),
                        eqChallengeOptionIsFeedback(true)
                )
                .distinct()
                .fetch();
    }

    @Override
    public AttendanceDashboardVo findAttendanceDashboardVo(Long missionId, Long userId) {
        return queryFactory
                .select(Projections.constructor(AttendanceDashboardVo.class,
                        attendance))
                .from(attendance)
                .leftJoin(attendance.mission, mission)
                .leftJoin(attendance.user, user)
                .where(
                        eqMissionId(missionId),
                        eqUserId(userId)
                )
                .fetchFirst();
    }

    @Override
    public List<MissionReviewAdminVo> findAllMissionReviewAdminVos() {
        return queryFactory
                .select(Projections.constructor(MissionReviewAdminVo.class,
                        challenge.id,
                        attendance.id,
                        attendance.createDate,
                        challenge.challengeType,
                        challenge.title,
                        mission.th,
                        mission.title,
                        user.name,
                        attendance.review,
                        attendance.reviewIsVisible))
                .from(attendance)
                .leftJoin(attendance.mission, mission)
                .leftJoin(mission.challenge, challenge)
                .leftJoin(attendance.user, user)
                .where(attendance.review.isNotEmpty())
                .orderBy(attendance.createDate.desc())
                .fetch();
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ? attendance.user.id.eq(userId) : null;
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }

    private BooleanExpression eqChallengeApplicationId(Long applicationId) {
        return applicationId != null ? challengeApplication._super.id.eq(applicationId) : null;
    }

    private BooleanExpression eqChallengeApplicationIsCanceled(Boolean isCanceled) {
        return isCanceled != null ? challengeApplication.isCanceled.eq(isCanceled) : null;
    }

    private BooleanExpression eqMissionId(Long missionId) {
        return missionId != null ? mission.id.eq(missionId) : null;
    }

    private BooleanExpression eqChallengeOptionId(Long challengeOptionId) {
        return challengeOptionId != null ? challengeOption.id.eq(challengeOptionId) : null;
    }

    private BooleanExpression eqChallengeOptionIsFeedback(Boolean isFeedback) {
        return isFeedback != null ? challengeOption.isFeedback.eq(isFeedback) : null;
    }

    private BooleanExpression eqAttendanceStatus(AttendanceStatus attendanceStatus) {
        return attendanceStatus != null ? attendance.status.eq(attendanceStatus) : null;
    }

    private NumberExpression<Integer> resultOrder() {
        return new CaseBuilder()
                .when(attendance.result.eq(AttendanceResult.WAITING)).then(0)
                .when(attendance.result.eq(AttendanceResult.WRONG)).then(1)
                .otherwise(2);
    }
}