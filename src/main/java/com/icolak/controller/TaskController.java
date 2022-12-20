package com.icolak.controller;

import com.icolak.dto.TaskDTO;
import com.icolak.enums.Status;
import com.icolak.service.ProjectService;
import com.icolak.service.TaskService;
import com.icolak.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final UserService userService;

    public TaskController(TaskService taskService, ProjectService projectService, UserService userService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createTask(Model model) {
        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks", taskService.findAll());

        return "/task/create";
    }

    @PostMapping("/create")
    public String insertTask(@ModelAttribute TaskDTO task) {

        taskService.save(task);

        return "redirect:/task/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long taskId) {

        taskService.deleteById(taskId);

        return "redirect:/task/create";
    }

    @GetMapping("/update/{taskId}")
    public String editTask(@PathVariable("taskId") Long taskId, Model model) {
        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks", taskService.findAll());

        return "/task/update";
    }

//    @PostMapping("/update/{taskId}")
//    public String updateTask(@PathVariable("taskId") Long taskId, @ModelAttribute TaskDTO task) {
//
//        task.setId(taskId);
//
//        taskService.update(task);
//
//        return "redirect:/task/create";
//    }

    // instead of above method we can use below method,
    // but the parameter{id} has to match class field (TaskDTO class, id field)

    @PostMapping("/update/{id}")
    public String updateTask(TaskDTO task) {

        taskService.update(task);

        return "redirect:/task/create";
    }

    @GetMapping("/employee/pending-tasks")
    public String employeePendingTasks(Model model) {

        model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETE));

        return "task/pending-tasks";
    }

    @GetMapping("/employee/archive")
    public String employeeArchivedTasks(Model model) {

        model.addAttribute("tasks", taskService.findAllTasksByStatus(Status.COMPLETE));

        return "task/archive";
    }

    @GetMapping("/employee/edit/{id}")
    public String employeeEditTask(@PathVariable Long id, Model model) {

        model.addAttribute("task", taskService.findById(id));
//        model.addAttribute("projects", projectService.findAll());
//        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETE));

        return "task/status-update";
    }

    @PostMapping("/employee/update/{id}")
    public String employeeUpdateTask(TaskDTO task) {

        taskService.updateStatus(task); // here we can not use update method directly,
        // because update method get the status from db. So, we should update status in db first,
        // to be able to do that, we create new method updateStatus
        return "redirect:/task/employee/pending-tasks";
    }

}
