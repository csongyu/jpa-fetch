package xyz.csongyu.jpafetch.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import xyz.csongyu.jpafetch.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, String> {
    @Query("from Company c join fetch c.employees where c.code = :id")
    Company findByIdWithUsers(@Param("id") String id);

    @Query("from Company c join fetch c.employees where c.code in (:ids)")
    Set<Company> findByIdsWithUsers(@Param("ids") Set<String> ids);
}
