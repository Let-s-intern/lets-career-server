package org.letscareer.letscareer.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("live_review")
@Getter
@Entity
public class LiveReview extends Review {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "live_id")
    private Live live;

    @Builder(access = AccessLevel.PRIVATE)
    public LiveReview(Live live, LiveApplication liveApplication, CreateReviewRequestDto requestDto) {
        super(liveApplication, requestDto);
        this.live = live;
    }

    public static LiveReview createLiveReview(Live live, LiveApplication liveApplication, CreateReviewRequestDto requestDto) {
        LiveReview liveReview = LiveReview.builder()
                .live(live)
                .liveApplication(liveApplication)
                .requestDto(requestDto)
                .build();
        liveApplication.setReview(liveReview);
        return liveReview;
    }
}
