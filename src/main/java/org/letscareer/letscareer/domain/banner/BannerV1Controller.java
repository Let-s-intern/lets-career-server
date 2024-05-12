package org.letscareer.letscareer.domain.banner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
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

    @Operation(summary = "배너 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createBanner(@RequestParam(name = "type") final BannerType bannerType,
                                                           @RequestBody final CreateBannerRequestDto createBannerRequestDto) {
        bannerServiceFactory.getBannerService(bannerType).createBanner(bannerType, createBannerRequestDto);
        return SuccessResponse.created(null);
    }
}
