package org.letscareer.letscareer.domain.application.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;

import java.util.List;

@RequiredArgsConstructor
public class ApplicationQueryRepositoryImpl implements ApplicationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MyApplicationVo> findMyApplications(Long userId) {
        return null;
    }
}
