package org.letscareer.letscareer.domain.blog.repository;

import org.letscareer.letscareer.domain.blog.vo.HashTagDetailInfo;

import java.util.List;

public interface HashTagQueryRepository {
    List<HashTagDetailInfo> findAllHashTagDetailInfo(Long blogId);
}
