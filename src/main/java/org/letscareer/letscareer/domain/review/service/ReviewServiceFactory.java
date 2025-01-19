package org.letscareer.letscareer.domain.review.service;

import org.letscareer.letscareer.domain.review.type.ReviewProgramType;

public interface ReviewServiceFactory {
    ReviewService getReviewService(ReviewProgramType programType);
}
