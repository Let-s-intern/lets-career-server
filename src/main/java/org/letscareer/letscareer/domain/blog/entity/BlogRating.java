package org.letscareer.letscareer.domain.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "blog_rating")
@Entity
public class BlogRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_rating_id")
    private Long id;
    private String title;
    private String content;
    @Builder.Default
    private Integer score = 0;
    @OneToMany(mappedBy = "blog_hash_tag", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Blog> blogList = new ArrayList<>();
}
