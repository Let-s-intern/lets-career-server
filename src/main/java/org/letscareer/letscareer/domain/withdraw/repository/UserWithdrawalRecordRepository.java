package org.letscareer.letscareer.domain.withdraw.repository;

import org.letscareer.letscareer.domain.withdraw.entity.UserWithdrawalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWithdrawalRecordRepository extends JpaRepository<UserWithdrawalRecord, Long> {
}
