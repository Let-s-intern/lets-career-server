package org.letscareer.letscareer.domain.notice.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.notice.type.NoticeType;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class NoticeTypeConverter extends AbstractEnumCodeAttributeConverter<NoticeType> {
    public NoticeTypeConverter() {
        super(NoticeType.class);
    }
}
