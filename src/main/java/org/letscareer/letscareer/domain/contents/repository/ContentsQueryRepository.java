package org.letscareer.letscareer.domain.contents.repository;

import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminSimpleVo;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContentsQueryRepository {
    Page<ContentsAdminVo> findAllContentsAdminVos(Pageable pageable);

    List<ContentsAdminSimpleVo> findAllContentsAdminSimpleVos(ContentsType contentsType);
}
