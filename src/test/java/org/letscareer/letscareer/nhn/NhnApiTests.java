package org.letscareer.letscareer.nhn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.letscareer.letscareer.domain.nhn.dto.request.CreateMessageRequestDto;
import org.letscareer.letscareer.domain.nhn.dto.request.CreditConfirmParameter;
import org.letscareer.letscareer.domain.nhn.dto.request.RecipientInfo;
import org.letscareer.letscareer.domain.nhn.dto.request.CreditRefundParameter;
import org.letscareer.letscareer.domain.nhn.dto.response.CreateMessageResponseDto;
import org.letscareer.letscareer.domain.payment.type.RefundType;
import org.letscareer.letscareer.global.common.utils.nhn.NhnFeignController;
import org.letscareer.letscareer.global.common.utils.nhn.NhnSecretKeyReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NhnApiTests {
    @Autowired
    private NhnSecretKeyReader nhnSecretKeyReader;
    @Autowired
    private NhnFeignController nhnFeignController;

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
}
