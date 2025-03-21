package org.letscareer.letscareer.domain.price.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.price.dto.request.CreateChallengePriceRequestDto;
import org.letscareer.letscareer.domain.price.type.ChallengeParticipationType;
import org.letscareer.letscareer.domain.price.type.ChallengePriceType;
import org.letscareer.letscareer.domain.price.type.ChallengeUserType;
import org.letscareer.letscareer.domain.price.type.converter.ChallengeParticipationTypeConverter;
import org.letscareer.letscareer.domain.price.type.converter.ChallengePriceTypeConverter;
import org.letscareer.letscareer.domain.price.type.converter.ChallengeUserTypeConverter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("challenge_price")
@Getter
@Entity
public class ChallengePrice extends Price {
    private Integer charge = 0;
    private Integer refund = 0;
    @Convert(converter = ChallengePriceTypeConverter.class)
    private ChallengePriceType challengePriceType;
    @Convert(converter = ChallengeUserTypeConverter.class)
    private ChallengeUserType challengeUserType;
    @Convert(converter = ChallengeParticipationTypeConverter.class)
    private ChallengeParticipationType challengeParticipationType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @Builder(access = AccessLevel.PRIVATE)
    public ChallengePrice(CreateChallengePriceRequestDto requestDto,
                          Challenge challenge) {
        super(requestDto.priceInfo());
        this.challenge = challenge;
        this.charge = requestDto.charge();
        this.refund = requestDto.refund();
        this.challengePriceType = requestDto.challengePriceType();
        this.challengeUserType = requestDto.challengeUserType();
        this.challengeParticipationType = requestDto.challengeParticipationType();
        challenge.addChallengePriceList(this);
    }

    public static ChallengePrice createChallengePrice(CreateChallengePriceRequestDto requestDto,
                                                      Challenge challenge) {
        return ChallengePrice.builder()
                .requestDto(requestDto)
                .challenge(challenge)
                .build();
    }
}
