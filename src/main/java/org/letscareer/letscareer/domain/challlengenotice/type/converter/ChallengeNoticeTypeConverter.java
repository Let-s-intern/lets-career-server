package org.letscareer.letscareer.domain.challlengenotice.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.challlengenotice.type.ChallengeNoticeType;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class ChallengeNoticeTypeConverter extends AbstractEnumCodeAttributeConverter<ChallengeNoticeType> {
    public ChallengeNoticeTypeConverter() {
        super(ChallengeNoticeType.class);
    }
}
