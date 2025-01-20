package org.letscareer.letscareer.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.attendance.helper.AttendanceHelper;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewForAdminResponseDto;
import org.letscareer.letscareer.domain.review.mapper.ReviewMapper;
import org.letscareer.letscareer.domain.review.vo.ReviewAdminVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service("MISSION_REVIEW")
public class MissionReviewServiceImpl implements ReviewService {
    private final AttendanceHelper attendanceHelper;
    private final ReviewMapper reviewMapper;

    @Override
    public GetReviewForAdminResponseDto getReviewsForAdmin() {
        List<ReviewAdminVo> reviewAdminVos = attendanceHelper.findAllMissionReviewAdminVos().stream()
                .map(missionReviewAdminVo -> reviewMapper.toReviewAdminVo(missionReviewAdminVo, new ArrayList<>()))
                .toList();
        return reviewMapper.toGetReviewForAdminResponseDto(reviewAdminVos);
    }

    @Override
    public void createReview(User user, Long applicationId, CreateReviewRequestDto requestDto) {
    }

    @Override
    public void updateReview(Long reviewId, UpdateReviewRequestDto requestDto) {
    }
}
