package org.letscareer.letscareer.nhn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.live.helper.LiveHelper;
import org.letscareer.letscareer.domain.nhn.dto.request.*;
import org.letscareer.letscareer.domain.nhn.dto.response.CreateMessageResponseDto;
import org.letscareer.letscareer.domain.payment.type.RefundType;
import org.letscareer.letscareer.global.common.utils.nhn.NhnFeignController;
import org.letscareer.letscareer.global.common.utils.nhn.NhnSecretKeyReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NhnApiTests {
    @Autowired
    private NhnSecretKeyReader nhnSecretKeyReader;
    @Autowired
    private NhnFeignController nhnFeignController;
    @Autowired
    private ChallengeHelper challengeHelper;
    @Autowired
    private LiveHelper liveHelper;

    @Test
    @DisplayName("app key 생성")
    void createSecretKeyTest() {
        // given

        // when
        String appKey = nhnSecretKeyReader.getAppKey();

        // then
        assertThat(appKey).isNotEmpty();
    }

    @Test
    @DisplayName("결제 확인 메시지 치환 발송 요청 api")
    void sendMessageToKakao() {
        // given
        String senderKey = nhnSecretKeyReader.getSendKey();
        String templateKey = nhnSecretKeyReader.getTemplateCode();
        String appKey = nhnSecretKeyReader.getAppKey();
        String recipientNo = "010-9278-7357";
        CreditConfirmParameter creditConfirmParameter = CreditConfirmParameter.of("류관곤", "101", null);

        // when
        RecipientInfo<?> recipientInfo = RecipientInfo.of(recipientNo, creditConfirmParameter);
        List<RecipientInfo<?>> recipientInfoList = new ArrayList<>();
        recipientInfoList.add(recipientInfo);
        CreateMessageRequestDto requestDto = CreateMessageRequestDto.of(senderKey, templateKey, recipientInfoList);
        CreateMessageResponseDto responseDto = nhnFeignController.createMessage(appKey, requestDto);

        // then
        System.out.println(responseDto);
        assertThat(responseDto).isNotNull();
    }

    @Test
    @DisplayName("환불 완료 메시지 치환 발송")
    void sendRefundMessageToKakao() {
        // given
        String senderKey = nhnSecretKeyReader.getSendKey();
        String templateCode = "payment_refund";
        String appKey = nhnSecretKeyReader.getAppKey();
        String recipientNo = "010-3419-0076";
        CreditRefundParameter creditRefundParameter = CreditRefundParameter.of("김민서", "order1234", "강아지 산책 노하우", RefundType.HALF, 10000, 5000);

        // when
        List<RecipientInfo<?>> recipientInfoList = new ArrayList<>();
        RecipientInfo<?> recipientInfo = RecipientInfo.of(recipientNo, creditRefundParameter);
        recipientInfoList.add(recipientInfo);
        CreateMessageRequestDto requestDto = CreateMessageRequestDto.of(senderKey, templateCode, recipientInfoList);
        CreateMessageResponseDto responseDto = nhnFeignController.createMessage(appKey, requestDto);

        // then
        System.out.println(responseDto);
        assertThat(responseDto).isNotNull();
    }

    @Test
    @DisplayName("OT 리마인드 메시지 치환 발송")
    void sendOTRemindMessageToKakao() {
        // given
        String senderKey = nhnSecretKeyReader.getSendKey();
        String templateCode = "OT_remind";
        String appKey = nhnSecretKeyReader.getAppKey();
        String recipientNo = "010-9322-8191";

        String userName = "임호정";
        String programTitle = "test program";
        LocalDateTime programStartDate = LocalDateTime.now();
        String zoomLink = "https";
        Long programId = 3L;
        String link = "84801956463";
        String password = "0BV4scd";
        OTRemindParameter creditRefundParameter = OTRemindParameter.of(userName, null);
        System.out.println(creditRefundParameter.programStartDate());

        // when
        List<RecipientInfo<?>> recipientInfoList = new ArrayList<>();
        RecipientInfo<?> recipientInfo = RecipientInfo.of(recipientNo, creditRefundParameter);
        recipientInfoList.add(recipientInfo);
        CreateMessageRequestDto requestDto = CreateMessageRequestDto.of(senderKey, templateCode, recipientInfoList);
        CreateMessageResponseDto responseDto = nhnFeignController.createMessage(appKey, requestDto);

        // then
        System.out.println(responseDto);
        assertThat(responseDto).isNotNull();
    }

    @Test
    @DisplayName("미션 종료 메시지 치환 발송")
    void sendMissionEndMessageToKakao() {
        // given
        String senderKey = nhnSecretKeyReader.getSendKey();
        String templateCode = "mission_end";
        String appKey = nhnSecretKeyReader.getAppKey();
        String recipientNo = "010-9322-8191";

        String userName = "임호정";
        String programTitle = "test program";
        String th = "1";
        LocalDateTime missionStartDate = LocalDateTime.now();
        LocalDateTime missionEndDate = LocalDateTime.now();
        Long programId = 3L;
        MissionEndParameter missionEndParameter = MissionEndParameter.of(userName, null, null);

        // when
        List<RecipientInfo<?>> recipientInfoList = new ArrayList<>();
        RecipientInfo<?> recipientInfo = RecipientInfo.of(recipientNo, missionEndParameter);
        recipientInfoList.add(recipientInfo);
        CreateMessageRequestDto requestDto = CreateMessageRequestDto.of(senderKey, templateCode, recipientInfoList);
        CreateMessageResponseDto responseDto = nhnFeignController.createMessage(appKey, requestDto);

        // then
        System.out.println(responseDto);
        assertThat(responseDto).isNotNull();
    }

    @Test
    @DisplayName("라이브 클래스 리마인드 메시지 치환 발송")
    void sendLiveClassRemindMessageToKakao() {
        // given
        String senderKey = nhnSecretKeyReader.getSendKey();
        String templateCode = "liveclass_remind";
        String appKey = nhnSecretKeyReader.getAppKey();
        String recipientNo = "010-9322-8191";

        String userName = "임호정";
        String programTitle = "test program";
        LocalDateTime programStartDate = LocalDateTime.now();
        String zoomLink = "https";
        Long programId = 3L;
        String link = "84801956463";
        String password = "0BV4scd";
        LiveClassRemindParameter liveClassRemindParameter = LiveClassRemindParameter.of(userName, null);

        // when
        List<RecipientInfo<?>> recipientInfoList = new ArrayList<>();
        RecipientInfo<?> recipientInfo = RecipientInfo.of(recipientNo, liveClassRemindParameter);
        recipientInfoList.add(recipientInfo);
        CreateMessageRequestDto requestDto = CreateMessageRequestDto.of(senderKey, templateCode, recipientInfoList);
        CreateMessageResponseDto responseDto = nhnFeignController.createMessage(appKey, requestDto);

        // then
        System.out.println(responseDto);
        assertThat(responseDto).isNotNull();
    }

    @Test
    @DisplayName("LIVE 클래스 안내사항 전달 (ONLINE) 메시지 치환 발송")
    void sendLiveClassPaymentMessageToKakao() {
        // given
        String senderKey = nhnSecretKeyReader.getSendKey();
        String templateCode = "liveclass_payment";
        String appKey = nhnSecretKeyReader.getAppKey();
        String recipientNo = "010-3419-0076";

        String userName = "김민동";
        Live live = liveHelper.findLiveByIdOrThrow(3L);
        LiveClassPaymentParameter liveClassPaymentParameter = LiveClassPaymentParameter.of(userName, live);

        // when
        List<RecipientInfo<?>> recipientInfoList = new ArrayList<>();
        RecipientInfo<?> recipientInfo = RecipientInfo.of(recipientNo, liveClassPaymentParameter);
        recipientInfoList.add(recipientInfo);
        CreateMessageRequestDto requestDto = CreateMessageRequestDto.of(senderKey, templateCode, recipientInfoList);
        CreateMessageResponseDto responseDto = nhnFeignController.createMessage(appKey, requestDto);

        // then
        System.out.println(responseDto);
        assertThat(responseDto).isNotNull();
    }

    @Test
    @DisplayName("챌린지 안내사항 전달 (LIVE) 메시지 치환 발송")
    void sendChallengePaymentMessageToKakao() {
        // given
        String senderKey = nhnSecretKeyReader.getSendKey();
        String templateCode = "challenge_payment";
        String appKey = nhnSecretKeyReader.getAppKey();
        String recipientNo = "010-3419-0076";

        String userName = "김민서";
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(25L);
        ChallengePaymentParameter challengePaymentParameter = ChallengePaymentParameter.of(userName, challenge);

        // when
        List<RecipientInfo<?>> recipientInfoList = new ArrayList<>();
        RecipientInfo<?> recipientInfo = RecipientInfo.of(recipientNo, challengePaymentParameter);
        recipientInfoList.add(recipientInfo);
        CreateMessageRequestDto requestDto = CreateMessageRequestDto.of(senderKey, templateCode, recipientInfoList);
        System.out.println(requestDto);
        CreateMessageResponseDto responseDto = nhnFeignController.createMessage(appKey, requestDto);

        // then
        System.out.println(responseDto);
        assertThat(responseDto).isNotNull();
    }

    @Test
    @DisplayName("챌린지 D-1 안내 (LIVE) 메시지 치환 발송")
    void sendChallengeRemindMessageToKakao() {
        // given
        String senderKey = nhnSecretKeyReader.getSendKey();
        String templateCode = "challenge_remind";
        String appKey = nhnSecretKeyReader.getAppKey();
        String recipientNo = "010-9322-8191";

        String userName = "임호정";
        String programTitle = "test program";
        LocalDateTime programStartDate = LocalDateTime.now();
        LocalDateTime programEndDate = LocalDateTime.now();
        String zoomLink = "https";
        String link = "84801956463";
        String password = "0BV4scd";
        ChallengeRemindParameter challengeRemindParameter = ChallengeRemindParameter.of(userName, null);

        // when
        List<RecipientInfo<?>> recipientInfoList = new ArrayList<>();
        RecipientInfo<?> recipientInfo = RecipientInfo.of(recipientNo, challengeRemindParameter);
        recipientInfoList.add(recipientInfo);
        CreateMessageRequestDto requestDto = CreateMessageRequestDto.of(senderKey, templateCode, recipientInfoList);
        CreateMessageResponseDto responseDto = nhnFeignController.createMessage(appKey, requestDto);

        // then
        System.out.println(responseDto);
        assertThat(responseDto).isNotNull();
    }

    @Test
    @DisplayName("챌린지 최종 종료 안내 (LIVE) 메시지 치환 발송")
    void sendChallengeEndMessageToKakao() {
        // given
        String senderKey = nhnSecretKeyReader.getSendKey();
        String templateCode = "challenge_end";
        String appKey = nhnSecretKeyReader.getAppKey();
        String recipientNo = "010-9322-8191";

        String userName = "임호정";
        String programTitle = "test program";
        Long programId = 3L;
        ChallengeEndParameter challengeEndParameter = ChallengeEndParameter.of(userName, programTitle, programId);

        // when
        List<RecipientInfo<?>> recipientInfoList = new ArrayList<>();
        RecipientInfo<?> recipientInfo = RecipientInfo.of(recipientNo, challengeEndParameter);
        recipientInfoList.add(recipientInfo);
        CreateMessageRequestDto requestDto = CreateMessageRequestDto.of(senderKey, templateCode, recipientInfoList);
        CreateMessageResponseDto responseDto = nhnFeignController.createMessage(appKey, requestDto);

        // then
        System.out.println(responseDto);
        assertThat(responseDto).isNotNull();
    }

    @Test
    @DisplayName("후기 요청 메시지 치환 발송")
    void sendReviewMessageToKakao() {
        // given
        String senderKey = nhnSecretKeyReader.getSendKey();
        String templateCode = "review";
        String appKey = nhnSecretKeyReader.getAppKey();
        String recipientNo = "010-9322-8191";
        String program = "CHALLENGE";
        Long programId = 1L;

        String userName = "임호정";
        ReviewParameter challengeEndParameter = ReviewParameter.of(userName, program, programId);

        // when
        List<RecipientInfo<?>> recipientInfoList = new ArrayList<>();
        RecipientInfo<?> recipientInfo = RecipientInfo.of(recipientNo, challengeEndParameter);
        recipientInfoList.add(recipientInfo);
        CreateMessageRequestDto requestDto = CreateMessageRequestDto.of(senderKey, templateCode, recipientInfoList);
        CreateMessageResponseDto responseDto = nhnFeignController.createMessage(appKey, requestDto);

        // then
        System.out.println(responseDto);
        assertThat(responseDto).isNotNull();
    }
}
