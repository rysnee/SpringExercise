package vn.elca.training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.ProjectNotFoundException;

/**
 * @author vlp
 *
 */
public interface ProjectService {
    List<Project> findAll();

    List<Project> findByKeyword(String keyword) throws ProjectNotFoundException;

    Project findById(long id) throws ProjectNotFoundException;

    Project update(Project project) throws ProjectNotFoundException;

    long count();
}
