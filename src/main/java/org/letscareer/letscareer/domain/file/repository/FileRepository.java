package org.letscareer.letscareer.domain.file.repository;

import org.letscareer.letscareer.domain.file.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
