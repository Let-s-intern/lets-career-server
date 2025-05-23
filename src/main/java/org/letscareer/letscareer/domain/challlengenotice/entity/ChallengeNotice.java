package org.letscareer.letscareer.domain.challlengenotice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challlengenotice.dto.request.CreateChallengeNoticeRequestDto;
import org.letscareer.letscareer.domain.challlengenotice.type.ChallengeNoticeType;
import org.letscareer.letscareer.domain.challlengenotice.type.converter.ChallengeNoticeTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "challenge_notice")
@Entity
public class ChallengeNotice extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_notice_id")
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String link;

    @Convert(converter = ChallengeNoticeTypeConverter.class)
    private ChallengeNoticeType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    public static ChallengeNotice createChallengeNotice(CreateChallengeNoticeRequestDto createChallengeNoticeRequestDto,
                                                        Challenge challenge) {
        return ChallengeNotice.builder()
                .title(createChallengeNoticeRequestDto.title())
                .link(createChallengeNoticeRequestDto.link())
                .type(createChallengeNoticeRequestDto.type())
                .challenge(challenge)
                .build();
    }

    public static ChallengeNotice copyChallengeNotice(Challenge challenge, ChallengeNotice challengeNotice) {
        return ChallengeNotice.builder()
                .title(challengeNotice.getTitle())
                .link(challengeNotice.getLink())
                .type(challengeNotice.getType())
                .challenge(challenge)
                .build();
    }

    public void updateChallengeNotice(CreateChallengeNoticeRequestDto updateChallengeNoticeRequestDto) {
        this.type = updateValue(this.type, updateChallengeNoticeRequestDto.type());
        this.title = updateValue(this.title, updateChallengeNoticeRequestDto.title());
        this.link = updateValue(this.link, updateChallengeNoticeRequestDto.link());
    }
}
