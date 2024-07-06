package org.letscareer.letscareer.toss;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;
import org.letscareer.letscareer.domain.pg.dto.request.TossPaymentsRequestDto;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.pg.mapper.TossMapper;
import org.letscareer.letscareer.global.common.utils.toss.TossFeignController;
import org.letscareer.letscareer.global.common.utils.toss.TossSecretKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TossApiTests {
    @Autowired
    private TossSecretKeyGenerator tossSecretKeyGenerator;
    @Autowired
    private TossFeignController tossFeignController;
    @Autowired
    private TossMapper tossMapper;

    @Test
    @DisplayName("secrete key 생성")
    void createSecretKeyTest() {
        // given
        String testKey = "Basic dGVzdF9nc2tfZG9jc19PYVB6OEw1S2RtUVhrelJ6M3k0N0JNdzY6";

        // when
        String secretKey = tossSecretKeyGenerator.generateSecretKey();

        // then
        System.out.println(secretKey);
        assertThat(secretKey).isEqualTo(testKey);
    }

    @Test
    @DisplayName("provider 요청 생성")
    void createRequestHeaderTest() {
        // given
        Long couponId = null;
        Long priceId = null;
        String paymentKey = "tgen_20240706152517DN255";
        String orderId = "MC4yODgwNzczNTQ1MDg1";
        String amount = "100";
        CreatePaymentRequestDto paymentRequestDto = new CreatePaymentRequestDto(couponId, priceId, paymentKey, orderId, amount);

        // when
        TossPaymentsRequestDto requestDto = tossMapper.toTossPaymentsRequestDto(paymentRequestDto);
        TossPaymentsResponseDto responseDto = tossFeignController.createPayments(requestDto);

        // then
        assertThat(responseDto).isNotNull();
    }
}
