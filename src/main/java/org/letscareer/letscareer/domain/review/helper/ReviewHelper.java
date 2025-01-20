package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.repository.ReviewRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReviewHelper {
    private final ReviewRepository reviewRepository;
}
