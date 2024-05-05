package org.letscareer.letscareer.domain.user.domain.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;
import org.letscareer.letscareer.domain.user.domain.AuthProvider;

@Converter
public class AuthProviderConverter extends AbstractEnumCodeAttributeConverter<AuthProvider> {
    public AuthProviderConverter() {
        super(AuthProvider.class);
    }
}
