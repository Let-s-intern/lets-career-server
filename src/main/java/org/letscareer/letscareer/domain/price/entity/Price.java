package org.letscareer.letscareer.domain.price.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.price.dto.request.CreatePriceRequestDto;
import org.letscareer.letscareer.domain.price.type.AccountType;
import org.letscareer.letscareer.domain.price.type.converter.AccountTypeConverter;
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
    private Integer price = 0;
    private Integer discount = 0;
    private String accountNumber;
    private LocalDateTime deadline;
    @Convert(converter = AccountTypeConverter.class)
    private AccountType accountType;

    public Price(CreatePriceRequestDto requestDto) {
        this.price = requestDto.price();
        this.discount = requestDto.discount();
        this.accountNumber = requestDto.accountNumber();
        this.deadline = requestDto.deadline();
        this.accountType = requestDto.accountType();
    }
}
