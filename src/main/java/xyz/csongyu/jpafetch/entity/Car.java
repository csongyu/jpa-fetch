package xyz.csongyu.jpafetch.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"employee"})
@EqualsAndHashCode(of = {"plate"})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String plate;

    private String name;

    @ManyToOne
    private Employee employee;
}
