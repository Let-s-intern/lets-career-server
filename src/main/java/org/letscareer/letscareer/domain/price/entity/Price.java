package org.letscareer.letscareer.domain.price.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.price.type.AccountType;
import org.letscareer.letscareer.domain.price.type.ChallengePriceType;
import org.letscareer.letscareer.domain.price.type.converter.AccountTypeConverter;
import org.letscareer.letscareer.domain.price.type.converter.ChallengePriceTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@Getter
@Entity
public abstract class Price extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Long id;
    @Builder.Default
    private Integer price = 0;
    @Builder.Default
    private Integer discount = 0;
    private String accountNumber;
    private LocalDateTime deadline;
    @Convert(converter = AccountTypeConverter.class)
    private AccountType accountType;
}
