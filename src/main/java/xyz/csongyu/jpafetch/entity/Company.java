package xyz.csongyu.jpafetch.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(of = {"code"})
public class Company implements Serializable {
    @Id
    private String code;

    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<>();
}
