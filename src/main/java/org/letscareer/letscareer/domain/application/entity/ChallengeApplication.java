package org.letscareer.letscareer.domain.application.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.score.entity.AdminScore;
import org.letscareer.letscareer.domain.user.entity.User;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("challenge_application")
@Getter
@Entity
public class ChallengeApplication extends Application {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "score_id")
    private AdminScore adminScore;

    @Builder(access = AccessLevel.PRIVATE)
    public ChallengeApplication(Challenge challenge, User user) {
        super(user);
        this.challenge = challenge;
    }

    public static ChallengeApplication createChallengeApplication(Challenge challenge, User user) {
        ChallengeApplication challengeApplication = ChallengeApplication.builder()
                .challenge(challenge)
                .user(user)
                .build();
        challenge.addChallengeApplicationList(challengeApplication);
        return challengeApplication;
    }

    public void setAdminScore(AdminScore adminScore) {
        this.adminScore = adminScore;
    }
}
