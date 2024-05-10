package org.letscareer.letscareer.domain.classification.repository;

import org.letscareer.letscareer.domain.classification.vo.VodClassificationDetailVo;

import java.util.List;

public interface VodClassificationQueryRepository {
    List<VodClassificationDetailVo> findVodClassificationDetailVos(Long vodId);
}
