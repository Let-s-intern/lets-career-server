package org.letscareer.letscareer.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.challenge.type.converter.ChallengeTypeConverter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("challenge_review")
@Getter
@Entity
public class ChallengeReview extends Review {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @Convert(converter = ChallengeTypeConverter.class)
    private ChallengeType challengeType;
}
