package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.dto.request.CreateOldReviewRequestDto;
import org.letscareer.letscareer.domain.review.entity.old.OldReview;
import org.letscareer.letscareer.domain.review.repository.old.OldReviewRepository;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewDetailVo;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.review.error.ReviewErrorCode.REVIEW_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class OldReviewHelper {
    private final OldReviewRepository oldReviewRepository;

    public OldReview createReviewAndSave(Application application, CreateOldReviewRequestDto reviewRequestDto) {
        OldReview review = OldReview.createReview(application, reviewRequestDto);
        return oldReviewRepository.save(review);
    }

    public OldReview createReviewByLinkAndSave(Long programId, ProgramType programType, CreateOldReviewRequestDto requestDto) {
        OldReview review = OldReview.createReviewByLink(programId, programType, requestDto);
        return oldReviewRepository.save(review);
    }

    public OldReview findReviewOrThrow(Long reviewId) {
        return oldReviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException(REVIEW_NOT_FOUND));
    }

    public OldReviewDetailVo findReviewDetailVoOrThrow(Long reviewId) {
        return oldReviewRepository.findReviewVo(reviewId)
                .orElseThrow(() -> new EntityNotFoundException(REVIEW_NOT_FOUND));
    }

    public Page<OldReviewAdminVo> findChallengeReviewAdminVos(Long challengeId, Pageable pageable) {
        return oldReviewRepository.findChallengeReviewAdminVos(challengeId, pageable);
    }

    public Page<OldReviewVo> findChallengeReviewVos(Pageable pageable) {
        return oldReviewRepository.findChallengeReviewVos(pageable);
    }

    public Page<OldReviewAdminVo> findLiveReviewAdminVos(Long liveId, Pageable pageable) {
        return oldReviewRepository.findLiveReviewAdminVos(liveId, pageable);
    }

    public Page<OldReviewVo> findLiveReviewVos(Pageable pageable) {
        return oldReviewRepository.findLiveReviewVos(pageable);
    }


    public List<String> findLiveReviewContentByLiveId(Long liveId) {
        return oldReviewRepository.findReviewContentByLiveId(liveId);
    }

    public List<OldReviewAdminVo> findReviewAdminVos(Boolean isVisible, ProgramType programType, List<String> sortBy) {
        return oldReviewRepository.findAllReviewAdminVosByProgramType(isVisible, programType, sortBy);
    }
}
