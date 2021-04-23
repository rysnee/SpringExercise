package vn.elca.training.repository;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.google.common.collect.Sets;
import com.querydsl.jpa.impl.JPAQuery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import vn.elca.training.ApplicationWebConfig;
import vn.elca.training.dao.GroupRepository;
import vn.elca.training.dao.PositionRepository;
import vn.elca.training.model.entity.*;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.QProject;
import vn.elca.training.model.entity.QPosition;

@ContextConfiguration(classes = {ApplicationWebConfig.class})
@RunWith(value = SpringRunner.class)
public class ProjectRepositoryTest {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private GroupRepository groupRepository;


    @Test
    public void testCountAll() {
        projectRepository.save(new Project("KSTA", LocalDate.now()));
        projectRepository.save(new Project("LAGAPEO", LocalDate.now()));
        projectRepository.save(new Project("ZHQUEST", LocalDate.now()));
        projectRepository.save(new Project("SECUTIX", LocalDate.now()));
        Assert.assertEquals(9, projectRepository.count());
    }

    @Test
    public void testFindOneWithQueryDSL() {
        final String PROJECT_NAME = "KSTA";
        projectRepository.save(new Project(PROJECT_NAME, LocalDate.now()));
        Project project = new JPAQuery<Project>(em)
                .from(QProject.project)
                .where(QProject.project.name.eq(PROJECT_NAME))
                .fetchFirst();
        Assert.assertEquals(PROJECT_NAME, project.getName());
    }

    @Test
    public void testAddProject() {
        String name = "New Project";
        long projectCountBefore = projectRepository.count();
        projectRepository.save(new Project(name, LocalDate.now()));
        Project Project = new JPAQuery<Project>(em)
                .from(QProject.project)
                .orderBy(QProject.project.id.desc())
                .fetchFirst();
        Assert.assertEquals(Project.getName(), name);
        Assert.assertEquals(projectCountBefore + 1, projectRepository.count());
    }


    @Test
    @Transactional
    public void addGroupsWithMultipleProjects() {
        // Create positions
        Set<String> positionNames = new HashSet<>(
                Arrays.asList("Group Leader", "Project Leader",
                        "Developer", "Quality Agent"));
        Map<String, Position> positions = new HashMap<String, Position>();
        for (String positionName : positionNames) {
            positions.put(positionName, new Position(positionName));          
        }
        
        // Create groups   
        Set<String> groupNames = new HashSet<>(
                Arrays.asList("Group A", "Group B"));
        Map<String, Group> groups = new HashMap<String, Group>();
        for (String groupName : groupNames) {
            groups.put(groupName, new Group(groupName));           
        }
        
        // Create users     
        Set<String> userNames = new HashSet<>(
                Arrays.asList("QMV", "HTV", "QKP", "MKN", "TQP", "HNH", 
                        "NQN", "PLH", "HNL", "TBH", "TDN", "HPN",
                        "HUN", "BNN", "PNH", "VVT", "APL", "XHP"));
        Map<String, User> users = new HashMap<String, User>();
        for (String userName : userNames) {
            users.put(userName, new User(userName));
        }

        // Create projects
        Set<String> projectNames = new HashSet<>(
                Arrays.asList("EFV", "CXTRANET", "CRYSTAL BALL",
                        "IOC CLIENT EXTRANET", "KSTA MIGRATION"));
        Map<String, Project> projects = new HashMap<String, Project>();
        for (String projectName : projectNames) {
            projects.put(projectName, new Project(projectName, LocalDate.now()));
        }

        // Create employees for projects
        HashSet<Employee> employees = new HashSet<>(Arrays.asList(
                new Employee(users.get("HTV"),positions.get("Project Leader")),
                new Employee(users.get("TQP"),positions.get("Developer")),
                new Employee(users.get("HNH"),positions.get("Quality Agent")),
                new Employee(users.get("NQN"),positions.get("Developer"))));
        projects.get("EFV").setEmployees(employees);

        employees = new HashSet<>(Arrays.asList(
                new Employee(users.get("QKP"),positions.get("Project Leader")),
                new Employee(users.get("HNL"),positions.get("Developer")),
                new Employee(users.get("PLH"),positions.get("Quality Agent"))));
        projects.get("CXTRANET").setEmployees(employees);

        employees = new HashSet<>(Arrays.asList(
                new Employee(users.get("MKN"),positions.get("Project Leader")),
                new Employee(users.get("TDN"),positions.get("Developer")),
                new Employee(users.get("TBH"),positions.get("Quality Agent"))));
        projects.get("CRYSTAL BALL").setEmployees(employees);

        employees = new HashSet<>(Arrays.asList(
                new Employee(users.get("APL"),positions.get("Project Leader")),
                new Employee(users.get("HPN"),positions.get("Developer")),
                new Employee(users.get("HUN"),positions.get("Quality Agent")),
                new Employee(users.get("BNN"),positions.get("Developer")),
                new Employee(users.get("PNH"),positions.get("Developer"))));
        projects.get("IOC CLIENT EXTRANET").setEmployees(employees);

        employees = new HashSet<>(Arrays.asList(
                new Employee(users.get("XHP"),positions.get("Project Leader")),
                new Employee(users.get("VVT"),positions.get("Developer")),
                new Employee(users.get("QMV"),positions.get("Quality Agent"))));
        projects.get("KSTA MIGRATION").setEmployees(employees);

        projectRepository.saveAll(projects.values());
        Assert.assertEquals(1,1);
    }
}
