package org.letscareer.letscareer.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewCountResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewResponseDto;
import org.letscareer.letscareer.domain.review.helper.BlogReviewHelper;
import org.letscareer.letscareer.domain.review.helper.ReviewHelper;
import org.letscareer.letscareer.domain.review.helper.ReviewItemHelper;
import org.letscareer.letscareer.domain.review.mapper.ReviewMapper;
import org.letscareer.letscareer.domain.review.type.ReviewProgramType;
import org.letscareer.letscareer.domain.review.vo.ReviewInfoVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class VWReviewServiceImpl implements VWReviewService {
    private final ReviewHelper reviewHelper;
    private final ReviewItemHelper reviewItemHelper;
    private final BlogReviewHelper blogReviewHelper;
    private final ReviewMapper reviewMapper;

    @Override
    public GetReviewResponseDto getReviews(List<ReviewProgramType> typeList, List<ChallengeType> challengeTypeList, Pageable pageable) {
        Page<ReviewInfoVo> reviewInfoVos = reviewHelper.getReviewInfoVos(typeList, challengeTypeList, pageable);
        List<ReviewVo> reviewVos = reviewInfoVos.getContent().stream()
                .map(reviewInfoVo -> reviewMapper.toReviewVo(
                        reviewInfoVo,
                        reviewInfoVo.type().equals(ReviewProgramType.MISSION_REVIEW) ? new ArrayList<>() : reviewItemHelper.findAllReviewItemVosByReviewId(reviewInfoVo.reviewId(), true)
                ))
                .collect(Collectors.toList());
        PageInfo pageInfo = PageInfo.of(reviewInfoVos);
        return reviewMapper.toGetReviewResponseDto(reviewVos, pageInfo);
    }

    @Override
    public GetReviewCountResponseDto getReviewCount() {
        Long reviewCount = reviewHelper.countReviews();
        Long blogReviewCount = blogReviewHelper.countBlogReviews();
        return reviewMapper.toGetReviewCountResponseDto(reviewCount, blogReviewCount);
    }
}
