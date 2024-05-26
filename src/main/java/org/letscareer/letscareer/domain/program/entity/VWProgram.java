package org.letscareer.letscareer.domain.program.entity;

import com.amazonaws.annotation.Immutable;
import jakarta.persistence.*;
import org.hibernate.annotations.Subselect;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.type.converter.ProgramTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;

@Immutable
@Subselect(
        "SELECT live_id as program_id, " +
                "2 as program_type, title, short_desc, thumbnail, current_count, participation_count, start_date, end_date, deadline, is_visible, zoom_link, zoom_password, create_date " +
                "FROM live " +
                "UNION ALL " +
                "SELECT challenge_id as program_id, " +
                "1 as program_type, title, short_desc, thumbnail, current_count, participation_count, start_date, end_date, deadline, is_visible, zoom_link, zoom_password, create_date " +
                "FROM challenge " +
                "UNION ALL " +
                "SELECT vod_id as program_id, " +
                "3 as program_type, title, short_desc, thumbnail, null as current_count, null participation_count, null as start_date, null as end_date, null as deadline, is_visible, null as zoom_link, null as zoom_password, create_date " +
                "FROM vod"
)
@Table(name = "vw_program")
@Entity
public class VWProgram extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long programId;
    @Convert(converter = ProgramTypeConverter.class)
    private ProgramType programType;
    private String title;
    private String shortDesc;
    private String thumbnail;
    private Integer currentCount;
    private Integer participationCount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime deadline;
    private Boolean isVisible;
    private String zoomLink;
    private String zoomPassword;
    private LocalDateTime createDate;
}
