package org.letscareer.letscareer.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewForAdminResponseDto;
import org.letscareer.letscareer.domain.review.entity.ReportReview;
import org.letscareer.letscareer.domain.review.helper.ReportReviewHelper;
import org.letscareer.letscareer.domain.review.helper.ReviewItemHelper;
import org.letscareer.letscareer.domain.review.type.ReviewQuestionType;
import org.letscareer.letscareer.domain.review.vo.CreateReviewItemVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.letscareer.letscareer.domain.review.error.ReviewErrorCode.REVIEW_ALREADY_EXISTS;
import static org.letscareer.letscareer.global.error.GlobalErrorCode.UNAUTHORIZED;

@RequiredArgsConstructor
@Transactional
@Service("REPORT_REVIEW")
public class ReportReviewServiceImpl implements ReviewService {
    private final ReportReviewHelper reportReviewHelper;
    private final ReportApplicationHelper reportApplicationHelper;
    private final ReviewItemHelper reviewItemHelper;

    @Override
    public GetReviewForAdminResponseDto getReviewForAdmin() {
        return null;
    }

    @Override
    public void createReview(User user, Long applicationId, CreateReviewRequestDto requestDto) {
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(applicationId);
        validateCreateReviewCondition(user, reportApplication, requestDto);
        Report report = reportApplication.getReport();
        ReportReview reportReview = reportReviewHelper.createReportReviewAndSave(report, reportApplication, requestDto);
        createReviewItemListAndSave(reportReview, reportApplication.getMessage(), requestDto.reviewItemList());
    }

    @Override
    public void updateReview(Long reviewId, UpdateReviewRequestDto requestDto) {
        ReportReview reportReview = reportReviewHelper.findReportReviewByReviewIdOrThrow(reviewId);
        reportReview.updateReview(requestDto);
    }

    private void validateCreateReviewCondition(User currentUser, ReportApplication reportApplication, CreateReviewRequestDto requestDto) {
        if(!Objects.equals(currentUser.getId(), reportApplication.getUser().getId())) {
            throw new UnauthorizedException(UNAUTHORIZED);
        }

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
