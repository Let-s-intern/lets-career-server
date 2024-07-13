package org.letscareer.letscareer.domain.withdraw.helper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.withdraw.entity.UserWithdrawalRecord;
import org.letscareer.letscareer.domain.withdraw.repository.UserWithdrawalRecordRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class WithdrawHelper {
    private final UserWithdrawalRecordRepository userWithdrawalRecordRepository;

    public void createUserWithdrawalRecordAndSave(User user) {
        UserWithdrawalRecord withdrawalRecord = UserWithdrawalRecord.createUserWithdrawalRecord(user);
        userWithdrawalRecordRepository.save(withdrawalRecord);
    }
}
