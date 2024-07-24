package org.letscareer.letscareer.domain.blog.mapper;

import org.letscareer.letscareer.domain.blog.dto.response.blog.BlogsElementInfo;
import org.letscareer.letscareer.domain.blog.dto.response.blog.GetBlogResponseDto;
import org.letscareer.letscareer.domain.blog.dto.response.blog.GetBlogsResponseDto;
import org.letscareer.letscareer.domain.blog.vo.BlogDetailVo;
import org.letscareer.letscareer.domain.blog.vo.BlogThumbnailVo;
import org.letscareer.letscareer.domain.blog.vo.HashTagDetailInfo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogMapper {
    public GetBlogResponseDto toGetBlogResponseDto(BlogDetailVo blogDetailInfo,
                                                   List<HashTagDetailInfo> tagDetailInfos) {
        return GetBlogResponseDto.of(blogDetailInfo, tagDetailInfos);
    }

    public GetBlogsResponseDto toGetBlogsResponseDto(List<BlogsElementInfo> blogsElementInfos,
                                                     PageInfo pageInfo) {
        return GetBlogsResponseDto.of(blogsElementInfos, pageInfo);
    }

    public BlogsElementInfo toBlogsElementInfo(BlogThumbnailVo blogThumbnailVo,
                                               List<HashTagDetailInfo> tagDetailInfos) {
        return BlogsElementInfo.of(blogThumbnailVo, tagDetailInfos);
    }
}
