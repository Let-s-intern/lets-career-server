package org.letscareer.letscareer.domain.live.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.live.type.MailStatus;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class MailStatusConverter extends AbstractEnumCodeAttributeConverter<MailStatus> {
    public MailStatusConverter() {
        super(MailStatus.class);
    }
}
