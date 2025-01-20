package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.repository.ReviewRepository;
import org.letscareer.letscareer.domain.review.vo.ReviewInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReviewHelper {
    private final ReviewRepository reviewRepository;

    public Page<ReviewInfoVo> getReviewInfoVos(Pageable pageable) {
        return reviewRepository.findAllReviewInfoVos(pageable);
    }
}
