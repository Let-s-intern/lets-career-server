package org.letscareer.letscareer.domain.price.type.converter;

import jakarta.persistence.Convert;
import org.letscareer.letscareer.domain.price.type.ChallengeParticipationType;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Convert
public class ChallengeParticipationTypeConverter extends AbstractEnumCodeAttributeConverter<ChallengeParticipationType> {
    public ChallengeParticipationTypeConverter() {
        super(ChallengeParticipationType.class);
    }
}
