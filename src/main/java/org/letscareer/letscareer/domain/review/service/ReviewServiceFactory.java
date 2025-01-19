package org.letscareer.letscareer.domain.review.service;

import org.letscareer.letscareer.domain.program.type.ProgramType;

public interface ReviewServiceFactory {
    ReviewService getReviewService(ProgramType programType);
}
