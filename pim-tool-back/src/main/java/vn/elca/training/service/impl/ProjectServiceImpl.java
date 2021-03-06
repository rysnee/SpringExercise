package vn.elca.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.ProjectNotFoundException;
import vn.elca.training.repository.ProjectRepository;
import vn.elca.training.service.ProjectService;

import java.util.List;

/**
 * @author vlp
 *
 */
@Service
@Profile("!dummy | dev")
@Qualifier("serviceProjectDummy")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> findByKeyword(String keyword) { return null; }

    @Override
    public Project findById(long id) throws ProjectNotFoundException{
        return projectRepository.findProjectById(id);
    }

    @Override
    public long count() {
        return projectRepository.count();
    }

    @Override
    public Project update(Project project) throws ProjectNotFoundException { return null;  }
}
