package org.letscareer.letscareer.domain.blog.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.entity.BlogRating;
import org.letscareer.letscareer.domain.blog.repository.RatingRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RatingHelper {
    private final RatingRepository ratingRepository;

    public void saveRating(BlogRating rating) {
        ratingRepository.save(rating);
    }
}
