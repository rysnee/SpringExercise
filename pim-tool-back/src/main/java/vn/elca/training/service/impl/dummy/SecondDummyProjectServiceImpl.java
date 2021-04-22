package vn.elca.training.service.impl.dummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.ProjectNotFoundException;
import vn.elca.training.repository.ProjectRepository;
import vn.elca.training.service.ProjectService;
import vn.elca.training.util.ApplicationMapper;

import java.util.List;

/**
 * @author gtn
 *
 */
@Service
@Profile("dummy")
@Qualifier("serviceProjectDummy")
public class SecondDummyProjectServiceImpl extends AbstractDummyProjectService implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> findByKeyword(String keyword) throws ProjectNotFoundException{
        List<Project> result = projectRepository.findProjectsByNameContains(keyword);
        if(result.isEmpty())
            throw new ProjectNotFoundException(
                    String.format("Project(s) with keyword: %s not found.",keyword),keyword);
        return result;
    }

    @Override
    public long count() {
        return projectRepository.count();
    }

    @Override
    public Project findById(long id) throws ProjectNotFoundException{
        Project result = projectRepository.findProjectById(id);
        if(result == null)
            throw new ProjectNotFoundException(
                    String.format("Project(s) with id: %d not found.",id),id);
        return result;
    }

    @Override
    public Project update(Project project) throws ProjectNotFoundException {
            Project p= findById(project.getId());
            /* Make repository update only, not create new */
            p.setName(project.getName());
            p.setFinishingDate(project.getFinishingDate());
            p.setCustomer(project.getCustomer());
            projectRepository.save(p);
            return p;
    }
}
