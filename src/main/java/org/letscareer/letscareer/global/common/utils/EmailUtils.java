package org.letscareer.letscareer.global.common.utils;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.live.type.ProgressType;
import org.letscareer.letscareer.domain.live.vo.LiveConfirmedEmailVo;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Locale;

@RequiredArgsConstructor
@Component
public class EmailUtils {
    private final JavaMailSender javaMailSender;
    private final MessageSource messageSource;

    public void sendPasswordResetEmail(String emailAddress, String randomPassword) {
        SimpleMailMessage mailMessage = createPasswordResetMessage(emailAddress, randomPassword);
        javaMailSender.send(mailMessage);
    }

    public void sendConfirmedEmail(String emailAddress, LiveConfirmedEmailVo liveConfirmedEmailVo) {
        SimpleMailMessage mailMessage = createConfirmedMessage(emailAddress, liveConfirmedEmailVo);
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

    private SimpleMailMessage createConfirmedMessage(String emailAddress, LiveConfirmedEmailVo liveConfirmedEmailVo) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject("[렛츠커리어] 참여 안내: " + liveConfirmedEmailVo.title());
        simpleMailMessage.setText(
                messageSource.getMessage("mail.header", null, Locale.KOREA) +
                "Live 클래스 [" + liveConfirmedEmailVo.title() + "]에 신청해주셔서 감사합니다!\n" +
                "Live 클래스 참여 확정되어 안내드립니다.\n\n" +
                createLiveDateInfo(liveConfirmedEmailVo) +
                createProgressTypeInfo(liveConfirmedEmailVo) +
                messageSource.getMessage("mail.live.cancel", null, Locale.KOREA) +
                messageSource.getMessage("mail.footer", null, Locale.KOREA));
        return simpleMailMessage;
    }

    private String createLiveDateInfo(LiveConfirmedEmailVo liveConfirmedEmailVo) {
        return "- 일시 : " + StringUtils.dateToString(liveConfirmedEmailVo.startDate()) + " ~ " + StringUtils.dateToString(liveConfirmedEmailVo.endDate()).substring(14) + "\n";
    }

    private String createProgressTypeInfo(LiveConfirmedEmailVo liveConfirmedEmailVo) {
        String wayInfo = "";

        if(liveConfirmedEmailVo.progressType().equals(ProgressType.ONLINE) || liveConfirmedEmailVo.progressType().equals(ProgressType.ALL)) {
            wayInfo += "- Zoom 링크: " + liveConfirmedEmailVo.zoomLink() + " \n" + "- Zoom 회의실 암호: " + liveConfirmedEmailVo.zoomPassword() + " \n";
        }

        if(liveConfirmedEmailVo.progressType().equals(ProgressType.OFFLINE) || liveConfirmedEmailVo.progressType().equals(ProgressType.ALL)) {
            wayInfo += "- 장소: " + liveConfirmedEmailVo.place() + "\n";
        }

        return wayInfo + "\n";
    }
}
