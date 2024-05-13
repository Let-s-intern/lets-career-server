package org.letscareer.letscareer.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.user.dto.request.UserAddInfoRequestDto;
import org.letscareer.letscareer.domain.user.dto.request.UserPwSignUpRequestDto;
import org.letscareer.letscareer.domain.user.type.AccountType;
import org.letscareer.letscareer.domain.user.type.AuthProvider;
import org.letscareer.letscareer.domain.user.type.UserGrade;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.letscareer.letscareer.domain.user.type.converter.AccountTypeConverter;
import org.letscareer.letscareer.domain.user.type.converter.AuthProviderConverter;
import org.letscareer.letscareer.domain.user.type.converter.UserGradeConverter;
import org.letscareer.letscareer.domain.user.type.converter.UserRoleConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;
import org.letscareer.letscareer.global.security.oauth2.userinfo.OAuth2UserInfo;

import java.util.ArrayList;
import java.util.List;

import static org.letscareer.letscareer.global.common.utils.EntityUpdateValueUtils.updateValue;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class User extends BaseTimeEntity {

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
    private UserGrade grade;

    @Nullable
    @Column(length = 30)
    private String wishJob;

    @Nullable
    @Column(length = 30)
    private String wishCompany;

    @Nullable
    @Column(length = 30)
    private String inflowPath;

    @NotNull
    @Builder.Default
    private Boolean marketingAgree = false;

    @Nullable
    @Convert(converter = AuthProviderConverter.class)
    private AuthProvider authProvider;

    @NotNull
    @Builder.Default
    @Convert(converter = UserRoleConverter.class)
    private UserRole role = UserRole.USER;

    @Nullable
    @Convert(converter = AccountTypeConverter.class)
    private AccountType accountType;

    @Nullable
    @Column(length = 30)
    private String accountNum;

    @Nullable
    @Column(length = 30)
    private String accountOwner;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Attendance> attendanceList = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Application> applicationList = new ArrayList<>();

    public static User createUserFromOAuth2(OAuth2UserInfo oAuth2UserInfo, AuthProvider authProvider) {
        return User.builder()
                .email(oAuth2UserInfo.getEmail())
                .name(oAuth2UserInfo.getName())
                .phoneNum(oAuth2UserInfo.getPhoneNum())
                .authProvider(authProvider)
                .build();
    }

    public static User createUser(UserPwSignUpRequestDto pwSignUpRequestDto, String encodedPassword) {
        return User.builder()
                .email(pwSignUpRequestDto.email())
                .name(pwSignUpRequestDto.name())
                .phoneNum(pwSignUpRequestDto.phoneNum())
                .password(encodedPassword)
                .build();
    }

    public User updateFromOAuth2(OAuth2UserInfo oAuth2UserInfo) {
        this.email = updateValue(this.email, oAuth2UserInfo.getEmail());
        this.name = updateValue(this.name, oAuth2UserInfo.getName());
        this.phoneNum = updateValue(this.phoneNum, oAuth2UserInfo.getPhoneNum());
        return this;
    }

    public void addUserInfo(UserAddInfoRequestDto addInfoRequestDto) {
        this.university = updateValue(this.university, addInfoRequestDto.university());
        this.major = updateValue(this.major, addInfoRequestDto.major());
        this.grade = updateValue(this.grade, addInfoRequestDto.grade());
        this.wishJob = updateValue(this.wishJob, addInfoRequestDto.wishJob());
        this.wishCompany = updateValue(this.wishCompany, addInfoRequestDto.wishCompany());
        this.marketingAgree = updateValue(this.marketingAgree, addInfoRequestDto.marketingAgree());
        this.accountType = updateValue(this.accountType, addInfoRequestDto.accountType());
        this.accountNum = updateValue(this.accountNum, addInfoRequestDto.accountNum());
        this.accountOwner = updateValue(this.accountOwner, addInfoRequestDto.accountOwner());
    }
}
