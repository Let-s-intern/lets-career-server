package org.letscareer.letscareer.domain.curation.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.curation.repository.CurationRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CurationHelper {
    private final CurationRepository curationRepository;
}
