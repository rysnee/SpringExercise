package vn.elca.training.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Position {
    @Id
    private long id;

    @Column(unique = true)
    private String name;

    @OneToMany( mappedBy = "position")
    private Set<User> users;

    public void setId(long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setUsers(Set<User> users) { this.users = users; }

    public String getName() { return name; }

    public long getId() { return id; }

    public Set<User> getUsers() { return users; }
}
