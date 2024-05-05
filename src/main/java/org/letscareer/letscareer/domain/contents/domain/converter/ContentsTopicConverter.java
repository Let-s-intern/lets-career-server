package org.letscareer.letscareer.domain.contents.domain.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.contents.domain.ContentsTopic;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class ContentsTopicConverter extends AbstractEnumCodeAttributeConverter<ContentsTopic> {
    public ContentsTopicConverter() {
        super(ContentsTopic.class);
    }
}
