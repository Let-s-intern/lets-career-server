package org.letscareer.letscareer.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetMyReviewResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewForAdminResponseDto;
import org.letscareer.letscareer.domain.review.entity.ReportReview;
import org.letscareer.letscareer.domain.review.helper.ReportReviewHelper;
import org.letscareer.letscareer.domain.review.helper.ReviewItemHelper;
import org.letscareer.letscareer.domain.review.mapper.ReviewMapper;
import org.letscareer.letscareer.domain.review.type.ReviewQuestionType;
import org.letscareer.letscareer.domain.review.vo.*;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.letscareer.letscareer.domain.review.error.ReviewErrorCode.REVIEW_ALREADY_EXISTS;
import static org.letscareer.letscareer.global.error.GlobalErrorCode.UNAUTHORIZED;

@RequiredArgsConstructor
@Transactional
@Service("REPORT_REVIEW")
public class ReportReviewServiceImpl implements ReviewService {
    private final ReportReviewHelper reportReviewHelper;
    private final ReportApplicationHelper reportApplicationHelper;
    private final ReviewItemHelper reviewItemHelper;
    private final ReviewMapper reviewMapper;

    @Override
    public GetReviewForAdminResponseDto getReviewsForAdmin() {
        List<ReviewAdminVo> reviewAdminVos = reportReviewHelper.findAllReportReviewAdminVos().stream()
                .map(reportReviewAdminVo -> reviewMapper.toReviewAdminVo(
                        reportReviewAdminVo,
                        reviewItemHelper.findAllReviewItemAdminVosByReviewId(reportReviewAdminVo.reviewId())
                ))
                .collect(Collectors.toList());
        return reviewMapper.toGetReviewForAdminResponseDto(reviewAdminVos);
    }

    @Override
    public GetMyReviewResponseDto getReview(Long reviewId, User user) {
        ReportReviewVo reportReviewVo = reportReviewHelper.findReportReviewVoOrThrow(reviewId);
        validateAuthorizedUser(user, reportReviewVo.userId());
        List<ReviewItemVo> reviewItemVos = reviewItemHelper.findAllReviewItemVosByReviewId(reviewId, null);
        ReviewMyVo reviewInfo = reviewMapper.toReviewMyVo(reportReviewVo, reviewItemVos);
        return reviewMapper.toGetMyReviewResponseDto(reviewInfo);
    }

    @Override
    public void createReview(User user, Long applicationId, CreateReviewRequestDto requestDto) {
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(applicationId);
        validateCreateReviewCondition(user, reportApplication);
        Report report = reportApplication.getReport();
        ReportReview reportReview = reportReviewHelper.createReportReviewAndSave(report, reportApplication, requestDto);
        createReviewItemListAndSave(reportReview, reportApplication.getMessage(), requestDto.reviewItemList());
    }

    @Override
    public void updateReview(Long reviewId, UpdateReviewRequestDto requestDto) {
        ReportReview reportReview = reportReviewHelper.findReportReviewByReviewIdOrThrow(reviewId);
        reportReview.updateReview(requestDto);
    }

    private void validateCreateReviewCondition(User currentUser, ReportApplication reportApplication) {
        validateAuthorizedUser(currentUser, reportApplication.getUser().getId());
        validateReviewAlreadyExists(reportApplication);
    }

    private void validateAuthorizedUser(User currentUser, Long applicationUserId) {
        if(!Objects.equals(currentUser.getId(), applicationUserId)) {
            throw new UnauthorizedException(UNAUTHORIZED);
        }
    }

    private void validateReviewAlreadyExists(ReportApplication reportApplication) {
        if(!Objects.isNull(reportApplication.getReview())) {
            throw new ConflictException(REVIEW_ALREADY_EXISTS);
        }
    }

    private void createReviewItemListAndSave(ReportReview reportReview, String message, List<CreateReviewItemVo> reviewItemList) {
        if(!Objects.isNull(message) && !message.isBlank()) {
            CreateReviewItemVo worryReviewItemVo = new CreateReviewItemVo(ReviewQuestionType.WORRY, message);
            reviewItemHelper.createReviewItemAndSave(reportReview, worryReviewItemVo);
        }

        reviewItemList.forEach(createReviewItemVo -> {
            reviewItemHelper.createReviewItemAndSave(reportReview, createReviewItemVo);
        });
    }
}
