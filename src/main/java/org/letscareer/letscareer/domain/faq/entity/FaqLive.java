package org.letscareer.letscareer.domain.faq.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.live.entity.Live;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "faq_live")
@Entity
public class FaqLive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_live_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "live_id")
    private Live live;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faq_id")
    private Faq faq;

    public static FaqLive createFaqLive(Faq faq,
                                        Live live) {
        FaqLive faqChallenge = FaqLive.builder()
                .live(live)
                .faq(faq)
                .build();
        faq.addFaqChallengeList(faqChallenge);
        challenge.addChallengeFaqList(faqChallenge);
        return faqChallenge;
    }
}
