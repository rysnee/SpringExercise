package vn.elca.training.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn
    private Position position;

    @ManyToMany(
            targetEntity = Project.class,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "employees")
    private Set<Project> projects = new HashSet<>();

    public Employee(){}

    public Employee(User user, Position position){
        this.user = user;
        this.position = position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public User getUser() {
        return user;
    }
}
