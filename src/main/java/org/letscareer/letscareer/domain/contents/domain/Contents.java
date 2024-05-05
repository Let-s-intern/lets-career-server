package org.letscareer.letscareer.domain.contents.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.contents.domain.converter.ContentsTopicConverter;
import org.letscareer.letscareer.domain.contents.domain.converter.ContentsTypeConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contents extends BaseTimeEntity {

    @Id
    @Column(name = "contents_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Convert(converter = ContentsTypeConverter.class)
    private ContentsType type;

    @NotNull
    @Convert(converter = ContentsTopicConverter.class)
    private ContentsTopic topic;

    @NotNull
    private String title;

    @NotNull
    private String link;
}
