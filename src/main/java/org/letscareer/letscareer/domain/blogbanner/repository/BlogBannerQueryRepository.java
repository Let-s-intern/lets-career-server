package org.letscareer.letscareer.domain.blogbanner.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerDetailVo;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerVo;
import org.letscareer.letscareer.domain.blogbanner.vo.BlogBannerVo;

import java.util.List;
import java.util.Optional;

public interface BlogBannerQueryRepository {

    Page<BlogBannerVo> findBlogBannerVos(Pageable pageable);

    List<AdminBlogBannerVo> findAdminBlogBannerVos();
    Optional<AdminBlogBannerDetailVo> findAdminBlogBannerDetailVoById(Long blogBannerId);
}
