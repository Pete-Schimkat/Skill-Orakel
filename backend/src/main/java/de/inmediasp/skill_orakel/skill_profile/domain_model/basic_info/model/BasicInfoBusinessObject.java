package de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.model;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicInfoBusinessObject {
        
        @NotNull
        private UUID id;
        @NotNull
        private String firstName;
        @NotNull
        private String lastName;
}
