package org.letscareer.letscareer.domain.application.service;

import org.letscareer.letscareer.domain.application.type.TossProgramType;

public interface TossApplicationServiceFactory {
    TossApplicationService getTossApplicationService(TossProgramType type);
}
