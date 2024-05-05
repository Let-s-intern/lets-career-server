package org.letscareer.letscareer.domain.faq.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "faq_challenge")
@Entity
public class FaqChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_challenge_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faq_id")
    private Faq faq;
}
