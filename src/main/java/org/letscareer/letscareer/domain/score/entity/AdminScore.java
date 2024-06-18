package org.letscareer.letscareer.domain.score.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("admin_score")
@Getter
@Entity
public class AdminScore extends Score {
    private Integer score;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_application_id")
    private ChallengeApplication application;

    @Builder(access = AccessLevel.PRIVATE)
    public AdminScore(ChallengeApplication application) {
        super();
        this.score = 0;
        this.application = application;
    }

    public static AdminScore creatAdminScore(ChallengeApplication application) {
        AdminScore adminScore = AdminScore.builder()
                .application(application)
                .build();
        application.setAdminScore(adminScore);
        return adminScore;
    }

    public void UpdateAdminScore(Integer score) {
        this.score = score;
    }

}
