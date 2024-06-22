package org.letscareer.letscareer.domain.missioncontents.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.entity.Contents;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.missioncontents.entity.MissionContents;
import org.letscareer.letscareer.domain.missioncontents.repository.MissionContentsRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MissionContentsHelper {
    private final MissionContentsRepository missionContentsRepository;

    public MissionContents createMissionContentsAndSave(Mission mission, Contents contents) {
        MissionContents newMissionContents = MissionContents.createMissionContents(mission, contents);
        return missionContentsRepository.save(newMissionContents);
    }

    public void deleteAllMissionContentsByMissionIdAndContentsType(Long missionId, ContentsType contentsType) {
        missionContentsRepository.deleteAllMissionContentsByMissionIdAndContentsType(missionId, contentsType);
    }
}
