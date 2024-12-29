package org.letscareer.letscareer.domain.faq.repository;

import org.letscareer.letscareer.domain.faq.type.FaqProgramType;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;

import java.util.List;

public interface FaqQueryRepository {
    List<FaqDetailVo> findChallengeFaqDetailVos(Long challengeId);

    List<FaqDetailVo> findLiveFaqDetailVos(Long liveId);

    List<FaqDetailVo> findReportFaqDetailVos(Long reportId);

    List<FaqDetailVo> findFaqDetailVosForType(FaqProgramType type);
}
