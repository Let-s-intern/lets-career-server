package org.letscareer.letscareer.domain.application.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.type.converter.ProgramTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;

@Immutable
@Subselect(
        "SELECT a.application_id, a.is_canceled, a.user_id, " +
                "1 as program_type, ca.challenge_id as program_id, " +
                "c.title as program_title, c.short_desc as program_short_desc, c.thumbnail as program_thumbnail, c.start_date as program_start_date, c.end_date as program_end_date, " +
                "p.payment_id as payment_id, p.payment_key, p.program_price, p.create_date as payment_create_date, " +
                "r.review_id as review_id " +
                "FROM application as a " +
                "INNER JOIN challenge_application as ca ON ca.application_id = a.application_id " +
                "INNER JOIN challenge as c ON c.challenge_id = ca.challenge_id " +
                "INNER JOIN payment as p ON p.application_id = a.application_id " +
                "LEFT JOIN review as r ON r.application_id = a.application_id " +
                "UNION ALL " +
                "SELECT a.application_id, a.is_canceled, a.user_id, " +
                "2 as program_type, la.live_id as program_id, " +
                "l.title as program_title, l.short_desc as program_short_desc, l.thumbnail as program_thumbnail, l.start_date as program_start_date, l.end_date as program_end_date, " +
                "p.payment_id as payment_id, p.payment_key, p.program_price, p.create_date as payment_create_date, " +
                "r.review_id as review_id " +
                "FROM application as a " +
                "INNER JOIN live_application as la ON la.application_id = a.application_id " +
                "INNER JOIN live as l ON l.live_id = la.live_id " +
                "INNER JOIN payment as p ON p.application_id = a.application_id " +
                "LEFT JOIN review as r ON r.application_id = a.application_id " +
                "ORDER BY application_id DESC"
)
@Table(name = "vw_application")
@Entity
public class VWApplication extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isCanceled;
    private Long applicationId;
    private Long userId;

    private Long paymentId;
    private String paymentKey;
    private Integer programPrice;
    private LocalDateTime paymentCreateDate;

    @Convert(converter = ProgramTypeConverter.class)
    private ProgramType programType;
    private Long programId;
    private String programTitle;
    private String programShortDesc;
    private String programThumbnail;
    private LocalDateTime programStartDate;
    private LocalDateTime programEndDate;

    private Long reviewId;
}
