package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.repository.ReportReviewRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportReviewHelper {
    private final ReportReviewRepository reportReviewRepository;
}
