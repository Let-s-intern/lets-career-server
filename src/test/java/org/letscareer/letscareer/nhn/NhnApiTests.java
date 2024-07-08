package org.letscareer.letscareer.nhn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.letscareer.letscareer.domain.nhn.dto.response.CreateMessageResponseDto;
import org.letscareer.letscareer.global.common.utils.nhn.NhnFeignController;
import org.letscareer.letscareer.global.common.utils.nhn.NhnSecretKeyReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        System.out.println(appKey);
        assertThat(appKey).isNotEmpty();
    }

    @Test
    @DisplayName("메시지 치환 발송 요청 api")
    void sendMessageToKakao() {
        // given
        String appKey = nhnSecretKeyReader.getAppKey();

        // when
        CreateMessageResponseDto responseDto = nhnFeignController.createMessage(appKey, null);

        // then
        System.out.println(responseDto);
        assertThat(responseDto).isNotNull();
    }
}
