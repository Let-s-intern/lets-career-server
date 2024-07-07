package org.letscareer.letscareer.domain.price.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.price.type.ChallengeUserType;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class ChallengeUserTypeConverter extends AbstractEnumCodeAttributeConverter<ChallengeUserType> {
    public ChallengeUserTypeConverter() {
        super(ChallengeUserType.class);
    }
}
