package org.letscareer.letscareer.domain.contents.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.contents.type.ContentsTopic;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class ContentsTopicConverter extends AbstractEnumCodeAttributeConverter<ContentsTopic> {
    public ContentsTopicConverter() {
        super(ContentsTopic.class);
    }
}
