package org.letscareer.letscareer.global.common.utils.toss;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class TossSecretKeyGenerator {
    private final static String KEY_TYPE = "Basic ";

    public String generateSecretKey() {
        String widgetSecretKey = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes(StandardCharsets.UTF_8));
        return KEY_TYPE + new String(encodedBytes);
    }
}
