package org.letscareer.letscareer.domain.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "blog")
@Entity
public class HashTag extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hash_tag_id")
    private Long id;
    @Column(nullable = false)
    private String title;
    private LocalDateTime deleteDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_hash_tag_id")
    private BlogHashTag blogHashTag;
}
