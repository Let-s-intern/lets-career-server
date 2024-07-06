package org.letscareer.letscareer.domain.user.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.global.common.utils.e.AbstractEnumCodeAttributeConverter;
import org.letscareer.letscareer.domain.user.type.AuthProvider;

@Converter
public class AuthProviderConverter extends AbstractEnumCodeAttributeConverter<AuthProvider> {
    public AuthProviderConverter() {
        super(AuthProvider.class);
    }
}
