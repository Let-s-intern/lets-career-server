package org.letscareer.letscareer.domain.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.blog.type.BlogType;
import org.letscareer.letscareer.domain.blog.type.converter.BlogTypeConverter;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "blog")
@Entity
public class Blog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long id;
    private String title;
    @Convert(converter = BlogTypeConverter.class)
    private BlogType category;
    private String thumbnail;
    private String description;
    private String content;
    private String ctaLink;
    private String ctaText;
    private LocalDateTime displayDate;
    private LocalDateTime deleteDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_hash_tag_id")
    private BlogHashTag blogHashTag;
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    @Builder.Default
    private List<BlogRating> blogRatingList = new ArrayList<>();
}
