package org.letscareer.letscareer.domain.admincalssification.repository;

import org.letscareer.letscareer.domain.admincalssification.vo.VodAdminClassificationDetailVo;

import java.util.List;

public interface VodAdminClassificationQueryRepository {
    List<VodAdminClassificationDetailVo> findVodClassificationDetailVos(Long vodId);
}
