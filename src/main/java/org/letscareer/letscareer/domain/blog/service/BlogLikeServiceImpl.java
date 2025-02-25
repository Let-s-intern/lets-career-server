package org.letscareer.letscareer.domain.blog.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.response.like.GetLikedBlogResponseDto;
import org.letscareer.letscareer.domain.blog.entity.Blog;
import org.letscareer.letscareer.domain.blog.helper.BlogHelper;
import org.letscareer.letscareer.domain.blog.helper.LikeHelper;
import org.letscareer.letscareer.domain.blog.mapper.LikeMapper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BlogLikeServiceImpl implements BlogLikeService {
    private final BlogHelper blogHelper;
    private final LikeHelper likeHelper;
    private final LikeMapper likeMapper;

    @Override
    public void addBlogLike(Long blogId, User user){
        Blog blog = blogHelper.findBlogByIdByOrThrow(blogId);
        likeHelper.addBlogLike(blog, user);
    }

    @Override
    public GetLikedBlogResponseDto getUserLikedBlogIds(Long userId){
        List<Long> blogIds = likeHelper.getUserLikedBlogIds(userId);
        return likeMapper.toGetLikedBlogResponseDto(blogIds);
    }
}
