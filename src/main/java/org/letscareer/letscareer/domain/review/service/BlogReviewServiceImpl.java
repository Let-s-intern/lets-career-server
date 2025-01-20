package org.letscareer.letscareer.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.dto.request.CreateBlogReviewRequestDto;
import org.letscareer.letscareer.domain.review.helper.BlogReviewHelper;
import org.letscareer.letscareer.domain.review.vo.BlogReviewOpenGraphVo;
import org.letscareer.letscareer.global.common.utils.opengraph.OpenGraphUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class BlogReviewServiceImpl implements BlogReviewService {
    private final BlogReviewHelper blogReviewHelper;
    private final OpenGraphUtils openGraphUtils;

    @Override
    public void createBlogReview(CreateBlogReviewRequestDto requestDto) {
        BlogReviewOpenGraphVo openGraphVo = openGraphUtils.getBlogReviewOpenGraphVo(requestDto.url());
    }
}
