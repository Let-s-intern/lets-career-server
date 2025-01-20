package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.repository.BlogReviewRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BlogReviewHelper {
    private final BlogReviewRepository blogReviewRepository;
}
