package org.letscareer.letscareer.global.common.utils.encoder;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.letscareer.letscareer.global.error.GlobalErrorCode.MISMATCH_PASSWORD;

@RequiredArgsConstructor
@Component
public class EncoderUtil {
    private final static String ENCRYPT_TYPE = "SHA-256";
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public String encodeUserData(User user) {
        String input = String.valueOf(user.getId());
        try {
            MessageDigest digest = MessageDigest.getInstance(ENCRYPT_TYPE);
            byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void validatePassword(User user, String inputPassword) {
        if (!passwordEncoder.matches(inputPassword, user.getPassword()))
            throw new InvalidValueException(MISMATCH_PASSWORD);
    }

    private String bytesToHex(byte[] bytes) {
        return IntStream.range(0, bytes.length)
                .mapToObj(i -> String.format("%02x", bytes[i] & 0xff))
                .collect(Collectors.joining());
    }
}
