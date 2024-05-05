package org.letscareer.letscareer.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.global.security.oauth2.AuthProvider;

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
    private String email;

    @Nullable
    private String contactEmail;

    @Nullable
    @JsonIgnore
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String phoneNum;

    @Nullable
    private String university;

    @Nullable
    private String major;

    @Nullable
    private UserGrade userGrade;

    @Nullable
    private AccountType accountType;

    @Nullable
    private String accountNum;

    @Nullable
    private AuthProvider authProvider;

    @NotNull
    @Builder.Default
    private UserRole role = UserRole.USER;

}
