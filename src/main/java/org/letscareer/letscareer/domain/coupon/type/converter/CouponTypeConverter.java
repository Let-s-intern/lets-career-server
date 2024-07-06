package org.letscareer.letscareer.domain.coupon.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.coupon.type.CouponType;
import org.letscareer.letscareer.global.common.utils.e.AbstractEnumCodeAttributeConverter;

@Converter
public class CouponTypeConverter extends AbstractEnumCodeAttributeConverter<CouponType> {
    public CouponTypeConverter() {
        super(CouponType.class);
    }
}

