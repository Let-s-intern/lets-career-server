package org.letscareer.letscareer.domain.withdraw.helper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.withdraw.entity.ApplicationWithdrawalRecord;
import org.letscareer.letscareer.domain.withdraw.entity.UserWithdrawalRecord;
import org.letscareer.letscareer.domain.withdraw.repository.ApplicationWithdrawalRecordRepository;
import org.letscareer.letscareer.domain.withdraw.repository.UserWithdrawalRecordRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class WithdrawHelper {
    private final UserWithdrawalRecordRepository userWithdrawalRecordRepository;
    private final ApplicationWithdrawalRecordRepository applicationWithdrawalRecordRepository;

    public void createUserWithdrawalRecordAndSave(User user) {
        UserWithdrawalRecord withdrawalRecord = UserWithdrawalRecord.createUserWithdrawalRecord(user);
        userWithdrawalRecordRepository.save(withdrawalRecord);
    }

    public void createApplicationWithdrawalRecordAndSave(Long programId, String title, ProgramType programType, User user) {
        ApplicationWithdrawalRecord withdrawalRecord = ApplicationWithdrawalRecord.createApplicationWithdrawalRecord(programId, title, programType, user);
        applicationWithdrawalRecordRepository.save(withdrawalRecord);
    }
}
