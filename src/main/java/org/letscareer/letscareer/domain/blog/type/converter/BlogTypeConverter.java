package org.letscareer.letscareer.domain.blog.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.blog.type.BlogType;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class BlogTypeConverter extends AbstractEnumCodeAttributeConverter<BlogType> {
    public BlogTypeConverter() {
        super(BlogType.class);
    }
}
