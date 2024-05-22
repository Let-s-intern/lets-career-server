package org.letscareer.letscareer.domain.application.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.review.entity.Review;
import org.letscareer.letscareer.domain.user.entity.User;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("challenge_application")
@Getter
@Entity
public class ChallengeApplication extends Application {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @Builder(access = AccessLevel.PRIVATE)
    public ChallengeApplication(Challenge challenge, User user) {
        super(user);
        this.challenge = challenge;
    }

    public static ChallengeApplication createChallengeApplication(Challenge challenge, User user) {
        return ChallengeApplication.builder()
                .challenge(challenge)
                .user(user)
                .build();
    }
}
