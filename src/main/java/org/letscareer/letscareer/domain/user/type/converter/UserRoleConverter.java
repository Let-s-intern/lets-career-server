package org.letscareer.letscareer.domain.user.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class UserRoleConverter extends AbstractEnumCodeAttributeConverter<UserRole> {
    public UserRoleConverter() {
        super(UserRole.class);
    }
}
