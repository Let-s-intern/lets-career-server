package org.letscareer.letscareer.domain.application.vo;

import java.util.List;

public record AdminChallengeApplicationWithOptionsVo(
        AdminChallengeApplicationVo application,
        List<String> optionCodes
) {
}
