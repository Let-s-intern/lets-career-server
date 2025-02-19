package org.letscareer.letscareer.domain.blogbanner.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BlogBannerQueryRepositoryImpl implements BlogBannerQueryRepository {
    private final JPAQueryFactory queryFactory;
}
