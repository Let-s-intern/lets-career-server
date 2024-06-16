package org.letscareer.letscareer.domain.contents.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.contents.entity.Contents;

@Builder
public record ContentsMissionVo(
        Long id,
        String title,
        String link
) {
    public static ContentsMissionVo of(Contents contents) {
        return ContentsMissionVo.builder()
                .id(contents.getId())
                .title(contents.getTitle())
                .link(contents.getLink())
                .build();
    }
}
