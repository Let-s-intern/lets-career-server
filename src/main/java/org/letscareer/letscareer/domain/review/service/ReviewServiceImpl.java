package org.letscareer.letscareer.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.application.helper.ApplicationHelper;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.entity.Review;
import org.letscareer.letscareer.domain.review.helper.ReviewHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewServiceImpl implements ReviewService{
    private final ReviewHelper reviewHelper;
    private final ApplicationHelper applicationHelper;

    @Override
    public void createReview(Long applicationId, CreateReviewRequestDto responseDto) {
        Application application = applicationHelper.findByIdOrThrow(applicationId);
        reviewHelper.createReviewAndSave(application, responseDto);
    }
}
