package de.inmediasp.skill_orakel.skill_profile.services.domain_model.project_assignment.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project.Project;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project_assignment.ProjectAssignment;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project_assignment_skill.ProjectAssignmentSkill;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.model.ProjectAssignmentBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.service.ProjectAssignmentMapper;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.entities.Skill;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model.SkillBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectAssignmentMapperTest {

    private final EntitiesToBusinessObjectsMapper<ProjectAssignmentBusinessObject, ProjectAssignment> mapper
            = new ProjectAssignmentMapper();

    @Test
    void GIVEN_listWithProjectAssignment_THEN_convertToBusinessObjects_Returns_correctlyMappedList() {

        String name = "Skills orakeln";
        String description = "Drei Idioten und ein Backend";
        String responsibilities = "Code schreiben und ihn dann wieder löschen";
        String customer = "custom Customer";
        LocalDate startDate = LocalDate.of(1914, 7, 28);
        LocalDate endDate = LocalDate.of(1918, 11, 11);
        UUID id = UUID.randomUUID();

        SkillBusinessObject skillBusinessObject = new SkillBusinessObject();
        List<SkillBusinessObject> skillBusinessObjects = List.of(skillBusinessObject);

        ProjectAssignmentBusinessObject projectAssignmentBusinessObject = new ProjectAssignmentBusinessObject();
        projectAssignmentBusinessObject.setDescription(description);
        projectAssignmentBusinessObject.setProjectId(id);
        projectAssignmentBusinessObject.setName(name);
        projectAssignmentBusinessObject.setResponsibilities(responsibilities);
        projectAssignmentBusinessObject.setStartDate(startDate);
        projectAssignmentBusinessObject.setEndDate(endDate);
        projectAssignmentBusinessObject.setCustomer(customer);
        projectAssignmentBusinessObject.setSkills(skillBusinessObjects);

        List<ProjectAssignmentBusinessObject> projectAssignmentBusinessObjects = List.of(projectAssignmentBusinessObject);

        Project project = new Project();
        project.setProjectId(id);
        project.setName(name);
        project.setDescription(description);
        project.setCustomer(customer);

        Skill skill = new Skill();

        ProjectAssignmentSkill projectAssignmentSkill = new ProjectAssignmentSkill();
        projectAssignmentSkill.setSkill(skill);
        List<ProjectAssignmentSkill> projectAssignmentSkills = List.of(projectAssignmentSkill);

        ProjectAssignment projectAssignment = new ProjectAssignment();
        projectAssignment.setStartDate(startDate);
        projectAssignment.setEndDate(endDate);
        projectAssignment.setResponsibilities(responsibilities);
        projectAssignment.setProject(project);
        projectAssignment.setProjectAssignmentSkills(projectAssignmentSkills);

        List<ProjectAssignment> projectAssignments = List.of(projectAssignment);

        List<ProjectAssignmentBusinessObject> result = mapper.convertToBusinessObjects(projectAssignments);

        assertEquals(projectAssignmentBusinessObjects, result);
    }

    @Test
    void GIVEN_emptyList_THEN_convertToBusinessObject_RETURNS_emptyList() {

        List<ProjectAssignment> projectAssignments = new ArrayList<>();

        List<ProjectAssignmentBusinessObject> result = mapper.convertToBusinessObjects(projectAssignments);

        assertTrue(result.isEmpty());
    }

    @Test
    void GIVEN_objectIsNull_Then_convertToBusinessObjects_RETURNS_null() {

        List<ProjectAssignment> projectAssignments = new ArrayList<>();
        List<ProjectAssignmentBusinessObject> projectAssignmentBusinessObjects = new ArrayList<>();

        projectAssignments.add(null);
        projectAssignmentBusinessObjects.add(null);

        List<ProjectAssignmentBusinessObject> result = mapper.convertToBusinessObjects(projectAssignments);

        assertEquals(projectAssignmentBusinessObjects, result);
    }

}