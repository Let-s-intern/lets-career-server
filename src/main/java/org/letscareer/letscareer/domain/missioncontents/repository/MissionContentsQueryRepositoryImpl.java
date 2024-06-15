package org.letscareer.letscareer.domain.missioncontents.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.type.ContentsType;

import static org.letscareer.letscareer.domain.missioncontents.entity.QMissionContents.missionContents;

@RequiredArgsConstructor
public class MissionContentsQueryRepositoryImpl implements MissionContentsQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteAllMissionContentsByMissionIdAndContentsType(Long missionId, ContentsType contentsType) {
        queryFactory
                .delete(missionContents)
                .where(
                        eqMissionId(missionId),
                        eqContentsType(contentsType)
                )
                .execute();
    }

    private BooleanExpression eqMissionId(Long missionId) {
        return missionId != null ? missionContents.mission.id.eq(missionId) : null;
    }

    private BooleanExpression eqContentsType(ContentsType contentsType) {
        return contentsType != null ? missionContents.contents.type.eq(contentsType) : null;
    }
}
