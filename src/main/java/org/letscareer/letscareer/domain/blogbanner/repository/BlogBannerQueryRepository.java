package org.letscareer.letscareer.domain.blogbanner.repository;

import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerDetailVo;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerVo;

import java.util.List;
import java.util.Optional;

public interface BlogBannerQueryRepository {
    List<AdminBlogBannerVo> findAdminBlogBannerVos();
    Optional<AdminBlogBannerDetailVo> findAdminBlogBannerDetailVoById(Long blogBannerId);
}
