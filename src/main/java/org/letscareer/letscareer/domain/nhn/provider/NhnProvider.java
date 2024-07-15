package org.letscareer.letscareer.domain.nhn.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.letscareer.letscareer.domain.nhn.dto.request.CreateMessageRequestDto;
import org.letscareer.letscareer.domain.nhn.dto.request.CreditConfirmParameter;
import org.letscareer.letscareer.domain.nhn.dto.request.RecipientInfo;
import org.letscareer.letscareer.domain.nhn.dto.response.CreateMessageResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.utils.nhn.NhnFeignController;
import org.letscareer.letscareer.global.common.utils.nhn.NhnSecretKeyReader;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class NhnProvider {
    private final NhnFeignController nhnFeignController;
    private final NhnSecretKeyReader nhnSecretKeyReader;

    @Async("threadPoolTaskExecutor")
    public void sendKakaoMessage(User user, CreditConfirmParameter requestParameter) {
        String appKey = nhnSecretKeyReader.getAppKey();
        List<RecipientInfo<?>> recipientInfoList = createRecipient(user, requestParameter);
        CreateMessageRequestDto requestDto = createMessageRequestDto(recipientInfoList);
        CreateMessageResponseDto responseDto = nhnFeignController.createMessage(appKey, requestDto);
        log.info("[NHN Result]::" + responseDto);
    }

    private CreateMessageRequestDto createMessageRequestDto(List<RecipientInfo<?>> recipientList) {
        String senderKey = nhnSecretKeyReader.getSendKey();
        String templateCode = nhnSecretKeyReader.getTemplateCode();
        return CreateMessageRequestDto.of(senderKey, templateCode, recipientList);
    }

    private List<RecipientInfo<?>> createRecipient(User user, CreditConfirmParameter requestParameter) {
        List<RecipientInfo<?>> recipientList = new ArrayList<>();
        RecipientInfo<?> recipientInfo = RecipientInfo.of(user.getPhoneNum(), requestParameter);
        recipientList.add(recipientInfo);
        return recipientList;
    }
}
