package de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers;


public interface EntityToBusinessObjectMapper<T, S> {
    T convertToBusinessObject(S entity);
}
