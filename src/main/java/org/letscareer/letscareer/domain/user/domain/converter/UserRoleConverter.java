package org.letscareer.letscareer.domain.user.domain.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.user.domain.UserRole;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class UserRoleConverter extends AbstractEnumCodeAttributeConverter<UserRole> {
    public UserRoleConverter() {
        super(UserRole.class);
    }
}
