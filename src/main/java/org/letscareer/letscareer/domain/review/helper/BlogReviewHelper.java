package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.dto.request.CreateBlogReviewRequestDto;
import org.letscareer.letscareer.domain.review.entity.BlogReview;
import org.letscareer.letscareer.domain.review.repository.BlogReviewRepository;
import org.letscareer.letscareer.domain.review.vo.BlogReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.BlogReviewOpenGraphVo;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BlogReviewHelper {
    private final BlogReviewRepository blogReviewRepository;

    public BlogReview createBlogReviewAndSave(CreateBlogReviewRequestDto requestDto, BlogReviewOpenGraphVo openGraphVo) {
        BlogReview blogReview = BlogReview.createBlogReview(requestDto, openGraphVo);
        return blogReviewRepository.save(blogReview);
    }

    public List<BlogReviewAdminVo> findAllBlogReviewAdminVos() {
        return blogReviewRepository.findAllBlogReviewAdminVos();
    }
}
