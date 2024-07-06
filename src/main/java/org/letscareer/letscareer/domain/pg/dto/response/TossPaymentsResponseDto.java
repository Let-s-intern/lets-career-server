package org.letscareer.letscareer.domain.pg.dto.response;

import org.letscareer.letscareer.domain.pg.dto.response.toss.CheckoutInfo;
import org.letscareer.letscareer.domain.pg.dto.response.toss.FailureInfo;
import org.letscareer.letscareer.domain.pg.dto.response.toss.PayInfo;
import org.letscareer.letscareer.domain.pg.dto.response.toss.ReceiptInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TossPaymentsResponseDto(
        String mId,
        String lastTransactionKey,
        String paymentKey,
        String orderId,
        String orderName,
        Integer taxExemptionAmount,
        String status,
        LocalDateTime requestedAt,
        LocalDateTime approvedAt,
        Boolean useEscrow,
        Boolean cultureExpense,
        String card,
        String virtualAccount,
        String transfer,
        String mobilePhone,
        String giftCertificate,
        String cashReceipt,
        String cashReceipts,
        String discount,
        String cancels,
        String secret,
        String type,
        PayInfo easyPay,
        String country,
        FailureInfo failure,
        Boolean isPartialCancelable,
        ReceiptInfo receipt,
        CheckoutInfo checkout,
        String currency,
        Integer totalAmount,
        Integer balanceAmount,
        Integer suppliedAmount,
        Integer vat,
        Integer taxFreeAmount,
        String method,
        LocalDate version
) {
}