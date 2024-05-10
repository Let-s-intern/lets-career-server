package org.letscareer.letscareer.domain.coupon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeDetailResponseDto;
import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponRequestDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponDetailResponseDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponsResponseDto;
import org.letscareer.letscareer.domain.coupon.service.CouponService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/coupon")
@RestController
public class CouponV1Controller {
    private final CouponService couponService;

    @Operation(summary = "쿠폰 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetCouponsResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getCoupons() {
        GetCouponsResponseDto responseDto = couponService.getCoupons();
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "쿠폰 상세 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetCouponDetailResponseDto.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> getCouponDetail(@PathVariable("id") final Long couponId) {
        GetCouponDetailResponseDto responseDto = couponService.getCouponDetail(couponId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "쿠폰 생성", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createCoupon(@RequestBody final CreateCouponRequestDto requestDto) {
        couponService.createCoupon(requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "쿠폰 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> updateCoupon(@PathVariable("id") final Long couponId,
                                                           @RequestBody final CreateCouponRequestDto requestDto) {
        couponService.updateCoupon(couponId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "쿠폰 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> deleteCoupon(@PathVariable("id") final Long couponId) {
        couponService.deleteCoupon(couponId);
        return SuccessResponse.ok(null);
    }
}
