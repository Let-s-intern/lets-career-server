package org.letscareer.letscareer.domain.classification.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.letscareer.letscareer.domain.classification.dto.request.CreateClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.classification.type.converter.ProgramClassificationConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@Getter
@Entity
public abstract class Classification extends BaseTimeEntity {
    @Id
    @Column(name = "classification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = ProgramClassificationConverter.class)
    private ProgramClassification programClassification;

    public Classification(CreateClassificationRequestDto requestDto) {
        this.programClassification = requestDto.programClassification();
    }
}
