package org.letscareer.letscareer.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.service.UserParticipationService;
import org.letscareer.letscareer.domain.user.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/admin/user")
@RequiredArgsConstructor
public class UserV2AdminController {
    private final UserService userService;
    private final UserParticipationService userParticipationService;


}
