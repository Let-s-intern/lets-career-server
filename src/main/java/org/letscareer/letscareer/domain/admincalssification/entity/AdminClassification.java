package org.letscareer.letscareer.domain.admincalssification.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.letscareer.letscareer.domain.admincalssification.request.CreateAdminClassificationRequestDto;
import org.letscareer.letscareer.domain.admincalssification.type.ProgramAdminClassification;
import org.letscareer.letscareer.domain.admincalssification.type.converter.ProgramAdminClassificationConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@Getter
@Entity
public abstract class AdminClassification extends BaseTimeEntity {
    @Id
    @Column(name = "admin_classification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = ProgramAdminClassificationConverter.class)
    private ProgramAdminClassification programAdminClassification;

    public AdminClassification(CreateAdminClassificationRequestDto requestDto) {
        this.programAdminClassification = requestDto.programAdminClassification();
    }
}
