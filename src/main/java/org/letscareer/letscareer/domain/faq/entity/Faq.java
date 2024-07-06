package org.letscareer.letscareer.domain.faq.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.faq.dto.request.CreateFaqRequestDto;
import org.letscareer.letscareer.domain.faq.type.FaqProgramType;
import org.letscareer.letscareer.domain.faq.type.converter.FaqProgramTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "faq")
@Entity
public class Faq extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_id")
    private Long id;
    private String question;
    private String answer;
    @Convert(converter = FaqProgramTypeConverter.class)
    private FaqProgramType faqProgramType;

    @OneToMany(mappedBy = "faq", cascade = CascadeType.ALL)
    @Builder.Default
    private List<FaqChallenge> faqChallengeList = new ArrayList<>();
    @OneToMany(mappedBy = "faq", cascade = CascadeType.ALL)
    @Builder.Default
    private List<FaqLive> faqLiveList = new ArrayList<>();

    public static Faq createFaq(CreateFaqRequestDto requestDto) {
        return Faq.builder()
                .question(requestDto.question())
                .answer(requestDto.answer())
                .faqProgramType(requestDto.type())
                .build();
    }

    public void updateFaq(CreateFaqRequestDto requestDto) {
        this.question = updateValue(this.question, requestDto.question());
        this.answer = updateValue(this.answer, requestDto.answer());
        this.faqProgramType = updateValue(this.faqProgramType, requestDto.type());
    }

    public void addFaqChallengeList(FaqChallenge faqChallenge) {
        this.faqChallengeList.add(faqChallenge);
    }

    public void addFaqLiveList(FaqLive faqLive) {
        this.faqLiveList.add(faqLive);
    }
}
