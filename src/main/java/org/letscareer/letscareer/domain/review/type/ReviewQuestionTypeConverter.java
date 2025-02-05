package org.letscareer.letscareer.domain.review.type;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

@Converter
public class ReviewQuestionTypeConverter extends AbstractEnumCodeAttributeConverter<ReviewQuestionType> {
    public ReviewQuestionTypeConverter() {
        super(ReviewQuestionType.class);
    }
}
