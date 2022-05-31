package xyz.csongyu.jpafetch.dao;

import xyz.csongyu.jpafetch.entity.Company;

public interface CompanyDao {
    Company findByIdWithUsers(String id);
}
