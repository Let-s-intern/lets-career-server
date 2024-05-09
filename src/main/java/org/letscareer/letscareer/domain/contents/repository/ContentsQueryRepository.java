package org.letscareer.letscareer.domain.contents.repository;

import org.letscareer.letscareer.domain.contents.vo.ContentsAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContentsQueryRepository {
    Page<ContentsAdminVo> findAllContentsAdminVos(Pageable pageable);
}
