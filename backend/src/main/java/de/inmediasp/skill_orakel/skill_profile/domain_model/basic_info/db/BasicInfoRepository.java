package de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.db;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.db.entity.BasicInfo;

public interface BasicInfoRepository extends JpaRepository<BasicInfo, UUID> {
    
}
