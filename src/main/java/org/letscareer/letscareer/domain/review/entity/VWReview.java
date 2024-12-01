package org.letscareer.letscareer.domain.review.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.type.converter.ProgramTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;

@Immutable
@Subselect(
        "SELECT r.review_id, r.application_id, " +
                "ca.challenge_id as program_id, 1 AS program_type, ch.title as program_title, " +
                "u.name as user_name, " +
                "r.nps, r.nps_ans, r.nps_check_ans, r.content, r.score, r.is_visible, r.create_date " +
                "FROM review as r " +
                "LEFT JOIN application as a ON r.application_id = a.application_id " +
                "LEFT JOIN challenge_application as ca ON ca.application_id = a.application_id " +
                "LEFT JOIN challenge as ch ON ca.challenge_id = ch.challenge_id " +
                "LEFT JOIN user as u ON a.user_id = u.user_id " +
                "WHERE r.application_id is NOT NULL AND a.dtype = 'challenge_application' " +
                "UNION ALL " +
                "SELECT r.review_id, r.application_id, " +
                "la.live_id as program_id, 2 AS program_type, li.title as program_title, " +
                "u.name as user_name, " +
                "r.nps, r.nps_ans, r.nps_check_ans, r.content, r.score, r.is_visible, r.create_date " +
                "FROM review as r " +
                "LEFT JOIN application as a ON r.application_id = a.application_id " +
                "LEFT JOIN live_application as la ON la.application_id = a.application_id " +
                "LEFT JOIN live as li ON li.live_id = la.live_id " +
                "LEFT JOIN user as u ON a.user_id = u.user_id " +
                "WHERE r.application_id is NOT NULL AND a.dtype = 'live_application' " +
//                "UNION ALL " +
//                "SELECT r.review_id, r.application_id, " +
//                "r.program_id, r.program_type, '', " +
//                "'익명' as user_name, " +
//                "r.nps, r.nps_ans, r.nps_check_ans, r.content, r.score, r.is_visible, r.create_date " +
//                "FROM review as r " +
//                "WHERE r.application_id is NULL and r.program_id is NOT NULL and r.program_type is NOT NULL " +
//                "GROUP BY review_id " +
                "ORDER BY review_id DESC"
)
@Table(name = "vw_review")
@Entity
public class VWReview extends BaseTimeEntity {
    @Id
    private Long reviewId;
    private Long applicationId;
    @Convert(converter = ProgramTypeConverter.class)
    private ProgramType programType;
    private Long programId;
    private String programTitle;

    private String userName;
    private Integer nps;
    private String npsAns;
    private Boolean npsCheckAns;
    private String content;
    private Integer score;
    private Boolean isVisible;
    private LocalDateTime createDate;
}
