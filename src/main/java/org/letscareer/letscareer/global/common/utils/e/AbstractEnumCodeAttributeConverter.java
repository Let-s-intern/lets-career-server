package org.letscareer.letscareer.global.common.utils.e;

import jakarta.persistence.AttributeConverter;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.e.EnumField;
import org.letscareer.letscareer.global.common.utils.e.EnumValueUtils;

@Getter
public class AbstractEnumCodeAttributeConverter<T extends Enum<T> & EnumField> implements AttributeConverter<T, Integer> {
    private Class<T> targetEnumClass;

    public AbstractEnumCodeAttributeConverter(Class<T> targetEnumClass) {
        this.targetEnumClass = targetEnumClass;
    }

    @Override
    public Integer convertToDatabaseColumn(T attribute) {
        return EnumValueUtils.toDBCode(attribute);
    }

    @Override
    public T convertToEntityAttribute(Integer dbData) {
        return EnumValueUtils.toEntityCode(targetEnumClass, dbData);
    }
}

