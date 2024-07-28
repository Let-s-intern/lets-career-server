package org.letscareer.letscareer.global.common.utils.nhn;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class NhnSecretKeyReader {
    @Value("${nhn.appKey}")
    private String appKey;
    @Value("${nhn.secretKey}")
    private String secretKey;
    @Value("${nhn.sendKey}")
    private String sendKey;
    @Value("${nhn.templateCode}")
    private String templateCode;
}
