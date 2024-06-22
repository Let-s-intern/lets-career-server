package org.letscareer.letscareer.domain.user.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.entity.QUser;
import org.letscareer.letscareer.domain.user.vo.UserAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static org.letscareer.letscareer.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class UserQueryRepositoryImpl implements UserQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<UserAdminVo> findAllUserAdminVos(String email, String name, String phoneNum, Pageable pageable) {
        List<UserAdminVo> userAdminVoList = queryFactory
                .select(Projections.constructor(UserAdminVo.class,
                        user.id,
                        user.name,
                        user.email,
                        user.contactEmail,
                        user.phoneNum,
                        user.createDate,
                        user.accountType,
                        user.accountNum,
                        user.accountOwner,
                        user.marketingAgree))
                .from(user)
                .where(
                        containsEmail(email),
                        containsName(name),
                        containsPhoneNum(phoneNum)
                )
                .orderBy(user.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(user.count()).from(user);

        return PageableExecutionUtils.getPage(userAdminVoList, pageable, countQuery::fetchOne);
    }

    public static Expression<String> activeEmail(QUser user) {
        return new CaseBuilder()
                .when(user.contactEmail.isNotNull()).then(user.contactEmail)
                .otherwise(user.email);
    }

    private BooleanExpression containsEmail(String email) {
        return email != null ? user.email.containsIgnoreCase(email).or(user.contactEmail.containsIgnoreCase(email)) : null;
    }

    private BooleanExpression containsName(String name) {
        return name != null ? user.name.containsIgnoreCase(name) : null;
    }

    private BooleanExpression containsPhoneNum(String phoneNum) {
        return phoneNum != null ? user.phoneNum.containsIgnoreCase(phoneNum) : null;
    }
}
