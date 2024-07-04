package org.letscareer.letscareer.global.common.entity;

import org.letscareer.letscareer.domain.application.error.ApplicationErrorCode;
import org.letscareer.letscareer.domain.attendance.error.AttendanceErrorCode;
import org.letscareer.letscareer.domain.banner.error.BannerErrorCode;
import org.letscareer.letscareer.domain.challenge.error.ChallengeErrorCode;
import org.letscareer.letscareer.domain.challengeguide.error.ChallengeGuideErrorCode;
import org.letscareer.letscareer.domain.classification.error.ChallengeClassificationErrorCode;
import org.letscareer.letscareer.domain.contents.error.ContentsErrorCode;
import org.letscareer.letscareer.domain.coupon.error.CouponErrorCode;
import org.letscareer.letscareer.domain.faq.error.FaqErrorCode;
import org.letscareer.letscareer.domain.live.error.LiveErrorCode;
import org.letscareer.letscareer.domain.mission.error.MissionErrorCode;
import org.letscareer.letscareer.domain.missiontemplate.error.MissionTemplateErrorCode;
import org.letscareer.letscareer.domain.payment.error.PaymentErrorCode;
import org.letscareer.letscareer.domain.price.error.ChallengePriceErrorCode;
import org.letscareer.letscareer.domain.price.error.PriceErrorCode;
import org.letscareer.letscareer.domain.score.error.ScoreErrorCode;
import org.letscareer.letscareer.domain.user.error.UserErrorCode;
import org.letscareer.letscareer.domain.vod.error.VodErrorCode;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.letscareer.letscareer.global.error.GlobalErrorCode;

public enum SwaggerEnum {
    // attendance
    ATTENDANCE_NOT_FOUND(AttendanceErrorCode.ATTENDANCE_NOT_FOUND),
    ATTENDANCE_UNAUTHORIZED(AttendanceErrorCode.ATTENDANCE_UNAUTHORIZED),
    CONFLICT_ATTENDANCE(AttendanceErrorCode.CONFLICT_ATTENDANCE),
    ATTENDANCE_NOT_AVAILABLE_DATE(AttendanceErrorCode.ATTENDANCE_NOT_AVAILABLE_DATE),

    // application
    INVALID_APPLICATION_TIME(ApplicationErrorCode.INVALID_APPLICATION_TIME),
    CONFLICT_APPLICATION(ApplicationErrorCode.CONFLICT_APPLICATION),
    LIVE_BAD_REQUEST(ApplicationErrorCode.LIVE_BAD_REQUEST),
    APPLICATION_NOT_FOUND(ApplicationErrorCode.APPLICATION_NOT_FOUND),

    // Banner
    BANNER_NOT_FOUND(BannerErrorCode.BANNER_NOT_FOUND),

    // challenge
    CHALLENGE_NOT_FOUND(ChallengeErrorCode.CHALLENGE_NOT_FOUND),

    // challenge guide
    CHALLENGE_GUIDE_NOT_FOUND(ChallengeGuideErrorCode.CHALLENGE_GUIDE_NOT_FOUND),

    // challenge classification
    CHALLENGE_CLASSIFICATION_NOT_FOUND(ChallengeClassificationErrorCode.CHALLENGE_CLASSIFICATION_NOT_FOUND),

    // coupon
    COUPON_NOT_FOUND(CouponErrorCode.COUPON_NOT_FOUND),
    COUPON_NOT_AVAILABLE_DATE(CouponErrorCode.COUPON_NOT_AVAILABLE_DATE),
    COUPON_NOT_AVAILABLE_PROGRAM_TYPE(CouponErrorCode.COUPON_NOT_AVAILABLE_PROGRAM_TYPE),
    COUPON_NOT_AVAILABLE_TIME(CouponErrorCode.COUPON_NOT_AVAILABLE_TIME),

    // contents
    CONTENTS_NOT_FOUND(ContentsErrorCode.CONTENTS_NOT_FOUND),

    // faq
    FAQ_NOT_FOUND(FaqErrorCode.FAQ_NOT_FOUND),

    // live
    LIVE_NOT_FOUND(LiveErrorCode.LIVE_NOT_FOUND),
    MENTOR_PASSWORD_WRONG(LiveErrorCode.MENTOR_PASSWORD_WRONG),

    // mission
    MISSION_NOT_FOUND(MissionErrorCode.MISSION_NOT_FOUND),
    MISSION_TEMPLATE_NOT_FOUND(MissionTemplateErrorCode.MISSION_TEMPLATE_NOT_FOUND),

    // payment
    PAYMENT_NOT_FOUND(PaymentErrorCode.PAYMENT_NOT_FOUND),

    // price
    CHALLENGE_PRICE_NOT_FOUND(ChallengePriceErrorCode.CHALLENGE_PRICE_NOT_FOUND),
    PRICE_NOT_FOUND(PriceErrorCode.PRICE_NOT_FOUND),
    LIVE_PRICE_NOT_FOUND(PriceErrorCode.LIVE_PRICE_NOT_FOUND),

    // score
    ATTENDANCE_SCORE_NOT_FOUND(ScoreErrorCode.ATTENDANCE_SCORE_NOT_FOUND),

    // vod
    VOD_NOT_FOUND(VodErrorCode.VOD_NOT_FOUND),

    // user
    INVALID_EMAIL(UserErrorCode.INVALID_EMAIL),
    INVALID_PASSWORD(UserErrorCode.INVALID_PASSWORD),
    INVALID_PHONE_NUMBER(UserErrorCode.INVALID_PHONE_NUMBER),
    USER_NOT_FOUND(UserErrorCode.USER_NOT_FOUND),
    USER_PHONE_NUMBER_CONFLICT(UserErrorCode.USER_PHONE_NUMBER_CONFLICT),
    USER_EMAIL_CONFLICT(UserErrorCode.USER_EMAIL_CONFLICT),

    // global
    BAD_REQUEST(GlobalErrorCode.BAD_REQUEST),
    MISMATCH_PASSWORD(GlobalErrorCode.MISMATCH_PASSWORD),
    NOT_REFRESH_TOKEN(GlobalErrorCode.NOT_REFRESH_TOKEN),
    INVALID_TOKEN(GlobalErrorCode.INVALID_TOKEN);

    private final ErrorCode errorCode;

    SwaggerEnum(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
