package de.inmediasp.skill_orakel.skillProfileIntegrationTests;

import com.c4_soft.springaddons.security.oauth2.test.annotations.OpenIdClaims;
import com.c4_soft.springaddons.security.oauth2.test.annotations.WithOAuth2Login;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.model.BasicInfoBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.db.entity.SkillProfile;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.dto.SkillProfileDTO;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ActiveProfiles("emptySkillProfileTest")
@SpringBootTest
public class GetSkillProfileWithTestPropertyIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final String URL = "http://localhost:8080/api/skillprofile/";
    private final String validId = "9483a127-d4ba-4f97-b5da-eec97c86036b";


    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    //@Test
    @WithOAuth2Login(claims = @OpenIdClaims(  sub = "xeXzdawmDkaZ4UdGqdlWYGTyUaLOo_-19z21vb1b2BQ"))
    void GIVEN_all_tables_are_empty_except_skillProfile_THEN_skillProfile_is_RETURNED() throws Exception {

        MvcResult result = mockMvc.perform(get(URL + validId))
                .andReturn();

        String responseAsString = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);


        SkillProfileDTO serializedResponse = objectMapper.readValue(responseAsString, SkillProfileDTO.class);

        BasicInfoBusinessObject basicInfo = new BasicInfoBusinessObject();
        basicInfo.setId(UUID.fromString("e1750f44-c689-46a6-826b-56fd3efd0ed8"));
        basicInfo.setFirstName("Heinz");
        basicInfo.setLastName("Testmeister");

        SkillProfile expectedSkillProfile = new SkillProfile();
        expectedSkillProfile.setId(UUID.fromString("9483a127-d4ba-4f97-b5da-eec97c86036b"));

        SkillProfileDTO expectedResult = new SkillProfileDTO();
        expectedResult.setSkillProfileId(UUID.fromString("9483a127-d4ba-4f97-b5da-eec97c86036b"));
        expectedResult.setBasicInfo(basicInfo);
        expectedResult.setSkills(new ArrayList<>());
        expectedResult.setLanguages(new ArrayList<>());
        expectedResult.setIndustryKnowledges(new ArrayList<>());
        expectedResult.setQualifications(new ArrayList<>());
        expectedResult.setProjectAssignments(new ArrayList<>());
        expectedResult.setWorkExperience(new ArrayList<>());


        assertEquals(expectedResult, serializedResponse);
    }
}
