package org.letscareer.letscareer.global.common.utils;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.live.type.ProgressType;
import org.letscareer.letscareer.domain.live.vo.LiveEmailVo;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public void sendConfirmedEmail(String emailAddress, LiveEmailVo liveEmailVo) {
        SimpleMailMessage mailMessage = createConfirmedMessage(emailAddress, liveEmailVo);
        javaMailSender.send(mailMessage);
    }

    public void sendRemindMail(List<String> emailAddressList, LiveEmailVo liveEmailVo) {
        SimpleMailMessage mailMessage = createRemindMessage(emailAddressList, liveEmailVo);
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

    private SimpleMailMessage createConfirmedMessage(String emailAddress, LiveEmailVo liveEmailVo) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject("[렛츠커리어] 참여 안내: " + liveEmailVo.title());
        simpleMailMessage.setText(
                messageSource.getMessage("mail.header", null, Locale.KOREA) +
                "Live 클래스 [" + liveEmailVo.title() + "]에 신청해주셔서 감사합니다!\n" +
                "Live 클래스 참여 확정되어 안내드립니다.\n\n" +
                createLiveDateInfo(liveEmailVo) +
                createProgressTypeInfo(liveEmailVo) +
                messageSource.getMessage("mail.live.cancel", null, Locale.KOREA) +
                messageSource.getMessage("mail.footer", null, Locale.KOREA));
        return simpleMailMessage;
    }

    private SimpleMailMessage createRemindMessage(List<String> emailAddressList, LiveEmailVo liveEmailVo) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setBcc(emailAddressList.toArray(String[]::new));
        simpleMailMessage.setSubject("[렛츠커리어] D-day 안내: " + liveEmailVo.title());
        simpleMailMessage.setText(
                messageSource.getMessage("mail.header", null, Locale.KOREA) +
                        "오늘은 신청해주신 Live 클래스 [" + liveEmailVo.title() + "]이 예정되어있는 날입니다.\n" +
                        "다들 잊지 않으셨죠? 아래 일정 확인하시어, 원활한 세션 진행을 위해 5분 전 입장 부탁드립니다.\n\n" +
                        createLiveDateInfo(liveEmailVo) +
                        createProgressTypeInfo(liveEmailVo) +
                        messageSource.getMessage("mail.live.cancel", null, Locale.KOREA) +
                        messageSource.getMessage("mail.footer", null, Locale.KOREA));
        return simpleMailMessage;
    }

    private String createLiveDateInfo(LiveEmailVo liveEmailVo) {
        return "- 일시 : " + StringUtils.dateToString(liveEmailVo.startDate()) + " ~ " + StringUtils.dateToString(liveEmailVo.endDate()).substring(14) + "\n";
    }

    private String createProgressTypeInfo(LiveEmailVo liveEmailVo) {
        String wayInfo = "";

        if(liveEmailVo.progressType().equals(ProgressType.ONLINE) || liveEmailVo.progressType().equals(ProgressType.ALL)) {
            wayInfo += "- Zoom 링크: " + liveEmailVo.zoomLink() + " \n" + "- Zoom 회의실 암호: " + liveEmailVo.zoomPassword() + " \n";
        }

        if(liveEmailVo.progressType().equals(ProgressType.OFFLINE) || liveEmailVo.progressType().equals(ProgressType.ALL)) {
            wayInfo += "- 장소: " + liveEmailVo.place() + "\n";
        }

        return wayInfo + "\n";
    }
}
