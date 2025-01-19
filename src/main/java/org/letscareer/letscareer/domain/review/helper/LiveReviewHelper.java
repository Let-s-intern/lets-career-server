package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.repository.LiveReviewRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LiveReviewHelper {
    private final LiveReviewRepository liveReviewRepository;
}
