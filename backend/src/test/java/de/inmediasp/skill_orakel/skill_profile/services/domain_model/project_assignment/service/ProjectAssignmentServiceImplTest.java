package de.inmediasp.skill_orakel.skill_profile.services.domain_model.project_assignment.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.ProjectAssignmentService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.ProjectAssignmentRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project_assignment.ProjectAssignment;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.model.ProjectAssignmentBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.service.ProjectAssignmentMapper;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.service.ProjectAssignmentServiceImpl;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProjectAssignmentServiceImplTest {
    private final ProjectAssignmentRepository repository = mock(ProjectAssignmentRepository.class);
    private final EntitiesToBusinessObjectsMapper<ProjectAssignmentBusinessObject, ProjectAssignment> mapper
            = mock(ProjectAssignmentMapper.class);
    private final ProjectAssignmentService service = new ProjectAssignmentServiceImpl(repository, mapper);

    @Test
    void GIVEN_dbHasNoEntryWithSkillProfileId_THEN_findBySkillProfileId_RETURNS_emptyList() {

        UUID uuid = UUID.randomUUID();

        List<ProjectAssignmentBusinessObject> projectAssignmentBusinessObjects = new ArrayList<>();
        List<ProjectAssignment> projectAssignments = new ArrayList<>();

        when(repository.findAllByProjectAssignmentId_SkillProfileId(uuid)).thenReturn(projectAssignments);
        when(mapper.convertToBusinessObjects(projectAssignments)).thenReturn(projectAssignmentBusinessObjects);

        List<ProjectAssignmentBusinessObject> result = service.findAllBySkillProfileId(uuid);

        assertTrue(result.isEmpty());
    }


    @Test
    void GIVEN_dbHasEntriesWithSkillProfileId_THEN_findBySkillProfileId_RETURNS_ListOfSkills() {
        UUID uuid = UUID.randomUUID();

        List<ProjectAssignmentBusinessObject> projectAssignmentBusinessObjects =
                List.of(new ProjectAssignmentBusinessObject(), new ProjectAssignmentBusinessObject());
        List<ProjectAssignment> projectAssignments = List.of(new ProjectAssignment(), new ProjectAssignment());

        when(repository.findAllByProjectAssignmentId_SkillProfileId(uuid)).thenReturn(projectAssignments);
        when(mapper.convertToBusinessObjects(projectAssignments)).thenReturn(projectAssignmentBusinessObjects);

        List<ProjectAssignmentBusinessObject> result = service.findAllBySkillProfileId(uuid);

        assertEquals(projectAssignmentBusinessObjects, result);
    }
}
