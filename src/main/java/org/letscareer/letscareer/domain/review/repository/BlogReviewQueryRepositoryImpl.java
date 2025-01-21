package org.letscareer.letscareer.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.vo.BlogReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.BlogReviewVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static org.letscareer.letscareer.domain.review.entity.QBlogReview.blogReview;

@RequiredArgsConstructor
public class BlogReviewQueryRepositoryImpl implements BlogReviewQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<BlogReviewAdminVo> findAllBlogReviewAdminVos() {
        return queryFactory
                .select(Projections.constructor(BlogReviewAdminVo.class,
                        blogReview.id,
                        blogReview.postDate,
                        blogReview.programType,
                        blogReview.programTitle,
                        blogReview.name,
                        blogReview.title,
                        blogReview.url,
                        blogReview.thumbnail,
                        blogReview.isVisible))
                .from(blogReview)
                .orderBy(blogReview.id.desc())
                .fetch();
    }

    @Override
    public Page<BlogReviewVo> findAllBlogReviewVos(List<ProgramType> typeList, Pageable pageable) {
        List<BlogReviewVo> blogReviewVoList = queryFactory
                .select(Projections.constructor(BlogReviewVo.class,
                        blogReview.id,
                        blogReview.postDate,
                        blogReview.programType,
                        blogReview.programTitle,
                        blogReview.name,
                        blogReview.title,
                        blogReview.url,
                        blogReview.thumbnail))
                .from(blogReview)
                .where(
                        eqIsVisible(true),
                        inProgramType(typeList)
                )
                .orderBy(blogReview.postDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(blogReview.count())
                .from(blogReview);

        return PageableExecutionUtils.getPage(blogReviewVoList, pageable, countQuery::fetchOne);
    }

    private BooleanExpression eqIsVisible(Boolean isVisible) {
        return isVisible != null ? blogReview.isVisible.eq(isVisible) : null;
    }

    private BooleanExpression inProgramType(List<ProgramType> typeList) {
        if(typeList == null || typeList.isEmpty()) return null;
        BooleanExpression challengeCondition = blogReview.programType.eq(ProgramType.CHALLENGE).and(blogReview.programType.in(typeList));
        BooleanExpression liveCondition = blogReview.programType.eq(ProgramType.LIVE).and(blogReview.programType.in(typeList));
        BooleanExpression vodCondition = blogReview.programType.eq(ProgramType.VOD).and(blogReview.programType.in(typeList));
        BooleanExpression reportCondition = blogReview.programType.eq(ProgramType.REPORT).and(blogReview.programType.in(typeList));
        return challengeCondition.or(liveCondition).or(vodCondition).or(reportCondition);
    }
}
