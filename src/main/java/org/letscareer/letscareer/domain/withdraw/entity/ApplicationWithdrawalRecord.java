package org.letscareer.letscareer.domain.withdraw.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "application_withdrawal_record")
@Entity
public class ApplicationWithdrawalRecord extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_witdrawal_record_id")
    private Long id;
    private Long userId;
    private Long challengeId;
    private Long liveId;
    private String name;
    private String email;
    private String phoneNum;
    private String inflowPath;
    private ProgramType programType;
    private String title;

    public static ApplicationWithdrawalRecord createApplicationWithdrawalRecord(Long programId, String title, ProgramType programType, User user) {
        return ApplicationWithdrawalRecord.builder()
                .userId(user.getId())
                .challengeId(programType.equals(ProgramType.CHALLENGE) ? programId : null)
                .liveId(programType.equals(ProgramType.LIVE) ? programId : null)
                .name(user.getName())
                .email(user.getEmail())
                .phoneNum(user.getPhoneNum())
                .inflowPath(user.getInflowPath())
                .programType(programType)
                .title(title)
                .build();
    }
}
