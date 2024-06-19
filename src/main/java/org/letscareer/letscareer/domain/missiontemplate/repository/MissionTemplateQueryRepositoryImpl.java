package org.letscareer.letscareer.domain.missiontemplate.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.missiontemplate.vo.MissionTemplateAdminSimpleVo;
import org.letscareer.letscareer.domain.missiontemplate.vo.MissionTemplateAdminVo;

import java.util.List;

import static org.letscareer.letscareer.domain.missiontemplate.entity.QMissionTemplate.missionTemplate;

@RequiredArgsConstructor
public class MissionTemplateQueryRepositoryImpl implements MissionTemplateQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MissionTemplateAdminVo> findAllMissionTemplateAdminVos() {
        return queryFactory
                .select(Projections.constructor(MissionTemplateAdminVo.class,
                        missionTemplate.id,
                        missionTemplate.createDate,
                        missionTemplate.missionTag,
                        missionTemplate.title,
                        missionTemplate.description,
                        missionTemplate.guide,
                        missionTemplate.templateLink))
                .from(missionTemplate)
                .orderBy(missionTemplate.id.desc())
                .fetch();
    }

    @Override
    public List<MissionTemplateAdminSimpleVo> findAllMissionTemplateAdminSimpleVos() {
        return queryFactory
                .select(Projections.constructor(MissionTemplateAdminSimpleVo.class,
                        missionTemplate.id,
                        missionTemplate.title))
                .from(missionTemplate)
                .orderBy(missionTemplate.id.desc())
                .fetch();
    }
}
