package org.letscareer.letscareer.domain.blog.dto.response.blog;

import java.util.List;

public record GetBlogsResponseDto(
        List<BlogsElementInfo> blogInfos
) {
}
