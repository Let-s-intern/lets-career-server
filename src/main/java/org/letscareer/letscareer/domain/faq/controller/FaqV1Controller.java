package org.letscareer.letscareer.domain.faq.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.faq.dto.request.CreateFaqRequestDto;
import org.letscareer.letscareer.domain.faq.dto.response.GetFaqResponseDto;
import org.letscareer.letscareer.domain.faq.service.FaqService;
import org.letscareer.letscareer.domain.faq.type.FaqProgramType;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/faq")
@RestController
public class FaqV1Controller {
    private final FaqService faqService;

    @Operation(summary = "program type에 해당하는 Faq 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetFaqResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getFaqs(@RequestParam final FaqProgramType type) {
        GetFaqResponseDto responseDto = faqService.getFaqs(type);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "Faq 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createFaq(@RequestBody final CreateFaqRequestDto requestDto) {
        faqService.creatFaq(requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "Faq 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{faqId}")
    public ResponseEntity<SuccessResponse<?>> updateFaq(@PathVariable final Long faqId,
                                                        @RequestBody final CreateFaqRequestDto requestDto) {
        faqService.updateFaq(faqId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "Faq 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping("/{faqId}")
    public ResponseEntity<SuccessResponse<?>> deleteFaq(@PathVariable final Long faqId) {
        faqService.deleteFaq(faqId);
        return SuccessResponse.ok(null);
    }
}
