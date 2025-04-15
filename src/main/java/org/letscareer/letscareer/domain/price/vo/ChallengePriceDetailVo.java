package org.letscareer.letscareer.domain.price.vo;

import lombok.Getter;
import org.letscareer.letscareer.domain.challengeoption.vo.ChallengeOptionVo;
import org.letscareer.letscareer.domain.price.type.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ChallengePriceDetailVo {
    private Long priceId;
    private Integer price;
    private Integer refund;
    private Integer discount;
    private String accountNumber;
    private LocalDateTime deadline;
    private AccountType accountType;
    private ChallengePriceType challengePriceType;
    private ChallengePricePlanType challengePricePlanType;
    private ChallengeParticipationType challengeParticipationType;

    private List<ChallengeOptionVo> challengeOptionList;

    public ChallengePriceDetailVo(Long priceId, Integer price, Integer refund, Integer discount,
                                  String accountNumber, LocalDateTime deadline,
                                  AccountType accountType, ChallengePriceType challengePriceType,
                                  ChallengePricePlanType challengePricePlanType,
                                  ChallengeParticipationType challengeParticipationType) {
        this.priceId = priceId;
        this.price = price;
        this.refund = refund;
        this.discount = discount;
        this.accountNumber = accountNumber;
        this.deadline = deadline;
        this.accountType = accountType;
        this.challengePriceType = challengePriceType;
        this.challengePricePlanType = challengePricePlanType;
        this.challengeParticipationType = challengeParticipationType;
    }

    public void setChallengeOptionList(List<ChallengeOptionVo> challengeOptionList) {
        this.challengeOptionList = challengeOptionList;
    }
}

