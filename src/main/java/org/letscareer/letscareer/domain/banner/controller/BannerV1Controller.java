package org.letscareer.letscareer.domain.banner.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerAdminListResponseDto;
import org.letscareer.letscareer.domain.banner.service.BannerServiceFactory;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/banner")
@RestController
public class BannerV1Controller {
    private final BannerServiceFactory bannerServiceFactory;

    @Operation(summary = "타입별 배너 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createBanner(@RequestParam(name = "type") final BannerType bannerType,
                                                           @RequestBody final CreateBannerRequestDto createBannerRequestDto) {
        bannerServiceFactory.getBannerService(bannerType).createBanner(bannerType, createBannerRequestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "배너 타입별 전체 목록", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BannerAdminListResponseDto.class)))
    })
    @GetMapping("/admin")
    public ResponseEntity<SuccessResponse<?>> getBannersForAdmin(@RequestParam(name = "type") final BannerType bannerType) {
        BannerAdminListResponseDto responseDto = bannerServiceFactory.getBannerService(bannerType).getBannersForAdmin();
        return SuccessResponse.ok(responseDto);
    }
}
