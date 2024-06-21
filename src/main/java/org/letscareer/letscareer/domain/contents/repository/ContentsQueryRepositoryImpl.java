package org.letscareer.letscareer.domain.contents.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminSimpleVo;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminVo;
import org.letscareer.letscareer.domain.contents.vo.ContentsDetailVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.contents.entity.QContents.contents;

@RequiredArgsConstructor
public class ContentsQueryRepositoryImpl implements ContentsQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ContentsAdminVo> findAllContentsAdminVos(Pageable pageable) {
        List<ContentsAdminVo> contentsAdminVoList = queryFactory
                .select(Projections.constructor(ContentsAdminVo.class,
                        contents.id,
                        contents.type,
                        contents.title,
                        contents.link,
                        contents.createDate))
                .from(contents)
                .orderBy(contents.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(contents.count()).from(contents);

        return PageableExecutionUtils.getPage(contentsAdminVoList, pageable, countQuery::fetchOne);
    }

    @Override
    public List<ContentsAdminSimpleVo> findAllContentsAdminSimpleVos(ContentsType contentsType) {
        return queryFactory
                .select(Projections.constructor(ContentsAdminSimpleVo.class,
                        contents.id,
                        contents.title))
                .from(contents)
                .where(
                        eqContentsType(contentsType)
                )
                .orderBy(contents.id.desc())
                .fetch();
    }

    @Override
    public Optional<ContentsDetailVo> findContentsDetailVo(Long contentsId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ContentsDetailVo.class,
                        contents.title,
                        contents.link,
                        contents.type
                ))
                .from(contents)
                .where(
                        eqContentsId(contentsId)
                )
                .fetchOne()
        );
    }

    private BooleanExpression eqContentsId(Long contentsId) {
        return contentsId != null ? contents.id.eq(contentsId) : null;
    }

    private BooleanExpression eqContentsType(ContentsType contentsType) {
        return contentsType != null ? contents.type.eq(contentsType) : null;
    }
}
