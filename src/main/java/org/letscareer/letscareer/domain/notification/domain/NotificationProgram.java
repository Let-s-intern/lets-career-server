package org.letscareer.letscareer.domain.notification.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "notification_program")
@Entity
public class NotificationProgram extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_program_id")
    private Long id;

    private ProgramType programType;

    private Long programId;

    @NotNull
    private String title;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @Builder.Default
    private Boolean isVisible = false;

    @OneToMany(mappedBy = "notificationProgram", cascade = CascadeType.ALL)
    @Builder.Default
    private List<NotificationProgramUser> notificationProgramUserList = new ArrayList<>();
}
