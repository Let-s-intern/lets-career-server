package org.letscareer.letscareer.domain.blog.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.vo.HashTagDetailInfo;

import java.util.List;

import static org.letscareer.letscareer.domain.blog.entity.QHashTag.hashTag;

@RequiredArgsConstructor
public class HashTagQueryRepositoryImpl implements HashTagQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<HashTagDetailInfo> findAllHashTagDetailInfo() {
        return queryFactory
                .select(Projections.constructor(HashTagDetailInfo.class,
                        hashTag.id,
                        hashTag.title,
                        hashTag.createDate,
                        hashTag.lastModifiedDate))
                .from(hashTag)
                .orderBy(
                        hashTag.id.desc()
                )
                .fetch();
    }
}
