package org.letscareer.letscareer.domain.challengementor.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.user.entity.User;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "challenge_mentor")
@Entity
public class ChallengeMentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_mentor_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    private User mentor;

    public static ChallengeMentor createChallengeMentor(Challenge challenge, User mentor) {
        return ChallengeMentor.builder()
                .challenge(challenge)
                .mentor(mentor)
                .build();
    }
}
