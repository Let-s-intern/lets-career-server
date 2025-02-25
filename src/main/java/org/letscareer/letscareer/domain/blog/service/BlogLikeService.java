package org.letscareer.letscareer.domain.blog.service;

import org.letscareer.letscareer.domain.blog.dto.response.like.GetLikedBlogResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface BlogLikeService {
    void addBlogLike(Long blogId, User user);
    GetLikedBlogResponseDto getUserLikedBlogIds(Long userId);
}
