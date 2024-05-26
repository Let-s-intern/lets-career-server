package org.letscareer.letscareer.domain.challenge.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.response.GetChallengeApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.helper.ChallengeApplicationHelper;
import org.letscareer.letscareer.domain.application.mapper.ChallengeApplicationMapper;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeDetailResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeReviewResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengesResponseDto;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.challenge.mapper.ChallengeMapper;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeDetailVo;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeProfileVo;
import org.letscareer.letscareer.domain.classification.dto.request.CreateChallengeClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.helper.ChallengeClassificationHelper;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.classification.vo.ChallengeClassificationDetailVo;
import org.letscareer.letscareer.domain.faq.dto.request.CreateProgramFaqRequestDto;
import org.letscareer.letscareer.domain.faq.entity.Faq;
import org.letscareer.letscareer.domain.faq.helper.FaqHelper;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.price.dto.request.CreateChallengePriceRequestDto;
import org.letscareer.letscareer.domain.price.helper.ChallengePriceHelper;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.review.helper.ReviewHelper;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ChallengeServiceImpl implements ChallengeService {
    private final ChallengeHelper challengeHelper;
    private final ChallengeMapper challengeMapper;
    private final ChallengeClassificationHelper challengeClassificationHelper;
    private final ChallengeApplicationHelper challengeApplicationHelper;
    private final ChallengeApplicationMapper challengeApplicationMapper;
    private final ChallengePriceHelper challengePriceHelper;
    private final ReviewHelper reviewHelper;
    private final FaqHelper faqHelper;

    @Override
    public GetChallengesResponseDto getChallengeList(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, Pageable pageable) {
        Page<ChallengeProfileVo> challengeProfileVos = challengeHelper.findChallengeProfiles(typeList, statusList, pageable);
        return challengeMapper.toGetChallengesResponseDto(challengeProfileVos);
    }

    @Override
    public GetChallengeDetailResponseDto getChallengeDetail(Long challengeId) {
        ChallengeDetailVo challengeDetailVo = challengeHelper.findChallengeDetailByIdOrThrow(challengeId);
        List<ChallengeClassificationDetailVo> classificationInfo = challengeClassificationHelper.findClassificationDetailVos(challengeId);
        List<ChallengePriceDetailVo> priceInfo = challengePriceHelper.findChallengePriceDetailVos(challengeId);
        List<FaqDetailVo> faqInfo = faqHelper.findChallengeFaqDetailVos(challengeId);
        return challengeMapper.toChallengeDetailResponseDto(challengeDetailVo, classificationInfo, priceInfo, faqInfo);
    }

    @Override
    public GetChallengeApplicationsResponseDto getApplications(Long challengeId, Boolean isConfirmed) {
        List<AdminChallengeApplicationVo> applicationVos = challengeApplicationHelper.findAdminChallengeApplicationVos(challengeId, isConfirmed);
        return challengeApplicationMapper.toGetChallengeApplicationsResponseDto(applicationVos);
    }

    @Override
    public GetChallengeReviewResponseDto getReviews(Long challengeId, Pageable pageable) {
        Page<ReviewVo> challengeReviewVos = reviewHelper.findChallengeReviewVos(challengeId, pageable);
        return challengeMapper.toGetChallengeReviewResponseDto(challengeReviewVos);
    }

    @Override
    public void createChallenge(CreateChallengeRequestDto createChallengeRequestDto) {
        Challenge challenge = challengeHelper.createChallengeAndSave(createChallengeRequestDto);
        createClassificationListAndSave(createChallengeRequestDto.programTypeInfo(), challenge);
        createPriceListAndSave(createChallengeRequestDto.priceInfo(), challenge);
        createFaqListAndSave(createChallengeRequestDto.faqInfo(), challenge);
    }

    @Override
    public void updateChallenge(Long challengeId, CreateChallengeRequestDto createChallengeRequestDto) {
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(challengeId);
        challenge.updateChallenge(createChallengeRequestDto);
        updateChallengeClassifications(challenge, createChallengeRequestDto.programTypeInfo());
        updateChallengePrices(challenge, createChallengeRequestDto.priceInfo());
        updateChallengeFaqs(challenge, createChallengeRequestDto.faqInfo());
    }

    @Override
    public void deleteChallenge(Long challengeId) {
        challengeHelper.deleteChallengeById(challengeId);
    }

    private void createClassificationListAndSave(List<CreateChallengeClassificationRequestDto> requestDtoList,
                                                 Challenge challenge) {
        requestDtoList.stream()
                .map(requestDto -> challengeClassificationHelper.createChallengeClassificationAndSave(requestDto, challenge))
                .collect(Collectors.toList());
    }

    private void createPriceListAndSave(List<CreateChallengePriceRequestDto> requestDtoList,
                                        Challenge challenge) {
        requestDtoList.stream()
                .map(requestDto -> challengePriceHelper.createChallengePriceAndSave(requestDto, challenge))
                .collect(Collectors.toList());
    }

    private void createFaqListAndSave(List<CreateProgramFaqRequestDto> requestDtoList,
                                      Challenge challenge) {
        List<Faq> faqs = getFaqsById(requestDtoList);
        faqs.stream().forEach(faq -> faqHelper.createFaqChallengeAndSave(faq, challenge));
    }

    private void updateChallengeClassifications(Challenge challenge, List<CreateChallengeClassificationRequestDto> programInfo) {
        if (Objects.isNull(programInfo)) return;
        challengeClassificationHelper.deleteChallengeClassificationsByChallengeId(challenge.getId());
        challenge.setInitClassificationList();
        createClassificationListAndSave(programInfo, challenge);
    }

    private void updateChallengePrices(Challenge challenge, List<CreateChallengePriceRequestDto> priceInfo) {
        if (Objects.isNull(priceInfo)) return;
        challengePriceHelper.deleteChallengePricesByChallengeId(challenge.getId());
        challenge.setInitPriceList();
        createPriceListAndSave(priceInfo, challenge);
    }

    private void updateChallengeFaqs(Challenge challenge, List<CreateProgramFaqRequestDto> faqInfo) {
        if (Objects.isNull(faqInfo)) return;
        faqHelper.deleteChallengeFaqsByChallengeId(challenge.getId());
        challenge.setInitFaqList();
        createFaqListAndSave(faqInfo, challenge);
    }

    private List<Faq> getFaqsById(List<CreateProgramFaqRequestDto> requestDtoList) {
        return requestDtoList.stream()
                .map(request -> faqHelper.findFaqByIdAndThrow(request.faqId()))
                .collect(Collectors.toList());
    }
}
