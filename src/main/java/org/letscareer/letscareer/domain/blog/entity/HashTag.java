package org.letscareer.letscareer.domain.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.blog.dto.request.CreateHashTagRequestDto;
import org.letscareer.letscareer.domain.blog.dto.request.UpdateHashTagRequestDto;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "hash_tag")
@Entity
public class HashTag extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hash_tag_id")
    private Long id;
    @Column(nullable = false)
    private String title;
    private LocalDateTime deleteDate;
    @OneToMany(mappedBy = "hashTag", cascade = CascadeType.ALL)
    @Builder.Default
    private List<BlogHashTag> blogHashTags = new ArrayList<>();

    public static HashTag createHashTag(CreateHashTagRequestDto requestDto) {
        return HashTag.builder()
                .title(requestDto.title())
                .build();
    }

    public void updateHashTag(UpdateHashTagRequestDto requestDto) {
        this.title = updateValue(this.title, requestDto.title());
    }

    public void addBlogHashTag(BlogHashTag blogHashTag) {
        this.blogHashTags.add(blogHashTag);
    }
}
