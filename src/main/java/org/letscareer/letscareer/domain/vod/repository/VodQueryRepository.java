package org.letscareer.letscareer.domain.vod.repository;

import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.vod.vo.VodDetailVo;
import org.letscareer.letscareer.domain.vod.vo.VodProfileVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VodQueryRepository {
    Optional<VodDetailVo> findVodDetailVo(Long vodId);
    Page<VodProfileVo> findVodProfileVos(ProgramClassification type, Pageable pageable);
    List<VodDetailVo> findAllVodRecommendVos();
}
