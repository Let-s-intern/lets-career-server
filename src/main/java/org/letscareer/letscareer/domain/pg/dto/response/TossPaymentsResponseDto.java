package org.letscareer.letscareer.domain.pg.dto.response;

import jakarta.annotation.Nullable;
import org.letscareer.letscareer.domain.pg.dto.response.toss.*;

import java.time.LocalDateTime;

public record TossPaymentsResponseDto(
        String mId,
        @Nullable String lastTransactionKey,
        String paymentKey,
        String orderId,
        String orderName,
        Integer taxExemptionAmount,
        String status,
        LocalDateTime requestedAt,
        LocalDateTime approvedAt,
        Boolean useEscrow,
        Boolean cultureExpense,
        @Nullable CardInfo card,
        @Nullable String virtualAccount,
        @Nullable TransferInfo transfer,
        @Nullable MobilePhoneInfo mobilePhone,
        @Nullable GiftCertificateInfo giftCertificate,
        @Nullable CashReceiptInfo cashReceipt,
        @Nullable CashReceiptsInfo cashReceipts,
        @Nullable DiscountInfo discount,
        @Nullable CancelsInfo cancels,
        @Nullable String secret,
        String type,
        @Nullable PayInfo easyPay,
        String country,
        @Nullable FailureInfo failure,
        Boolean isPartialCancelable,
        @Nullable ReceiptInfo receipt,
        @Nullable CheckoutInfo checkout,
        String currency,
        Integer totalAmount,
        Integer balanceAmount,
        Integer suppliedAmount,
        Integer vat,
        Integer taxFreeAmount,
        String method,
        String version
) {
}