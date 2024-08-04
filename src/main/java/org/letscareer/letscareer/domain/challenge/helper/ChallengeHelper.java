package org.letscareer.letscareer.domain.challenge.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.error.ChallengeErrorCode;
import org.letscareer.letscareer.domain.challenge.repository.ChallengeRepository;
import org.letscareer.letscareer.domain.challenge.vo.*;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.program.dto.response.ZoomMeetingResponseDto;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.global.common.utils.string.StringUtils;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Component
public class ChallengeHelper {
    private final ChallengeRepository challengeRepository;
    private final MessageSource messageSource;

    public Challenge createChallengeAndSave(CreateChallengeRequestDto challengeRequestDto, ZoomMeetingResponseDto zoomMeetingInfo) {
        Challenge newChallenge = Challenge.createChallenge(challengeRequestDto, zoomMeetingInfo);
        return challengeRepository.save(newChallenge);
    }

    public Challenge findChallengeByIdOrThrow(Long challengeId) {
        return challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(ChallengeErrorCode.CHALLENGE_NOT_FOUND));
    }

    public ChallengeTitleVo findChallengeTitleVoOrThrow(Long challengeId) {
        return challengeRepository.findChallengeTitleVo(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(ChallengeErrorCode.CHALLENGE_NOT_FOUND));
    }

    public ChallengeDetailVo findChallengeDetailByIdOrThrow(Long challengeId) {
        return challengeRepository.findChallengeDetailById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(ChallengeErrorCode.CHALLENGE_NOT_FOUND));
    }

    public Page<ChallengeProfileVo> findChallengeProfiles(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, Pageable pageable) {
        return challengeRepository.findChallengeProfiles(typeList, statusList, pageable);
    }

    public ChallengeApplicationFormVo findChallengeApplicationFormVoOrThrow(Long challengeId) {
        return challengeRepository.findChallengeApplicationFormVo(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(ChallengeErrorCode.CHALLENGE_NOT_FOUND));
    }

    public ChallengeThumbnailVo findChallengeThumbnailOrThrow(Long challengeId) {
        return challengeRepository.findChallengeThumbnailVo(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(ChallengeErrorCode.CHALLENGE_NOT_FOUND));
    }

    public ChallengeContentVo findChallengeContentOrThrow(Long challengeId) {
        return challengeRepository.findChallengeContentVo(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(ChallengeErrorCode.CHALLENGE_NOT_FOUND));
    }

    public List<Long> findRemindNotificationChallengeIds() {
        return challengeRepository.findAllRemindNotificationChallengeId();
    }

    public List<Long> findEndNotificationChallengeIds() {
        return challengeRepository.findAllEndNotificationChallengeId();
    }

    public List<Long> findOTRemindNotificationChallengeIds() {
        return challengeRepository.findAllOTRemindNotificationChallengeId();
    }

    public void deleteChallengeById(Long challengeId) {
        challengeRepository.deleteById(challengeId);
    }

    public String createChallengeMailTitle(Challenge challenge) {
        return "[렛츠커리어] " + challenge.getTitle() + " 참여 확정 공지";
    }

    public String createChallengeMailContents(Challenge challenge) {
        return messageSource.getMessage("mail.header", null, Locale.KOREA) +
                "드디어 내일(" + StringUtils.dateToStringMMdd(challenge.getStartDate()) + ") OT와 함께 인턴/신입 지원 챌린지 20기가 시작합니다!\n" +
                "챌린지 참여에 핵심이 될 공지를 몇가지 드리려 합니다.\n" +
                "안내 사항에 따라 설정 및 접속 부탁 드리겠습니다.\n" +
                "[1] " + challenge.getTitle() + " 카카오톡 오픈 채팅방에 들어와 주세요!\n" +
                "참여자들 간의 소통이 이루어지는 공간입니다.\n" +
                "이때 카카오프렌즈 프로필이 아닌 “새로운 오픈 프로필 만들기“로 가입해주세요!\n" +
                "프로필 우측 하단에 회색 박스가 있으면 됩니다.\n" +
                "사전에 세팅해 주셔야 1:1 대화가 가능합니다.\n" +
                "☑︎ 프로필 이미지 : 자신을 잘 나타낼 수 있는 이미지로 자유롭게\n" +
                "☑︎ 닉네임 : OOO_희망 직무 (ex. 홍길동_콘텐츠 마케팅, 김철수_개발)\n" +
                "☑︎ 채팅 설정 : 기본 프로필만 허용 ‘해제’\n" +
                "채팅 설정은 한번 프로필을 만든 후에는 수정이 어려워,\n" +
                "다시 만들어야 한다는 번거로움이 있어 상기 조건을 한번 더 확인해 주세요.\n" +
                ":링크: 링크: " + challenge.getChatLink() + "\n" +
                ":열쇠와_잠긴_자물쇠: 비밀번호 : " + challenge.getChatPassword() + "\n" +
                "[2] OT는 온라인으로 " + StringUtils.dateToStringMMddEaHHmm(challenge.getStartDate()) + "에 진행됩니다.\n" +
                "1시간 소요 예정으로 렛츠인턴 소개와 챌린지 운영 방식, 보증금 및 혜택을 안내 드립니다.\n" +
                "참여하신 분들만을 위해 커리큘럼을 상세하게 설명 드리오니 최대한 참여해 주세요.\n" +
                ":링크: 링크 : " + challenge.getZoomLink() + "\n" +
                ":열쇠와_잠긴_자물쇠: 비밀번호 : " + challenge.getZoomPassword() + "\n" +
                "[3] 이번 챌린지는 렛츠인턴 웹사이트에서 콘텐츠 및 미션 공지와 인증이 이루어집니다.\n" +
                "신청하신 계정의 마이페이지에서 챌린지 대시보드에 바로 접속하실 수 있습니다.\n" +
                "금일 중으로 확인 부탁 드리며, 접속에 어려움 있으실 경우 회신해 주세요.\n" +
                ":링크: 링크 : https://www.letsintern.co.kr/\n" +
                ":오른쪽을_가리키는_손_모양: 방법 : 로그인 후 마이페이지 → 신청 현황 → 참여 중 섹션 → 챌린지로 이동 클릭\n" +
                challenge.getTitle() + "에 함께 하게 되신 걸 다시 한번 환영합니다.\n" +
                "모두 내일 OT에서 만나요 :미소짓는_상기된_얼굴:\n\n" +
                messageSource.getMessage("mail.footer", null, Locale.KOREA);
    }

}
