package com.dao;

import com.models.Collateral;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollateralRepository extends CrudRepository<Collateral, Long> {
     void delete(Long id);
}
