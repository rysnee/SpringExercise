package vn.elca.training.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "position")
    private Set<Employee> employees = new HashSet<>();

    public Position(){}

    public Position(String name){ this.name = name;}

    public void setId(long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public String getName() { return name; }

    public long getId() { return id; }


}
