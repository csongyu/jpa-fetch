package xyz.csongyu.jpafetch.dao;

import java.util.Set;

import xyz.csongyu.jpafetch.entity.Company;

public interface CompanyDao {
    Company findByIdWithUsers(String id);

    Set<Company> findByIdsWithUsers(Set<String> ids);
}
