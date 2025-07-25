package org.letscareer.letscareer.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.file.type.FileType;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.dto.request.CreateBlogReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.CreateUserBlogReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateBlogReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.CreateUserBlogReviewResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetBlogReviewForAdminResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetBlogReviewResponseDto;
import org.letscareer.letscareer.domain.review.entity.BlogReview;
import org.letscareer.letscareer.domain.review.helper.BlogReviewHelper;
import org.letscareer.letscareer.domain.review.mapper.ReviewMapper;
import org.letscareer.letscareer.domain.review.vo.BlogReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.BlogReviewOpenGraphVo;
import org.letscareer.letscareer.domain.review.vo.BlogReviewVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.letscareer.letscareer.global.common.utils.aws.S3Utils;
import org.letscareer.letscareer.global.common.utils.opengraph.OpenGraphUtils;
import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.attendance.helper.AttendanceHelper;
import org.letscareer.letscareer.domain.attendance.dto.request.CreateAttendanceRequestDto;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;
import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.mission.helper.MissionHelper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private final AttendanceHelper attendanceHelper;
    private final MissionHelper missionHelper;

    @Override
    public GetBlogReviewResponseDto getBlogReviews(List<ProgramType> typeList, Pageable pageable) {
        Page<BlogReviewVo> blogReviewVos = blogReviewHelper.findAllBlogReviewVos(typeList, pageable);
        PageInfo pageInfo = PageInfo.of(blogReviewVos);
        return reviewMapper.toGetBlogReviewResponseDto(blogReviewVos.getContent(), pageInfo);
    }

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
            blogReview.updateThumbnail(thumbnail);
        }
    }

    @Override
    public void updateBlogReview(Long blogReviewId, UpdateBlogReviewRequestDto requestDto) {
        BlogReview blogReview = blogReviewHelper.findBlogReviewByBlogReviewIdOrThrow(blogReviewId);
        blogReview.updateBlogReview(requestDto);
        if(checkBlogReviewUrlUpdateCondition(blogReview, requestDto)) {
            BlogReviewOpenGraphVo openGraphVo = openGraphUtils.getBlogReviewOpenGraphVo(requestDto.url());
            String thumbnail = s3Utils.saveImgFromUrl(openGraphVo.image(), FileType.BLOG_REVIEW.getDesc(), blogReview.getId() + openGraphVo.title()).getUrl();
            blogReview.updateBlogReviewByUrl(openGraphVo, thumbnail);
        }
    }

    @Override
    public void deleteBlogReview(Long blogReviewId) {
        BlogReview blogReview = blogReviewHelper.findBlogReviewByBlogReviewIdOrThrow(blogReviewId);
        s3Utils.deleteFile(FileType.BLOG_REVIEW.getDesc() + blogReview.getId() + blogReview.getTitle());
        blogReviewHelper.deleteBlogReview(blogReview);
    }

    @Override
    public CreateUserBlogReviewResponseDto createUserBlogReview(CreateUserBlogReviewRequestDto requestDto, User user) {
        Mission mission = missionHelper.findMissionByIdOrThrow(requestDto.missionId());
        
        CreateAttendanceRequestDto attendanceRequestDto = new CreateAttendanceRequestDto(
                requestDto.url(), 
                "보너스 미션 블로그 후기 제출"
        );
        
        Attendance attendance = Attendance.createAttendance(
                mission, 
                attendanceRequestDto, 
                AttendanceStatus.PRESENT, 
                user, 
                AttendanceResult.PASS
        );
        attendance.updateAccountInfo(requestDto.accountType(), requestDto.accountNumber());
        
        attendance = attendanceHelper.saveAttendance(attendance);
        BlogReviewOpenGraphVo openGraphVo = openGraphUtils.getBlogReviewOpenGraphVo(requestDto.url());
        
        CreateBlogReviewRequestDto blogReviewRequestDto = new CreateBlogReviewRequestDto(
                ProgramType.CHALLENGE,
                mission.getChallenge().getTitle(),
                user.getName(),
                requestDto.url(),
                LocalDateTime.now()
        );
        
        BlogReview blogReview = blogReviewHelper.createBonusBlogReviewAndSave(blogReviewRequestDto, openGraphVo, attendance);
        
        if (!Objects.isNull(openGraphVo.image())) {
            String thumbnail = s3Utils.saveImgFromUrl(openGraphVo.image(), FileType.BLOG_REVIEW.getDesc(), 
                    blogReview.getId() + openGraphVo.title()).getUrl();
            blogReview.updateThumbnail(thumbnail);
        }
        
        return CreateUserBlogReviewResponseDto.of(blogReview.getId(), attendance.getId());
    }

    private boolean checkBlogReviewUrlUpdateCondition(BlogReview blogReview, UpdateBlogReviewRequestDto requestDto) {
        if(Objects.isNull(requestDto.url()) || requestDto.url().isBlank()) return false;
        if(Objects.equals(blogReview.getUrl(), requestDto.url())) return false;
        return true;
    }
}
