package org.letscareer.letscareer.domain.user.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.user.type.UserGrade;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class UserGradeConverter extends AbstractEnumCodeAttributeConverter<UserGrade> {
    public UserGradeConverter() {
        super(UserGrade.class);
    }
}
