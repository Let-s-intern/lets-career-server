package org.letscareer.letscareer.domain.blog.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.entity.BlogRating;
import org.letscareer.letscareer.domain.blog.repository.RatingRepository;
import org.letscareer.letscareer.domain.blog.vo.RatingDetailVo;
import org.letscareer.letscareer.domain.blog.vo.RatingVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RatingHelper {
    private final RatingRepository ratingRepository;

    public void saveRating(BlogRating rating) {
        ratingRepository.save(rating);
    }

    public List<RatingDetailVo> findAllRatingDetailVos(Long blogId) {
        return ratingRepository.findAllRatingDetailVosByBlogId(blogId);
    }

    public Page<RatingVo> findRatingVos(Pageable pageable) {
        return ratingRepository.findRatingVos(pageable);
    }
}
