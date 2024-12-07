package de.inmediasp.skill_orakel.skill_profile.services.domain_model.basic_info.service;

import org.junit.jupiter.api.Test;

import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.BasicInfoService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.db.BasicInfoRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.db.entity.BasicInfo;
import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.model.BasicInfoBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.service.BasicInfoMapper;
import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.service.BasicInfoServiceImpl;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasicInfoServiceImplTests {

   private final BasicInfoRepository repository = mock(BasicInfoRepository.class);
   private final BasicInfoMapper basicInfoEntityObjectMapper = mock(BasicInfoMapper.class);
   private final BasicInfoService service = new BasicInfoServiceImpl(repository, basicInfoEntityObjectMapper);

   @Test
   void GIVEN_db_is_empty_THEN_find_by_id_RETURNS_Null(){
        
        Optional<BasicInfo> optional = Optional.empty();
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(optional);


        var result = service.findById(id);
        assertNull(result);
   }

   @Test
   void GIVEN_db_has_entry_with_id_THEN_find_by_id_returns_that_element(){

        BasicInfo basicInfo = new BasicInfo();
        BasicInfoBusinessObject basicInfoBusinessObject = new BasicInfoBusinessObject();
        Optional<BasicInfo> optional = Optional.of(basicInfo);

        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(optional);
        when(basicInfoEntityObjectMapper.convertToBusinessObject(optional.get()))
                .thenReturn(basicInfoBusinessObject);

        BasicInfoBusinessObject result = service.findById(id);

        assertEquals(basicInfoBusinessObject, result);
   }
}
