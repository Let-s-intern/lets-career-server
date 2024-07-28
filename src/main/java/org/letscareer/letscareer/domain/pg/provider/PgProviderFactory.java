package org.letscareer.letscareer.domain.pg.provider;

import org.letscareer.letscareer.domain.pg.type.PgType;

public interface PgProviderFactory {
    PgProvider getPgProvider(PgType pgType);
}
