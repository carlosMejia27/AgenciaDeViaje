package com.example.demo.dominan.entities.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Role {

    @Field(name = "granted_authorities") //colum para darle el nombre
    private Set<String> grantedAuthorities;
}
