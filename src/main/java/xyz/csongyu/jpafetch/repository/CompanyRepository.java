package xyz.csongyu.jpafetch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.csongyu.jpafetch.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, String> {}
