package org.letscareer.letscareer.domain.banner.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.request.UpdateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerDetailResponseDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerListResponseDto;
import org.letscareer.letscareer.domain.banner.service.BannerServiceFactory;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/banner")
@RestController
public class BannerV1Controller {
    private final BannerServiceFactory bannerServiceFactory;

    @Operation(summary = "노출 배너 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BannerListResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getBanners(@RequestParam(name = "type") final BannerType bannerType) {
        final BannerListResponseDto responseDto = bannerServiceFactory.getBannerService(bannerType).getBanners();
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 배너 상세 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BannerDetailResponseDto.class)))
    })
    @GetMapping("/admin/{id}")
    public ResponseEntity<SuccessResponse<?>> getBannerDetail(@PathVariable(name = "id") final Long bannerId,
                                                              @RequestParam(name = "type") final BannerType bannerType) {
        final BannerDetailResponseDto responseDto = bannerServiceFactory.getBannerService(bannerType).getBannerDetail(bannerId);
        return SuccessResponse.ok(responseDto);
    }


    @Operation(summary = "[어드민] 타입별 배너 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createBanner(@RequestParam(name = "type") final BannerType bannerType,
                                                           @RequestBody final CreateBannerRequestDto createBannerRequestDto) {
        bannerServiceFactory.getBannerService(bannerType).createBanner(bannerType, createBannerRequestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "[어드민] 타입별 배너 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> updateBanner(@PathVariable(name = "id") final Long bannerId,
                                                           @RequestParam(name = "type") final BannerType bannerType,
                                                           @RequestBody final UpdateBannerRequestDto updateBannerRequestDto) {
        bannerServiceFactory.getBannerService(bannerType).updateBanner(bannerId, updateBannerRequestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "[어드민] 배너 타입별 전체 목록", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BannerListResponseDto.class)))
    })
    @GetMapping("/admin")
    public ResponseEntity<SuccessResponse<?>> getBannersForAdmin(@RequestParam(name = "type") final BannerType bannerType) {
        final BannerListResponseDto responseDto = bannerServiceFactory.getBannerService(bannerType).getBannersForAdmin();
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 배너 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> deleteBanner(@PathVariable(name = "id") final Long bannerId,
                                                           @RequestParam(name = "type") final BannerType bannerType) {
        bannerServiceFactory.getBannerService(bannerType).deleteBanner(bannerId);
        return SuccessResponse.ok(null);
    }
}
