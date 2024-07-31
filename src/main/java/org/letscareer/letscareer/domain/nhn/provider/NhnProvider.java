package org.letscareer.letscareer.domain.nhn.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.letscareer.letscareer.domain.nhn.dto.request.CreateMessageRequestDto;
import org.letscareer.letscareer.domain.nhn.dto.request.CreditConfirmParameter;
import org.letscareer.letscareer.domain.nhn.dto.request.RecipientInfo;
import org.letscareer.letscareer.domain.nhn.dto.request.ReviewParameter;
import org.letscareer.letscareer.domain.nhn.dto.response.CreateMessageResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.utils.nhn.NhnFeignController;
import org.letscareer.letscareer.global.common.utils.nhn.NhnSecretKeyReader;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Component
public class NhnProvider<T> {
    private final NhnFeignController nhnFeignController;
    private final NhnSecretKeyReader nhnSecretKeyReader;

    @Async("threadPoolTaskExecutor")
    public void sendKakaoMessage(User user, T requestParameter, String templateCode) {
        String appKey = nhnSecretKeyReader.getAppKey();
        List<RecipientInfo<?>> recipientInfoList = createRecipient(user, requestParameter);
        CreateMessageRequestDto requestDto = createMessageRequestDto(recipientInfoList, templateCode);
        CreateMessageResponseDto responseDto = nhnFeignController.createMessage(appKey, requestDto);
        log.info("[NHN Result]::" + responseDto);
    }

    @Async("threadPoolTaskExecutor")
    public void sendPaymentKakaoMessages(User user, T paymentRequestParameter, T programRequestParameter, String paymentTemplateCode, String programTemplateCode) {
        try {
            String appKey = nhnSecretKeyReader.getAppKey();
            List<RecipientInfo<?>> paymentRecipientInfoList = createRecipient(user, paymentRequestParameter);
            CreateMessageRequestDto paymentRequestDto = createMessageRequestDto(paymentRecipientInfoList, paymentTemplateCode);
            CreateMessageResponseDto paymentResponseDto = nhnFeignController.createMessage(appKey, paymentRequestDto);
            log.info("[NHN Result]::" + paymentResponseDto);
            Thread.sleep(5000);
            if(programRequestParameter != null) {
                List<RecipientInfo<?>> programRecipientInfoList = createRecipient(user, programRequestParameter);
                CreateMessageRequestDto programRequestDto = createMessageRequestDto(programRecipientInfoList, programTemplateCode);
                CreateMessageResponseDto programResponseDto = nhnFeignController.createMessage(appKey, programRequestDto);
                log.info("[NHN Result]::" + programResponseDto);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Async("threadPoolTaskExecutor")
    public void sendKakaoMessages(List<User> userList, List<T> requestParameterList, String templateCode) {
        String appKey = nhnSecretKeyReader.getAppKey();
        List<RecipientInfo<?>> recipientInfoList = createRecipientList(userList, requestParameterList);
        CreateMessageRequestDto requestDto = createMessageRequestDto(recipientInfoList, templateCode);
        CreateMessageResponseDto responseDto = nhnFeignController.createMessage(appKey, requestDto);
        log.info("[NHN Result]::" + responseDto);
    }

    private CreateMessageRequestDto createMessageRequestDto(List<RecipientInfo<?>> recipientList, String templateCode) {
        String senderKey = nhnSecretKeyReader.getSendKey();
        return CreateMessageRequestDto.of(senderKey, templateCode, recipientList);
    }

    private List<RecipientInfo<?>> createRecipient(User user, T requestParameter) {
        List<RecipientInfo<?>> recipientList = new ArrayList<>();
        RecipientInfo<?> recipientInfo = RecipientInfo.of(user.getPhoneNum(), requestParameter);
        recipientList.add(recipientInfo);
        return recipientList;
    }

    private List<RecipientInfo<?>> createRecipientList(List<User> userList, List<T> requestParameterList) {
        return Stream.iterate(0, i->i+1)
                .limit(userList.size())
                .map(i -> RecipientInfo.of(userList.get(i).getPhoneNum(), requestParameterList.get(i)))
                .collect(Collectors.toList());
    }
}
