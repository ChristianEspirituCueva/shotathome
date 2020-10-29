package com.ShopAtHome.Repository;

import com.ShopAtHome.Entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends
        JpaRepository<Qualification , Long> {

    List<Qualification> findByUserId(Long userId);
    List<Qualification> findByStoreId(Long storeId);

}