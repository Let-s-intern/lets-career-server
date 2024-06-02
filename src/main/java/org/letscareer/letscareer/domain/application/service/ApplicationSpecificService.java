package org.letscareer.letscareer.domain.application.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.response.GetMyApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.helper.ApplicationHelper;
import org.letscareer.letscareer.domain.application.mapper.ApplicationMapper;
import org.letscareer.letscareer.domain.application.type.ApplicationReviewStatus;
import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplicationSpecificService {
    private final ApplicationHelper applicationHelper;
    private final ApplicationMapper applicationMapper;

    public GetMyApplicationsResponseDto getMyApplications(User user, ApplicationStatus status, ApplicationReviewStatus reviewStatus) {
        List<MyApplicationVo> applicationList = applicationHelper.getMyApplications(user.getId(), status, reviewStatus);
        return applicationMapper.toGetMyApplicationsResponseDto(applicationList);
    }
}
