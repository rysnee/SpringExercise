package vn.elca.training.service.impl.dummy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.ProjectNotFoundException;
import vn.elca.training.service.ProjectService;

import java.util.List;

/**
 * @author gtn
 *
 */
@Component
@Profile("dummy")
@Qualifier("componentProjectDummy")
public class FirstDummyProjectServiceImpl extends AbstractDummyProjectService implements ProjectService {

    @Override
    public List<Project> findAll() {
        throw new UnsupportedOperationException("This is first dummy service");
    }

    @Override
    public List<Project> findByKeyword(String keyword) {
        return null;
    }

    @Override
    public long count() {
        printCurrentActiveProfiles();
        throw new UnsupportedOperationException("This is first dummy service");
    }

    @Override
    public Project findById(long id){
        printCurrentActiveProfiles();
        throw new UnsupportedOperationException("This is first dummy service");
    }

    @Override
    public Project update(Project project) throws ProjectNotFoundException {
        printCurrentActiveProfiles();
        throw new UnsupportedOperationException("This is first dummy service");
    }


}
