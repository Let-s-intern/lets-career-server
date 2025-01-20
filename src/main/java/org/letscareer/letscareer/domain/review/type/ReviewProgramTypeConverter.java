package org.letscareer.letscareer.domain.review.type;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class ReviewProgramTypeConverter extends AbstractEnumCodeAttributeConverter<ReviewProgramType> {
    public ReviewProgramTypeConverter() {
        super(ReviewProgramType.class);
    }
}
