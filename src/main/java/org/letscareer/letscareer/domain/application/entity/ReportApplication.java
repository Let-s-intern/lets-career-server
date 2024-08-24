package org.letscareer.letscareer.domain.application.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;
import org.letscareer.letscareer.domain.application.type.converter.ReportApplicationStatusConverter;
import org.letscareer.letscareer.domain.user.entity.User;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("report_application")
@Getter
@Entity
public class ReportApplication extends Application {
    private Integer price;
    private Integer discountPrice;
    private String wishJob;
    private String message;
    private String applyUrl;
    private String recruitmentUrl;
    private String reportUrl;
    private LocalDateTime reportDate;

    @Convert(converter = ReportApplicationStatusConverter.class)
    private ReportApplicationStatus status;

    @Builder(access = AccessLevel.PRIVATE)
    public ReportApplication(User user) {
        super(user);
    }
}
