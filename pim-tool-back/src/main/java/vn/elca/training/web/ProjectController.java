package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.ProjectNotFoundException;
import vn.elca.training.service.ProjectService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gtn
 */
@RestController
@RequestMapping("/projects")
public class ProjectController extends AbstractApplicationController {

    @Autowired
    @Qualifier("serviceProjectDummy")
    private ProjectService projectService;

    @GetMapping("/search")
    public List<ProjectDto> search() {
        return projectService.findAll()
                .stream()
                .map(mapper::projectToProjectDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/search/id/{id}")
    public ProjectDto searchByIdPath(@PathVariable long id) throws ProjectNotFoundException {
        return mapper.projectToProjectDto(projectService.findById(id));
    }

    @GetMapping(value = "/search", params = "id")
    public ProjectDto searchByIdParam(@RequestParam("id") long id) throws ProjectNotFoundException {
        return mapper.projectToProjectDto(projectService.findById(id));
    }

    @GetMapping("/search/keyword/{keyword}")
    public List<ProjectDto> searchByKeywordPath(@PathVariable String keyword) throws ProjectNotFoundException {
        return projectService.findByKeyword(keyword)
                .stream()
                .map(mapper::projectToProjectDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/search", params = "keyword")
    public List<ProjectDto> searchByKeywordParam(@RequestParam("keyword") String keyword) throws ProjectNotFoundException {
        return projectService.findByKeyword(keyword)
                .stream()
                .map(mapper::projectToProjectDto)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/update")
    public ProjectDto updateProject(@Valid @RequestBody Project project) throws ProjectNotFoundException {
        return mapper.projectToProjectDto(projectService.update(project));
    }
}
