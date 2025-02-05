package org.letscareer.letscareer.domain.admincalssification.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.admincalssification.request.CreateChallengeAdminClassificationRequestDto;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("challenge_admin_classification")
@Getter
@Entity
public class ChallengeAdminClassification extends AdminClassification {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @Builder(access = AccessLevel.PRIVATE)
    public ChallengeAdminClassification(CreateChallengeAdminClassificationRequestDto requestDto,
                                        Challenge challenge) {
        super(requestDto.classificationInfo());
        this.challenge = challenge;
        challenge.addChallengeAdminClassification(this);
    }

    public static ChallengeAdminClassification createChallengeAdminClassification(CreateChallengeAdminClassificationRequestDto requestDto,
                                                                                  Challenge challenge) {
        return ChallengeAdminClassification.builder()
                .requestDto(requestDto)
                .challenge(challenge)
                .build();

    }
}
