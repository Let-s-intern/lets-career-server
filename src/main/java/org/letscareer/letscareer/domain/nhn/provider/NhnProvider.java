package org.letscareer.letscareer.domain.nhn.provider;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.common.utils.nhn.NhnFeignController;
import org.letscareer.letscareer.global.common.utils.nhn.NhnSecretKeyReader;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NhnProvider {
    private final NhnFeignController nhnFeignController;
    private final NhnSecretKeyReader nhnSecretKeyReader;
}
