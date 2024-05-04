package org.letscareer.letscareer.global.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;

import java.util.EnumSet;

import static org.letscareer.letscareer.global.error.GlobalErrorCode.INVALID_ENUM_CODE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumValueUtils {
    public static <T extends Enum<T> & EnumField> Integer toDBCode(T enumClass) {
        if (enumClass == null) return -1;
        return enumClass.getCode();
    }

    public static <T extends Enum<T> & EnumField> T toEntityCode(Class<T> enumClass, Integer dbCode) {
        if (dbCode == -1) return null;
        return EnumSet.allOf(enumClass).stream()
                .filter(e -> e.getCode().equals(dbCode))
                .findAny()
                .orElseThrow(() -> new InvalidValueException(INVALID_ENUM_CODE));
    }
}


