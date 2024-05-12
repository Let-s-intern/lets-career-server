package org.letscareer.letscareer.domain.challengeguide.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challengeguide.dto.request.CreateChallengeGuideRequestDto;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "challenge_guide")
@Entity
public class ChallengeGuide extends BaseTimeEntity {

    @Id
    @Column(name = "challenge_guide_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    public static ChallengeGuide of(Challenge challenge, CreateChallengeGuideRequestDto createChallengeGuideRequestDto) {
        return ChallengeGuide.builder()
                .title(createChallengeGuideRequestDto.title())
                .link(createChallengeGuideRequestDto.link())
                .challenge(challenge)
                .build();
    }
}
