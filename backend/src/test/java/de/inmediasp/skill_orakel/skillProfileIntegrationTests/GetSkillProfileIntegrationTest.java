package de.inmediasp.skill_orakel.skillProfileIntegrationTests;

import com.c4_soft.springaddons.security.oauth2.test.annotations.OpenIdClaims;
import com.c4_soft.springaddons.security.oauth2.test.annotations.WithOAuth2Login;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.dto.SkillProfileDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
public class GetSkillProfileIntegrationTest {

    private final DummySkillProfile dummySkillProfile = new DummySkillProfile();

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final String validId = "9483a127-d4ba-4f97-b5da-eec97c86036b";
    private final String URL = "http://localhost:8080/api/skillprofile/";

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    @WithOAuth2Login(claims = @OpenIdClaims(  sub = "xeXzdawmDkaZ4UdGqdlWYGTyUaLOo_-19z21vb1b2BQ"))
    void GIVEN_callGetSkillProfileWithValidId_THEN_responseIs200OK() throws Exception {

        mockMvc.perform(get(URL + validId))
                .andExpect(status().isOk());
    }

    @Test
    @WithOAuth2Login(claims = @OpenIdClaims(  sub = "xeXzdawmDkaZ4UdGqdlWYGTyUaLOo_-19z21vb1b2BQ"))
    void GIVEN_callGetSkillProfileWithInvalidId_THEN_responseIs400BadRequest() throws Exception {

        String invalidId = "09345720934";

        mockMvc.perform(get(URL + invalidId))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithOAuth2Login(claims = @OpenIdClaims(  sub = "xeXzdawmDkaZ4UdGqdlWYGTyUaLOo_-19z21vb1b2BQ"))
    void GIVEN_callGetSkillProfileWhereNoProfileMatchesId_THEN_responseIs404NotFound() throws Exception {

        String validIdWithoutMatchingProfile = "0957b99d-737a-4276-aecf-e7b587aa911d";

        mockMvc.perform(get(URL + validIdWithoutMatchingProfile))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithOAuth2Login(claims = @OpenIdClaims(  sub = "xeXzdawmDkaZ4UdGqdlWYGTyUaLOo_-19z21vb1b2BQ"))
    void GIVEN_callGetSkillProfileWithValidId_THEN_ValidSkillProfileIsReturned() throws Exception {

        SkillProfileDTO expectedResult = dummySkillProfile.getSkillProfileDTO();

        mockMvc.perform(get(URL + validId)).andExpect(status().isOk());
        MvcResult result = mockMvc.perform(get(URL + validId))
                .andReturn();

        String responseAsString = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        SkillProfileDTO serializedResponse = objectMapper.readValue(responseAsString, SkillProfileDTO.class);

        assertEquals(expectedResult, serializedResponse);
    }
}
