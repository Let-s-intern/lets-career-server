package org.letscareer.letscareer.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.user.domain.converter.AccountTypeConverter;
import org.letscareer.letscareer.domain.user.domain.converter.AuthProviderConverter;
import org.letscareer.letscareer.domain.user.domain.converter.UserGradeConverter;
import org.letscareer.letscareer.domain.user.domain.converter.UserRoleConverter;
import org.letscareer.letscareer.global.security.oauth2.userinfo.OAuth2UserInfo;

import static org.letscareer.letscareer.global.common.utils.EntityUpdateValueUtils.updateValue;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 30)
    private String email;

    @Nullable
    @Column(length = 30)
    private String contactEmail;

    @Nullable
    @JsonIgnore
    private String password;

    @NotNull
    @Column(length = 10)
    private String name;

    @NotNull
    @Column(length = 20)
    private String phoneNum;

    @Nullable
    @Column(length = 30)
    private String university;

    @Nullable
    @Column(length = 30)
    private String major;

    @Nullable
    @Convert(converter = UserGradeConverter.class)
    private UserGrade userGrade;

    @Nullable
    @Convert(converter = AccountTypeConverter.class)
    private AccountType accountType;

    @Nullable
    @Column(length = 30)
    private String accountNum;

    @Nullable
    @Convert(converter = AuthProviderConverter.class)
    private AuthProvider authProvider;

    @NotNull
    @Builder.Default
    @Convert(converter = UserRoleConverter.class)
    private UserRole role = UserRole.USER;

    public static User createUserFromOAuth2(OAuth2UserInfo oAuth2UserInfo, AuthProvider authProvider) {
        return User.builder()
                .email(oAuth2UserInfo.getEmail())
                .name(oAuth2UserInfo.getName())
                .phoneNum(oAuth2UserInfo.getPhoneNum())
                .authProvider(authProvider)
                .build();
    }

    public User updateFromOAuth2(OAuth2UserInfo oAuth2UserInfo) {
        this.email = updateValue(this.email, oAuth2UserInfo.getEmail());
        this.name = updateValue(this.name, oAuth2UserInfo.getName());
        this.phoneNum = updateValue(this.phoneNum, oAuth2UserInfo.getPhoneNum());
        return this;
    }
}
