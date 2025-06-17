package org.letscareer.letscareer.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.dto.response.MentorListResponseDto;
import org.letscareer.letscareer.domain.user.dto.response.UserAdminListResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.service.UserService;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/admin/user")
@RequiredArgsConstructor
public class UserV2AdminController {
    private final UserService userService;

    @Operation(summary = "유저 전체 목록", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserAdminListResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.IS_NOT_ADMIN})
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getUsersForAdmin(@RequestParam(required = false) String email,
                                                               @RequestParam(required = false) String name,
                                                               @RequestParam(required = false) String phoneNum,
                                                               @RequestParam(required = false) String role,
                                                               @PageableDefault Pageable pageable) {
        final UserAdminListResponseDto responseDto = userService.getUsers(email, name, phoneNum, role, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "멘토 전체 목록", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = MentorListResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.IS_NOT_ADMIN})
    @GetMapping("/mentor")
    public ResponseEntity<SuccessResponse<?>> getMentorsForAdmin() {
        final MentorListResponseDto responseDto = userService.getMentors();
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "유저 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.IS_NOT_ADMIN, SwaggerEnum.USER_NOT_FOUND})
    @DeleteMapping
    public ResponseEntity<SuccessResponse<?>> deleteUserForAdmin(@CurrentUser User user,
                                                                 @RequestParam String number) {
        userService.deleteUserForAdmin(user, number);
        return SuccessResponse.ok(null);
    }
}
