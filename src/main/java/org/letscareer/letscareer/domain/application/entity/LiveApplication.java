package org.letscareer.letscareer.domain.application.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.user.entity.User;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("live_application")
@Getter
@Entity
public class LiveApplication extends Application {
    private String motivate;
    private String question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "live_id")
    private Live live;

    @Builder(access = AccessLevel.PRIVATE)
    public LiveApplication(CreateApplicationRequestDto createApplicationRequestDto,
                           Live live,
                           User user) {
        super(user);
        this.motivate = createApplicationRequestDto.motivate();
        this.question = createApplicationRequestDto.question();
        this.live = live;
    }

    public static LiveApplication createLiveApplication(CreateApplicationRequestDto createApplicationRequestDto,
                                                        Live live,
                                                        User user) {
        return LiveApplication.builder()
                .createApplicationRequestDto(createApplicationRequestDto)
                .live(live)
                .user(user)
                .build();
    }
}
