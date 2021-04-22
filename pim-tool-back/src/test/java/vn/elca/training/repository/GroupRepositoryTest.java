package vn.elca.training.repository;

import com.querydsl.jpa.impl.JPAQuery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import vn.elca.training.ApplicationWebConfig;
import vn.elca.training.dao.GroupRepository;
import vn.elca.training.model.entity.Group;
import vn.elca.training.model.entity.QGroup;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ContextConfiguration(classes = {ApplicationWebConfig.class})
@RunWith(value= SpringRunner.class)
public class GroupRepositoryTest {
    @PersistenceContext
    EntityManager em;
    @Autowired
    GroupRepository groupRepository;

    @Test
    public void testAddGroup(){
        String name = "New Group";
        long projectCountBefore = groupRepository.count();
        groupRepository.save(new Group(name));
        Group group = new JPAQuery<Group>(em)
                .from(QGroup.group)
                .orderBy(QGroup.group.id.desc())
                .fetchFirst();
        Assert.assertEquals(group.getName(), name);
        Assert.assertEquals(projectCountBefore + 1, groupRepository.count());
    }

    @Test
    @Transactional
    public void testDeleteGroupByName(){
        String name = "DELETED_GROUP";
        groupRepository.save(new Group(name));
        long projectCountBefore = groupRepository.count();
        groupRepository.deleteByName(name);
        Assert.assertEquals(groupRepository.count(), projectCountBefore - 1);
    }
}
