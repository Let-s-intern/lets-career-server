package org.letscareer.letscareer.domain.faq.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.faq.type.FaqProgramType;
import org.letscareer.letscareer.domain.faq.type.converter.FaqProgramTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

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
}
