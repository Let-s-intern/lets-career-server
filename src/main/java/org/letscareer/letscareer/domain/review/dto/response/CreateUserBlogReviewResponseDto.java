package org.letscareer.letscareer.domain.review.dto.response;

public record CreateUserBlogReviewResponseDto(
        Long blogReviewId,
        Long attendanceId,
        String message
) {
    public static CreateUserBlogReviewResponseDto of(Long blogReviewId, Long attendanceId) {
        return new CreateUserBlogReviewResponseDto(
                blogReviewId,
                attendanceId,
                "블로그 후기가 성공적으로 제출되었습니다. 관리자 승인 후 공개됩니다."
        );
    }
}