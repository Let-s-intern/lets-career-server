package org.letscareer.letscareer.domain.blog.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.entity.Blog;
import org.letscareer.letscareer.domain.blog.entity.BlogHashTag;
import org.letscareer.letscareer.domain.blog.entity.HashTag;
import org.letscareer.letscareer.domain.blog.repository.BlogHashTagRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BlogHashTagHelper {
    private final BlogHashTagRepository blogHashTagRepository;

    public BlogHashTag createBlogHashTagAndSave(Blog blog, HashTag hashTag) {
        BlogHashTag blogHashTag = BlogHashTag.createBlogHashTag(blog, hashTag);
        return blogHashTagRepository.save(blogHashTag);
    }

    public void deleteBlogHashTags(List<BlogHashTag> blogHashTags) {
        blogHashTags.forEach(this::deleteBlogHashTag);
    }

    public void deleteBlogHashTag(BlogHashTag blogHashTag) {
        blogHashTagRepository.delete(blogHashTag);
    }

    public Boolean existsBlogHashTagByHashTagId(Long hashTagId) {
        return blogHashTagRepository.existsByHashTagId(hashTagId);
    }
}
