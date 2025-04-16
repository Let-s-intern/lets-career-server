package org.letscareer.letscareer.domain.price.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challengeoption.entity.ChallengePriceOption;
import org.letscareer.letscareer.domain.price.dto.request.CreateChallengePriceRequestDto;
import org.letscareer.letscareer.domain.price.type.ChallengeParticipationType;
import org.letscareer.letscareer.domain.price.type.ChallengePricePlanType;
import org.letscareer.letscareer.domain.price.type.ChallengePriceType;
import org.letscareer.letscareer.domain.price.type.ChallengeUserType;
import org.letscareer.letscareer.domain.price.type.converter.ChallengeParticipationTypeConverter;
import org.letscareer.letscareer.domain.price.type.converter.ChallengePricePlanTypeConverter;
import org.letscareer.letscareer.domain.price.type.converter.ChallengePriceTypeConverter;
import org.letscareer.letscareer.domain.price.type.converter.ChallengeUserTypeConverter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("challenge_price")
@Getter
@Entity
public class ChallengePrice extends Price {
    private String title;
    private Integer charge = 0;
    private Integer refund = 0;
    @Convert(converter = ChallengeUserTypeConverter.class)
    private ChallengeUserType challengeUserType;
    @Convert(converter = ChallengePriceTypeConverter.class)
    private ChallengePriceType challengePriceType;
    @Convert(converter = ChallengePricePlanTypeConverter.class)
    private ChallengePricePlanType challengePricePlanType;
    @Convert(converter = ChallengeParticipationTypeConverter.class)
    private ChallengeParticipationType challengeParticipationType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;
    @OneToMany(mappedBy = "challengePrice", cascade = CascadeType.ALL)
    private List<ChallengePriceOption> challengePriceOptionList;

    @Builder(access = AccessLevel.PRIVATE)
    public ChallengePrice(CreateChallengePriceRequestDto requestDto,
                          Challenge challenge) {
        super(requestDto.priceInfo());
        this.challenge = challenge;
        this.title = requestDto.title();
        this.charge = requestDto.charge();
        this.refund = requestDto.refund();
        this.challengePriceType = requestDto.challengePriceType();
        this.challengePricePlanType = requestDto.challengePricePlanType();
        this.challengeParticipationType = requestDto.challengeParticipationType();
        this.challengePriceOptionList = new ArrayList<>();
        challenge.addChallengePriceList(this);
    }

    public static ChallengePrice createChallengePrice(CreateChallengePriceRequestDto requestDto,
                                                      Challenge challenge) {
        return ChallengePrice.builder()
                .requestDto(requestDto)
                .challenge(challenge)
                .build();
    }

    public void addChallengePriceOptionList(ChallengePriceOption challengePriceOption) {
        this.challengePriceOptionList.add(challengePriceOption);
    }
}
