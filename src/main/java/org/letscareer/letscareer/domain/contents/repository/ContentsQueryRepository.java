package org.letscareer.letscareer.domain.contents.repository;

import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminSimpleVo;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminVo;
import org.letscareer.letscareer.domain.contents.vo.ContentsDetailVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ContentsQueryRepository {
    Page<ContentsAdminVo> findAllContentsAdminVos(Pageable pageable);

    List<ContentsAdminSimpleVo> findAllContentsAdminSimpleVos(ContentsType contentsType);

    Optional<ContentsDetailVo> findContentsDetailVo(Long contentsId);
}
