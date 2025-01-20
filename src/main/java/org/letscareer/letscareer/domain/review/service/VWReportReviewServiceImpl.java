package org.letscareer.letscareer.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewResponseDto;
import org.letscareer.letscareer.domain.review.helper.ReportReviewHelper;
import org.letscareer.letscareer.domain.review.helper.ReviewItemHelper;
import org.letscareer.letscareer.domain.review.mapper.ReviewMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class VWReportReviewServiceImpl implements VWReviewService {
    private final ReportReviewHelper reportReviewHelper;
    private final ReviewItemHelper reviewItemHelper;
    private final ReviewMapper reviewMapper;

    @Override
    public GetReviewResponseDto getReviews() {
        return null;
    }
}
