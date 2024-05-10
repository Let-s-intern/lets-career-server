package org.letscareer.letscareer.domain.vod.repository;

import org.letscareer.letscareer.domain.vod.vo.VodDetailVo;

import java.util.Optional;

public interface VodQueryRepository {
    Optional<VodDetailVo> findVodDetailVo(Long vodId);
}
