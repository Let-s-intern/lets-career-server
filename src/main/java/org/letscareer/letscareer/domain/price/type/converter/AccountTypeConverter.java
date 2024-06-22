package org.letscareer.letscareer.domain.price.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.price.type.AccountType;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class AccountTypeConverter extends AbstractEnumCodeAttributeConverter<AccountType> {
    public AccountTypeConverter() {
        super(AccountType.class);
    }
}