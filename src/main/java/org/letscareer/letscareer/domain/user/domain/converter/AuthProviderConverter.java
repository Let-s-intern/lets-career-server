package org.letscareer.letscareer.domain.user.domain.converter;

import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;
import org.letscareer.letscareer.domain.user.domain.AuthProvider;

public class AuthProviderConverter extends AbstractEnumCodeAttributeConverter<AuthProvider> {
    public AuthProviderConverter() {
        super(AuthProvider.class);
    }
}
