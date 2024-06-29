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
        "SELECT r.review_id as id, r.application_id, r.program_id, " +
                "CASE WHEN a.dtype = 'challenge_application' then 1 WHEN a.dtype = 'live_application' then 2 END AS program_type, " +
                "u.name as user_name, " +
                "r.nps, r.nps_ans, r.nps_check_ans, r.content, r.score, r.is_visible, r.create_date " +
                "FROM review as r " +
                "LEFT JOIN application as a ON r.application_id = a.application_id " +
                "LEFT JOIN user as u ON a.user_id = u.user_id " +
                "WHERE r.application_id is NOT NULL " +
                "UNION ALL " +
                "SELECT r.review_id as id, r.application_id, r.program_id, " +
                "r.program_type, " +
                "null as user_name, " +
                "r.nps, r.nps_ans, r.nps_check_ans, r.content, r.score, r.is_visible, r.create_date " +
                "FROM review as r " +
                "WHERE r.application_id is NULL and r.program_type is NOT NULL " +
                "ORDER BY id DESC"
)
@Table(name = "vw_review")
@Entity
public class VWReview extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long applicationId;
    @Convert(converter = ProgramTypeConverter.class)
    private ProgramType programType;
    private Long programId;

    private String userName;
    private Integer nps;
    private String npsAns;
    private Boolean npsCheckAns;
    private String content;
    private Integer score;
    private Boolean isVisible;
    private LocalDateTime createDate;
}
