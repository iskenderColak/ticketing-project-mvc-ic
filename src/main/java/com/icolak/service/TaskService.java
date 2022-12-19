package com.icolak.service;

import com.icolak.dto.TaskDTO;
import com.icolak.dto.UserDTO;

import java.util.List;

public interface TaskService extends CrudService<TaskDTO, Long> {
    List<TaskDTO> findTasksByManager(UserDTO manager);
}
