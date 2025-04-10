package org.letscareer.letscareer.domain.notification.domain;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "notification_program_user")
@Entity
public class NotificationProgramUser extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_program_user_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_program_id")
    private NotificationProgram notificationProgram;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_user_id")
    private NotificationUser notificationUser;
}
