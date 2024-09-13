package org.letscareer.letscareer.global.common.utils.slack;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

public interface WebhookProvider {
//    @Async
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void sendMessage(Object sendData);
}
