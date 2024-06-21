package org.letscareer.letscareer.domain.withdraw.repository;

import org.letscareer.letscareer.domain.withdraw.entity.ApplicationWithdrawalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationWithdrawalRecordRepository extends JpaRepository<ApplicationWithdrawalRecord, Long> {
}
