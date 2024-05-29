package org.letscareer.letscareer.global.common.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailUtils {
    private final JavaMailSender javaMailSender;
    private final MessageSource messageSource;

    public void sendPasswordResetEmail(String emailAddress, String randomPassword) {
        SimpleMailMessage mailMessage = createPasswordResetMessage(emailAddress, randomPassword);
        javaMailSender.send(mailMessage);
    }

    private SimpleMailMessage createPasswordResetMessage(String emailAddress, String randomPassword) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject("[렛츠커리어] 비밀번호 재설정 메일입니다.");
        simpleMailMessage.setText("안녕하세요, 렛츠커리어입니다.\n\n" +
                "비밀번호 재설정을 위한 임시 비밀번호는 아래와 같습니다.\n" +
                "해당 비밀번호로 로그인 후, 마이페이지에서 비밀번호를 재설정해주시길 바랍니다.\n감사합니다.\n\n" +
                "임시 비밀번호 : " + randomPassword);

        return simpleMailMessage;
    }
}
