package org.letscareer.letscareer.domain.user.domain.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.user.domain.UserGrade;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class UserGradeConverter extends AbstractEnumCodeAttributeConverter<UserGrade> {
    public UserGradeConverter() {
        super(UserGrade.class);
    }
}
