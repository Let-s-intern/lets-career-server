package org.letscareer.letscareer.toss;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.type.RefundType;
import org.letscareer.letscareer.domain.pg.dto.request.TossPaymentsCancelRequestDto;
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
    @DisplayName("provider 결제 승인 api")
    void createPaymentTest() {
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

    @Test
    @DisplayName("paymentKey로 결제 조회 api")
    void getPaymentDetailForPaymentKeyTest() {
        // given
        String paymentKey = "tgen_20240713123135pVXw8";

        // when
        TossPaymentsResponseDto responseDto = tossFeignController.getPaymentDetail(paymentKey);

        // then
        System.out.println(responseDto);
        assertThat(responseDto.paymentKey()).isEqualTo(paymentKey);
    }

    @Test
    @DisplayName("paymentKey로 결제 취소 api")
    void cancelPayment() {
        // given
        String paymentKey = "tgen_20240713115657yIgO8";
        TossPaymentsCancelRequestDto requestDto = tossMapper.toTossPaymentsCancelRequestDto(RefundType.HALF, "고객이 취소를 원함", 100);

        // when
        TossPaymentsResponseDto responseDto = tossFeignController.cancelPayments(paymentKey, requestDto);

        // then
        System.out.println(responseDto.cancels().get(0));
        assertThat(responseDto.cancels().get(0).cancelAmount().equals(100));
    }
}
