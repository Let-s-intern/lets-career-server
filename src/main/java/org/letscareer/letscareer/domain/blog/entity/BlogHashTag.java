package org.letscareer.letscareer.domain.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "blog_hash_tag")
@Entity
public class BlogHashTag extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_hash_tag_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    private Blog blog;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hash_tag_id")
    private HashTag hashTag;

    public static BlogHashTag createBlogHashTag(Blog blog, HashTag hashTag) {
        BlogHashTag blogHashTag = BlogHashTag.builder()
                .blog(blog)
                .hashTag(hashTag)
                .build();
        blog.addBlogHashTag(blogHashTag);
        hashTag.addBlogHashTag(blogHashTag);
        return blogHashTag;
    }
}
