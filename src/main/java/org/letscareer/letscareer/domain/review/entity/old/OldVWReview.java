package org.letscareer.letscareer.domain.review.entity.old;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.type.converter.ProgramTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;

@Immutable
@Subselect(
        "SELECT r.old_review_id as review_id, r.application_id, " +
                "ca.challenge_id as program_id, 1 AS program_type, ch.title as program_title, " +
                "u.name as user_name," +
                "u.user_id as user_id, " +
                "r.nps, r.nps_ans, r.nps_check_ans, r.content, r.program_detail, r.score, r.is_visible, r.create_date " +
                "FROM old_review as r " +
                "LEFT JOIN application as a ON r.application_id = a.application_id " +
                "LEFT JOIN challenge_application as ca ON ca.application_id = a.application_id " +
                "LEFT JOIN challenge as ch ON ca.challenge_id = ch.challenge_id " +
                "LEFT JOIN user as u ON a.user_id = u.user_id " +
                "WHERE r.application_id is NOT NULL AND a.dtype = 'challenge_application' " +

                "UNION ALL " +
                "SELECT r.old_review_id as review_id, r.application_id, " +
                "la.live_id as program_id, 2 AS program_type, li.title as program_title, " +
                "u.name as user_name, " +
                "u.user_id as user_id, " +
                "r.nps, r.nps_ans, r.nps_check_ans, r.content, r.program_detail, r.score, r.is_visible, r.create_date " +
                "FROM old_review as r " +
                "LEFT JOIN application as a ON r.application_id = a.application_id " +
                "LEFT JOIN live_application as la ON la.application_id = a.application_id " +
                "LEFT JOIN live as li ON li.live_id = la.live_id " +
                "LEFT JOIN user as u ON a.user_id = u.user_id " +
                "WHERE r.application_id is NOT NULL AND a.dtype = 'live_application' " +

                "UNION ALL " +
                "SELECT r.old_review_id as review_id, r.application_id, " +
                "re.report_id as program_id, 4 AS program_type, re.title as program_title, " +
                "u.name as user_name, " +
                "u.user_id as user_id, " +
                "r.nps, r.nps_ans, r.nps_check_ans, r.content, r.program_detail, r.score, r.is_visible, r.create_date " +
                "FROM old_review as r " +
                "LEFT JOIN application as a ON r.application_id = a.application_id " +
                "LEFT JOIN report_application as ra ON ra.application_id = a.application_id " +
                "LEFT JOIN report as re ON re.report_id = ra.report_id " +
                "LEFT JOIN user as u ON a.user_id = u.user_id " +
                "WHERE r.application_id is NOT NULL AND a.dtype = 'report_application' " +

                "UNION ALL " +
                "SELECT r.old_review_id as review_id, r.application_id, " +
                "ch.challenge_id as program_id, 1 AS program_type, ch.title as program_title, " +
                "'익명' as user_name, " +
                "null as user_id, " +
                "r.nps, r.nps_ans, r.nps_check_ans, r.content, r.program_detail, r.score, r.is_visible, r.create_date " +
                "FROM old_review as r " +
                "LEFT JOIN challenge as ch ON r.program_id = ch.challenge_id " +
                "WHERE r.application_id is NULL AND r.program_type is NOT NULL AND r.program_id is NOT NULL " +
                "AND r.program_type = 1 " +

                "UNION ALL " +
                "SELECT r.old_review_id as review_id, r.application_id, " +
                "li.live_id as program_id, 2 AS program_type, li.title as program_title, " +
                "'익명' as user_name, " +
                "null as user_id, " +
                "r.nps, r.nps_ans, r.nps_check_ans, r.content, r.program_detail, r.score, r.is_visible, r.create_date " +
                "FROM old_review as r " +
                "LEFT JOIN live as li ON r.program_id = li.live_id " +
                "WHERE r.application_id is NULL AND r.program_type is NOT NULL AND r.program_id is NOT NULL " +
                "AND r.program_type = 2 " +

                "UNION ALL " +
                "SELECT r.old_review_id as review_id, r.application_id, " +
                "re.report_id as program_id, 4 AS program_type, re.title as program_title, " +
                "'익명' as user_name, " +
                "null as user_id, " +
                "r.nps, r.nps_ans, r.nps_check_ans, r.content, r.program_detail, r.score, r.is_visible, r.create_date " +
                "FROM old_review as r " +
                "LEFT JOIN report as re ON r.program_id = re.report_id " +
                "WHERE r.application_id is NULL AND r.program_type is NOT NULL AND r.program_id is NOT NULL " +
                "AND r.program_type = 4 " +

                "ORDER BY review_id DESC"
)
@Table(name = "vw_review")
@Entity
public class OldVWReview extends BaseTimeEntity {
    @Id
    private Long reviewId;
    private Long applicationId;
    @Convert(converter = ProgramTypeConverter.class)
    private ProgramType programType;
    private Long programId;
    private String programTitle;
    private String userName;
    private Long userId;
    private Integer nps;
    private String npsAns;
    private Boolean npsCheckAns;
    private String content;
    private String programDetail;
    private Integer score;
    private Boolean isVisible;
    private LocalDateTime createDate;
}
