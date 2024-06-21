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
    public Page<UserAdminVo> findAllUserAdminVos(String keyword, Pageable pageable) {
        List<UserAdminVo> userAdminVoList = queryFactory
                .select(Projections.constructor(UserAdminVo.class,
                        user.id,
                        user.name,
                        user.email,
                        user.phoneNum,
                        user.createDate,
                        user.accountType,
                        user.accountNum,
                        user.accountOwner,
                        user.marketingAgree))
                .from(user)
                .where(
                        containsKeyword(keyword)
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

    private BooleanExpression containsKeyword(String keyword) {
        return keyword != null ? user.email.containsIgnoreCase(keyword)
                .or(user.contactEmail.containsIgnoreCase(keyword))
                .or(user.name.containsIgnoreCase(keyword))
                .or(user.phoneNum.containsIgnoreCase(keyword)) : null;
    }
}
