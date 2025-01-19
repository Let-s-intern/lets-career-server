package org.letscareer.letscareer.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.challenge.type.converter.ChallengeTypeConverter;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;

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

    @Builder(access = AccessLevel.PRIVATE)
    public ChallengeReview(Challenge challenge, ChallengeApplication challengeApplication, CreateReviewRequestDto requestDto) {
        super(challengeApplication, requestDto);
        this.challenge = challenge;
        this.challengeType = challenge.getChallengeType();
    }

    public static ChallengeReview createChallengeReview(Challenge challenge, ChallengeApplication challengeApplication, CreateReviewRequestDto requestDto) {
        ChallengeReview challengeReview = ChallengeReview.builder()
                .challenge(challenge)
                .challengeApplication(challengeApplication)
                .requestDto(requestDto)
                .build();
        challengeApplication.setReview(challengeReview);
        return challengeReview;
    }
}
