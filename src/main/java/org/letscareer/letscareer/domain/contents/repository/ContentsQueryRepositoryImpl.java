package org.letscareer.letscareer.domain.contents.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.vo.ContentsAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

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
}
