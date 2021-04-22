package vn.elca.training.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GROUP_TABLE")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Project> projects;

    @OneToOne()
    @JoinColumn
    private User groupLeader;

    public Group(){};

    public Group(String name){ this.name = name; }

    public void setId(long id) { this.id = id; }

    public long getId() { return id; }

    public void setName(String name) { this.name = name; }

    public String getName() { return name; }

    public void setProjects(List<Project> projectss) { this.projects = projects; }

    public List<Project> getProjects() { return projects; }

    public void setGroupLeader(User groupLeader) { this.groupLeader = groupLeader; }

    public User getGroupLeader() { return groupLeader; }


}
