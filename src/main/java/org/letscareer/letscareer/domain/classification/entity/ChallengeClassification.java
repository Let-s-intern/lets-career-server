package org.letscareer.letscareer.domain.classification.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.classification.dto.request.CreateChallengeClassificationRequestDto;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("challenge_classification")
@Getter
@Entity
public class ChallengeClassification extends Classification {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @Builder(access = AccessLevel.PRIVATE)
    public ChallengeClassification(CreateChallengeClassificationRequestDto requestDto,
                                   Challenge challenge) {
        super(requestDto.classificationInfo());
        challenge.addChallengeClassificationList(this);
    }

    public static ChallengeClassification createChallengeClassification(CreateChallengeClassificationRequestDto requestDto,
                                                                        Challenge challenge) {
        return ChallengeClassification.builder()
                .requestDto(requestDto)
                .challenge(challenge)
                .build();

    }
}
