package org.letscareer.letscareer.domain.contents.repository;

import org.letscareer.letscareer.domain.contents.entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsRepository extends JpaRepository<Contents, Long>, ContentsQueryRepository {
}
