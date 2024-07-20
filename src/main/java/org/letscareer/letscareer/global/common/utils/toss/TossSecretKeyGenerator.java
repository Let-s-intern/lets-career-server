package org.letscareer.letscareer.global.common.utils.toss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class TossSecretKeyGenerator {
    private final static String KEY_TYPE = "Basic ";
    @Value("${toss.secretKey}")
    private String widgetSecretKey;

    public String generateSecretKey() {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes(StandardCharsets.UTF_8));
        return KEY_TYPE + new String(encodedBytes);
    }
}
