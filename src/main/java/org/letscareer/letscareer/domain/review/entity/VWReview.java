package org.letscareer.letscareer.domain.review.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.challenge.type.converter.ChallengeTypeConverter;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.type.ReportTypeConverter;
import org.letscareer.letscareer.domain.review.type.ReviewProgramType;
import org.letscareer.letscareer.domain.review.type.ReviewProgramTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;

@Immutable
@Subselect(
        "SELECT r.review_id, r.application_id, r.good_point, r.bad_point, r.create_date, r.is_visible, " +
                "1 AS type, " +
                "ch.challenge_id as program_id, ch.title as program_title, ch.thumbnail as program_thumbnail, " +
                "ch.challenge_type, null as report_type, " +
                "0 as mission_id, null as mission_title, null as mission_th, null as attendance_review, " +
                "u.user_id as user_id, u.name as user_name, u.wish_job as user_wish_job, u.wish_company as user_wish_company " +
                "FROM review as r " +
                "LEFT JOIN application as a ON r.application_id = a.application_id " +
                "LEFT JOIN challenge_application as ca ON ca.application_id = a.application_id " +
                "LEFT JOIN challenge as ch ON ca.challenge_id = ch.challenge_id " +
                "LEFT JOIN user as u ON a.user_id = u.user_id " +
                "WHERE r.is_visible = true AND r.dtype = 'challenge_review' " +

                "UNION ALL " +
                "SELECT r.review_id, r.application_id, r.good_point, r.bad_point, r.create_date, r.is_visible, " +
                "2 AS type, " +
                "l.live_id as program_id, l.title as program_title, l.thumbnail as program_thumbnail, " +
                "null as challenge_type, null as report_type, " +
                "0 as mission_id, null as mission_title, null as mission_th, null as attendance_review, " +
                "u.user_id as user_id, u.name as user_name, u.wish_job as user_wish_job, u.wish_company as user_wish_company " +
                "FROM review as r " +
                "LEFT JOIN application as a ON r.application_id = a.application_id " +
                "LEFT JOIN live_application as la ON la.application_id = a.application_id " +
                "LEFT JOIN live as l ON la.live_id = l.live_id " +
                "LEFT JOIN user as u ON a.user_id = u.user_id " +
                "WHERE r.is_visible = true AND r.dtype = 'live_review' " +

                "UNION ALL " +
                "SELECT r.review_id, r.application_id, r.good_point, r.bad_point, r.create_date, r.is_visible, " +
                "4 AS type, " +
                "re.report_id as program_id, re.title as program_title, null as program_thumbnail, " +
                "null as challenge_type, re.type as report_type, " +
                "0 as mission_id, null as mission_title, null as mission_th, null as attendance_review, " +
                "u.user_id as user_id, u.name as user_name, u.wish_job as user_wish_job, u.wish_company as user_wish_company " +
                "FROM review as r " +
                "LEFT JOIN application as a ON r.application_id = a.application_id " +
                "LEFT JOIN report_application as ra ON ra.application_id = a.application_id " +
                "LEFT JOIN report as re ON ra.report_id = re.report_id " +
                "LEFT JOIN user as u ON a.user_id = u.user_id " +
                "WHERE r.is_visible = true AND r.dtype = 'report_review' " +

                "UNION ALL " +
                "SELECT att.attendance_id as review_id, 0, null, null, att.create_date, att.review_is_visible as is_visible, " +
                "5 AS type, " +
                "0 as program_id, null as program_title, null as program_thumbnail, " +
                "ch.challenge_type, null as report_type, " +
                "m.mission_id, m.title as mission_title, m.th as mission_th, att.review as attendance_review, " +
                "u.user_id as user_id, u.name as user_name, u.wish_job as user_wish_job, u.wish_company as user_wish_company " +
                "FROM attendance as att " +
                "LEFT JOIN mission as m ON att.mission_id = m.mission_id " +
                "LEFT JOIN challenge as ch ON m.challenge_id = ch.challenge_id " +
                "LEFT JOIN user as u ON att.user_id = u.user_id " +
                "WHERE att.review_is_visible = true " +

                "ORDER BY create_date DESC"
)
@Table(name = "vw_review")
@Entity
public class VWReview extends BaseTimeEntity {
    @Id
    private Long reviewId;
    private Long applicationId;
    private String goodPoint;
    private String badPoint;
    private LocalDateTime createDate;
    private Boolean isVisible;

    @Convert(converter = ReviewProgramTypeConverter.class)
    private ReviewProgramType type;
    private Long programId;
    private String programTitle;
    private String programThumbnail;

    @Convert(converter = ChallengeTypeConverter.class)
    private ChallengeType challengeType;

    @Convert(converter = ReportTypeConverter.class)
    private ReportType reportType;

    private Long missionId;
    private String missionTitle;
    private Integer missionTh;
    private String attendanceReview;

    private Long userId;
    private String userName;
    private String userWishJob;
    private String userWishCompany;
}
