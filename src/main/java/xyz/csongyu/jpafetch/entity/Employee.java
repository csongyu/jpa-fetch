package xyz.csongyu.jpafetch.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(of = {"code"})
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    // whatever FetchType is LAZY or EAGER
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;
}
