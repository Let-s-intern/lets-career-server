package org.letscareer.letscareer.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service("LIVE")
public class LiveReviewServiceImpl implements ReviewService {
    @Override
    public void createReview(Long applicationId, CreateReviewRequestDto requestDto) {

    }
}
