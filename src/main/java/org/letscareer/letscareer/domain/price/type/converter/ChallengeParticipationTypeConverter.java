package org.letscareer.letscareer.domain.price.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.price.type.ChallengeParticipationType;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class ChallengeParticipationTypeConverter extends AbstractEnumCodeAttributeConverter<ChallengeParticipationType> {
    public ChallengeParticipationTypeConverter() {
        super(ChallengeParticipationType.class);
    }
}
