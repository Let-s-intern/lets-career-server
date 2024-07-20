package org.letscareer.letscareer.domain.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "blogHashTag", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Blog> blogList = new ArrayList<>();
    @OneToMany(mappedBy = "blogHashTag", cascade = CascadeType.ALL)
    @Builder.Default
    private List<HashTag> hashTagList = new ArrayList<>();
}
