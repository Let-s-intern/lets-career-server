package org.letscareer.letscareer.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.file.type.FileType;
import org.letscareer.letscareer.domain.review.dto.request.CreateBlogReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetBlogReviewForAdminResponseDto;
import org.letscareer.letscareer.domain.review.entity.BlogReview;
import org.letscareer.letscareer.domain.review.helper.BlogReviewHelper;
import org.letscareer.letscareer.domain.review.mapper.ReviewMapper;
import org.letscareer.letscareer.domain.review.vo.BlogReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.BlogReviewOpenGraphVo;
import org.letscareer.letscareer.global.common.utils.aws.S3Utils;
import org.letscareer.letscareer.global.common.utils.opengraph.OpenGraphUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class BlogReviewServiceImpl implements BlogReviewService {
    private final BlogReviewHelper blogReviewHelper;
    private final OpenGraphUtils openGraphUtils;
    private final S3Utils s3Utils;
    private final ReviewMapper reviewMapper;

    @Override
    public GetBlogReviewForAdminResponseDto getBlogReviewForAdmin() {
        List<BlogReviewAdminVo> blogReviewAdminVos = blogReviewHelper.findAllBlogReviewAdminVos();
        return reviewMapper.toGetBlogReviewForAdminResponseDto(blogReviewAdminVos);
    }

    @Override
    public void createBlogReview(CreateBlogReviewRequestDto requestDto) {
        BlogReviewOpenGraphVo openGraphVo = openGraphUtils.getBlogReviewOpenGraphVo(requestDto.url());
        BlogReview blogReview = blogReviewHelper.createBlogReviewAndSave(requestDto, openGraphVo);
        if(!Objects.isNull(openGraphVo.image())) {
            String thumbnail = s3Utils.saveImgFromUrl(openGraphVo.image(), FileType.BLOG_REVIEW.getDesc(), blogReview.getId() + openGraphVo.title()).getUrl();
            blogReview.setThumbnail(thumbnail);
        }
    }
}
