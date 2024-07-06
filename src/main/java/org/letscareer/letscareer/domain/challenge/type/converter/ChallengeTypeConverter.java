package org.letscareer.letscareer.domain.challenge.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class ChallengeTypeConverter extends AbstractEnumCodeAttributeConverter<ChallengeType> {
    public ChallengeTypeConverter() {
        super(ChallengeType.class);
    }
}
