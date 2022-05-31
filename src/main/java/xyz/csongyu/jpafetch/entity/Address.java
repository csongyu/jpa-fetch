package xyz.csongyu.jpafetch.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"employee"})
@EqualsAndHashCode(of = {"id"})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String city;

    private String street;

    @OneToOne
    private Employee employee;
}
