package org.letscareer.letscareer.domain.faq.repository;

import org.letscareer.letscareer.domain.faq.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Long> {
}
