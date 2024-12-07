package de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers;

import java.util.List;
import java.util.UUID;

public interface EntitiesToBusinessObjectsMapper<T, S> {
    
    List<T> convertToBusinessObjects(List<S> entities);
    List<S> convertToEntities(List<T> businessObjects, UUID skillProfileId);
}
