package org.letscareer.letscareer.domain.review.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.type.converter.ProgramTypeConverter;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class BlogReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_review_id")
    private Long blogReviewId;

    @Convert(converter = ProgramTypeConverter.class)
    private ProgramType programType;

    private Long programId;

    private String programTitle;

    private String name;

    private String title;

    private String url;

    private LocalDateTime postDate;

    @Builder.Default
    @NotNull
    private Boolean isVisible = false;

}
