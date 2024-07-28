package org.letscareer.letscareer.domain.user.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.user.type.AccountType;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class AccountTypeConverter extends AbstractEnumCodeAttributeConverter<AccountType> {
    public AccountTypeConverter() {
        super(AccountType.class);
    }
}
