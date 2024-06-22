package org.letscareer.letscareer.domain.banner.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.vo.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.banner.entity.QPopup.popup;
import static org.letscareer.letscareer.domain.file.entity.QFile.file;

@RequiredArgsConstructor
public class PopupQueryRepositoryImpl implements PopupQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<PopupBannerAdminVo> findAllPopupAdminVos() {
        return queryFactory
                .select(Projections.constructor(PopupBannerAdminVo.class,
                        popup.id,
                        popup.title,
                        popup.link,
                        popup.startDate,
                        popup.endDate,
                        popup.isValid,
                        popup.isVisible,
                        file.url
                ))
                .from(popup)
                .leftJoin(popup.file, file)
                .orderBy(popup.id.desc())
                .fetch();
    }

    @Override
    public Optional<PopupBannerAdminDetailVo> findBannerAdminDetailVo(Long bannerId) {
        return Optional.ofNullable(
                queryFactory
                        .select(Projections.constructor(PopupBannerAdminDetailVo.class,
                                popup.id,
                                popup.title,
                                popup.link,
                                popup.startDate,
                                popup.endDate,
                                popup.isValid,
                                popup.isVisible,
                                file.url
                        ))
                        .from(popup)
                        .leftJoin(popup.file, file)
                        .where(
                                eqBannerId(bannerId)
                        )
                        .fetchFirst()
        );
    }

    @Override
    public List<PopupUserVo> findAllPopBannerUserVos() {
        return queryFactory
                .select(Projections.constructor(PopupUserVo.class,
                        popup.id,
                        popup.title,
                        popup.link,
                        popup.startDate,
                        popup.endDate,
                        popup.isValid,
                        file.url
                ))
                .from(popup)
                .leftJoin(popup.file, file)
                .where(
                        isVisible(),
                        isWithinDateRange()
                )
                .orderBy(popup.id.desc())
                .fetch();
    }

    private BooleanExpression eqBannerId(Long bannerId) {
        return bannerId != null ? popup.id.eq(bannerId) : null;
    }

    private BooleanExpression isVisible() {
        return popup.isVisible.eq(true);
    }

    private BooleanExpression isWithinDateRange() {
        LocalDateTime now = LocalDateTime.now();
        return popup.startDate.loe(now).and(popup.endDate.goe(now));
    }
}
