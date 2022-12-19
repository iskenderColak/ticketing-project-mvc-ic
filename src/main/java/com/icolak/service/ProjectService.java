package com.icolak.service;

import com.icolak.dto.ProjectDTO;
import com.icolak.dto.UserDTO;

import java.util.List;

public interface ProjectService extends CrudService<ProjectDTO, String> {
    void complete(ProjectDTO project);

    List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager);
}
