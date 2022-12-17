package com.icolak.service.impl;

import com.icolak.dto.ProjectDTO;
import com.icolak.enums.Status;
import com.icolak.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO, String> implements ProjectService {
    @Override
    public ProjectDTO save(ProjectDTO project) {
        if(project.getProjectStatus() == null) {
            project.setProjectStatus(Status.OPEN);
        }
        return super.save(project.getProjectCode(), project);
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO object) {

        // When we update project, since there is no field for the project status in the form,
        // we take project status of that project from the db and assign it to the updated project.
        if(object.getProjectStatus() == null) {
            object.setProjectStatus(findById(object.getProjectCode()).getProjectStatus());
        }

        super.update(object.getProjectCode(), object);
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public void complete(ProjectDTO project) {
        project.setProjectStatus(Status.COMPLETE);
    }
}
