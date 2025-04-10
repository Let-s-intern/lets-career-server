package org.letscareer.letscareer.domain.notification.domain;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "notification_user")
@Entity
public class NotificationUser extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_user_id")
    private Long id;

    private Long userId;

    private String name;

    private String phoneNum;

    // wish_job 다중 선택

    @OneToMany(mappedBy = "notificationUser", cascade = CascadeType.ALL)
    @Builder.Default
    private List<NotificationProgramUser> notificationProgramUserList = new ArrayList<>();
}
