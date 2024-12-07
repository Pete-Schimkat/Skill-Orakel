package de.inmediasp.skill_orakel.skillProfileIntegrationTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
public class SecurityIntegrationTest {
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
    void GIVEN_isNotOwnSkillProfileAndIsAdmin_Then_RESPONSE_is200K() throws Exception {

        List<String> roles = List.of("Admin");
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "user");
        map.put("sub", "claimThatDoesNotMatchProfile");
        map.put("roles", roles);

        OAuth2User user = new DefaultOAuth2User(
                new ArrayList<>(),
                map,
                "user_name");

        mockMvc.perform(get(URL + validId).with(oauth2Login().oauth2User(user)))
                .andExpect(status().isOk());
    }

    @Test
    void GIVEN_hasNoSubClaim_THEN_response_is_401Forbidden() throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "user");

        OAuth2User oAuth2User = new DefaultOAuth2User(
                new ArrayList<>(),
                map,
                "user_name"
        );

        mockMvc.perform(get(URL + validId).with(oauth2Login().oauth2User(oAuth2User)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void GIVEN_hasNoSubClaimAndIsAdmin_Then_response_is_401Forbidden() throws Exception {

        List<String> roles = List.of("Admin");
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "user");
        map.put("roles", roles);
        map.put("sub", null);

        OAuth2User oAuth2User = new DefaultOAuth2User(
                new ArrayList<>(),
                map,
                "user_name"
        );

        mockMvc.perform(get(URL + validId).with(oauth2Login().oauth2User(oAuth2User)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void GIVEN_isNotOwnSkillProfileAndIsNotAdmin_Then_RESPONSE_is_403Forbidden() throws Exception {

        List<String> roles = List.of("Mitarbeiter");
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "user");
        map.put("roles", roles);
        map.put("sub", "XeXzdawmDkaZ4UdGqdlWYGTyUaLOo_-19z21vb1b2BQ");

        OAuth2User oAuth2User = new DefaultOAuth2User(
                new ArrayList<>(),
                map,
                "user_name"
        );

        mockMvc.perform(get(URL + validId).with(oauth2Login().oauth2User(oAuth2User)))
                .andExpect(status().isForbidden());
    }
}
