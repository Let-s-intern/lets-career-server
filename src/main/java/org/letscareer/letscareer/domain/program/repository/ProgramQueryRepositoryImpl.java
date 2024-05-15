package org.letscareer.letscareer.domain.program.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.program.entity.QVWProgram;
import org.letscareer.letscareer.domain.program.entity.VWProgram;
import org.letscareer.letscareer.domain.program.vo.AdminProgramVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static org.letscareer.letscareer.domain.program.entity.QVWProgram.vWProgram;

@RequiredArgsConstructor
public class ProgramQueryRepositoryImpl implements ProgramQueryRepository {
    private final JPAQueryFactory queryFactory;


    @Override
    public Page<AdminProgramVo> findAdminProgramVos(Pageable pageable) {
        List<AdminProgramVo> contents = queryFactory
                .select(Projections.constructor(AdminProgramVo.class,
                        vWProgram.programId,
                        vWProgram.programType,
                        vWProgram.title,
                        vWProgram.currentCount,
                        vWProgram.participationCount,
                        vWProgram.startDate,
                        vWProgram.endDate,
                        vWProgram.deadline,
                        vWProgram.isVisible,
                        vWProgram.zoomLink,
                        vWProgram.zoomPassword
                ))
                .from(vWProgram)
                .orderBy(vWProgram.createDate.desc())
                .fetch();

        JPAQuery<VWProgram> countQuery = queryFactory
                .selectFrom(vWProgram);

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }
}
