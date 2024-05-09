package org.letscareer.letscareer.domain.application.service;

import org.letscareer.letscareer.domain.program.type.ProgramType;

public interface ApplicationServiceFactory {
    ApplicationService getApplicationService(ProgramType programType);
}
