package org.letscareer.letscareer.domain.blogbanner.repository;

import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerVo;

import java.util.List;

public interface BlogBannerQueryRepository {
    List<AdminBlogBannerVo> findAdminBlogBannerVos();
}
