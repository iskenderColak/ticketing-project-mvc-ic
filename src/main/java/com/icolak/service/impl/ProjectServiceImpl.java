package com.icolak.service.impl;

import com.icolak.dto.ProjectDTO;
import com.icolak.dto.TaskDTO;
import com.icolak.dto.UserDTO;
import com.icolak.enums.Status;
import com.icolak.service.ProjectService;
import com.icolak.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO, String> implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

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

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {

        List<ProjectDTO> projectList =
                findAll().stream()
                        .filter(project -> project.getAssignedManager().equals(manager))
                        .map(project -> {

                            //we need all the task belongs to this project
                            List<TaskDTO> taskList = taskService.findTasksByManager(manager);

                            int completeTaskCounts = (int) taskList.stream()
                                    .filter(task -> task.getProject().equals(project) &&
                                            task.getTaskStatus() == Status.COMPLETE).count();
                            int unfinishedTaskCounts = (int) taskList.stream()
                                    .filter(task -> task.getProject().equals(project) &&
                                            task.getTaskStatus() != Status.COMPLETE).count();

                            project.setCompleteTaskCounts(completeTaskCounts);
                            project.setUnfinishedTaskCounts(unfinishedTaskCounts);

                            return project;

                        })
                        .collect(Collectors.toList());



        return projectList;
    }
}
