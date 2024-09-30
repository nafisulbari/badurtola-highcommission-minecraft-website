package com.nafisulbari.minecraft.website.repository;

import com.nafisulbari.minecraft.website.entity.SiteVisitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SiteVisitorRepository extends JpaRepository<SiteVisitor, Integer> {

    Optional<SiteVisitor> getSiteVisitorByIpAddress(String ipAddress);

    @Modifying
    @Transactional
    @Query("UPDATE SiteVisitor s SET s.visitingCount = s.visitingCount + 1 WHERE s.ipAddress = :ipAddress")
    void incrementVisitingCount(String ipAddress);


}
