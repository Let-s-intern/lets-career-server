package org.letscareer.letscareer.domain.application.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.utils.toss.TossFeignController;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service("TOSS")
public class TossApplicationServiceImpl implements ApplicationService{
    private final TossFeignController tossFeignController;

    @Override
    public void createApplication(Long programId, User user, CreateApplicationRequestDto requestDto) {
        System.out.println(tossFeignController.createPayments());
    }

    @Override
    public void deleteApplication(Long applicationId, User user) {

    }
}
