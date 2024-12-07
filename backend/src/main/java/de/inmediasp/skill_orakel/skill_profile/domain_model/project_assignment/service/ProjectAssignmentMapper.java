package de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project.Project;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project_assignment.ProjectAssignment;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project_assignment.ProjectAssignmentId;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project_assignment_skill.ProjectAssignmentSkill;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project_assignment_skill.ProjectAssignmentSkillId;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.model.ProjectAssignmentBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.entities.Skill;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model.SkillBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

@Service
public class ProjectAssignmentMapper
        implements EntitiesToBusinessObjectsMapper<ProjectAssignmentBusinessObject, ProjectAssignment> {

    @Override
    public List<ProjectAssignmentBusinessObject> convertToBusinessObjects(List<ProjectAssignment> entities) {

        return entities.stream()
                .map(this::convertToBusinessObject)
                .toList();

    }

    private ProjectAssignmentBusinessObject convertToBusinessObject(ProjectAssignment projectAssignment) {
        if (projectAssignment == null)
            return null;

        return new ProjectAssignmentBusinessObject(
                projectAssignment.getProject().getProjectId(),
                projectAssignment.getProject().getName(),
                projectAssignment.getProject().getDescription(),
                projectAssignment.getResponsibilities(),
                projectAssignment.getProject().getCustomer(),
                projectAssignment.getStartDate(),
                projectAssignment.getEndDate(),
                projectAssignment.getProjectAssignmentSkills().stream().map(skill -> new SkillBusinessObject(
                        skill.getSkill().getSkillId(),
                        skill.getSkill().getName())).toList());
    }

    @Override
    public List<ProjectAssignment> convertToEntities(List<ProjectAssignmentBusinessObject> businessObjects,
            UUID skillProfileId) {
        return businessObjects.stream().map(assignment -> convertToEntity(assignment, skillProfileId)).toList();
    }

    private ProjectAssignment convertToEntity(ProjectAssignmentBusinessObject assignment, UUID skillProfileId) {
        ProjectAssignmentId projectAssignmentId = new ProjectAssignmentId(assignment.getProjectId(), skillProfileId);
        Project projectEntity = new Project(assignment.getProjectId(), assignment.getName(),
                assignment.getDescription(), assignment.getCustomer());
        List<ProjectAssignmentSkill> skillList = new LinkedList<>();
        ProjectAssignmentSkill temp = new ProjectAssignmentSkill();

        for (SkillBusinessObject skillBusinessObject : assignment.getSkills()) {
            temp.setProjectAssignmentSkillId(new ProjectAssignmentSkillId(assignment.getProjectId(), skillProfileId,
                    skillBusinessObject.getId()));
            temp.setSkill(new Skill(skillBusinessObject.getId(), skillBusinessObject.getName()));
            skillList.add(temp);
        }

        ProjectAssignment assignmentEntity = new ProjectAssignment(projectAssignmentId,
                assignment.getStartDate(),
                assignment.getEndDate(),
                assignment.getResponsibilities(),
                projectEntity,
                skillList);

        return assignmentEntity;
    }
}
