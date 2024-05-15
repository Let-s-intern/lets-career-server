package org.letscareer.letscareer.domain.program.repository;

import org.letscareer.letscareer.domain.program.entity.VWProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<VWProgram, Long>, ProgramQueryRepository{
}
