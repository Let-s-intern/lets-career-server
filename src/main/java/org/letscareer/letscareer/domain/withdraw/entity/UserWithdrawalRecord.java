package org.letscareer.letscareer.domain.withdraw.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "user_withdrawal_record")
@Entity
public class UserWithdrawalRecord extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_witdrawal_record_id")
    private Long id;
    private Long userId;
    private String name;
    private String email;
    private String phoneNum;
    private String inflowPath;

    public static UserWithdrawalRecord createUserWithdrawalRecord(User user) {
        return UserWithdrawalRecord.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNum(user.getPhoneNum())
                .inflowPath(user.getInflowPath())
                .build();
    }
}
