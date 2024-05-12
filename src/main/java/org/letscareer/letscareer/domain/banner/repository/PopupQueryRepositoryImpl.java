package org.letscareer.letscareer.domain.banner.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;

import java.util.List;

import static org.letscareer.letscareer.domain.banner.entity.QPopup.popup;

@RequiredArgsConstructor
public class PopupQueryRepositoryImpl implements PopupQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<BannerAdminVo> findAllPopupAdminVos() {
        return queryFactory
                .select(Projections.constructor(BannerAdminVo.class,
                        popup.id,
                        popup.title,
                        popup.link,
                        popup.startDate,
                        popup.endDate,
                        popup.isValid,
                        popup.isVisible,
                        popup.imgUrl))
                .from(popup)
                .orderBy(popup.id.desc())
                .fetch();
    }
}
