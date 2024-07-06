package org.letscareer.letscareer.domain.coupon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponRequestDto;
import org.letscareer.letscareer.domain.coupon.dto.response.CouponApplyResponseDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponDetailResponseDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponsResponseDto;
import org.letscareer.letscareer.domain.coupon.service.CouponService;
import org.letscareer.letscareer.domain.coupon.type.CouponProgramType;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/coupon")
@RestController
public class CouponV1Controller {
    private final CouponService couponService;

    @Operation(summary = "쿠폰 적용", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CouponApplyResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.COUPON_NOT_FOUND, SwaggerEnum.COUPON_NOT_AVAILABLE_DATE, SwaggerEnum.COUPON_NOT_AVAILABLE_PROGRAM_TYPE, SwaggerEnum.COUPON_NOT_AVAILABLE_TIME})
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> applyCoupon(@CurrentUser final User user,
                                                          @RequestParam("code") final String code,
                                                          @RequestParam("programType") final CouponProgramType programType) {
        CouponApplyResponseDto responseDto = couponService.applyCoupon(user, code, programType);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 쿠폰 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetCouponsResponseDto.class)))
    })
    @GetMapping("/admin")
    public ResponseEntity<SuccessResponse<?>> getCoupons() {
        GetCouponsResponseDto responseDto = couponService.getCoupons();
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 쿠폰 상세 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetCouponDetailResponseDto.class)))
    })
    @GetMapping("/admin/{couponId}")
    public ResponseEntity<SuccessResponse<?>> getCouponDetail(@PathVariable final Long couponId) {
        GetCouponDetailResponseDto responseDto = couponService.getCouponDetail(couponId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 쿠폰 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createCoupon(@RequestBody final CreateCouponRequestDto requestDto) {
        couponService.createCoupon(requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "[어드민] 쿠폰 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{couponId}")
    public ResponseEntity<SuccessResponse<?>> updateCoupon(@PathVariable final Long couponId,
                                                           @RequestBody final CreateCouponRequestDto requestDto) {
        couponService.updateCoupon(couponId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "[어드민] 쿠폰 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping("/{couponId}")
    public ResponseEntity<SuccessResponse<?>> deleteCoupon(@PathVariable final Long couponId) {
        couponService.deleteCoupon(couponId);
        return SuccessResponse.ok(null);
    }
}
