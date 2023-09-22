package com.itstep.hello_spring.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class PersonGroupRequest {
    private UUID group_id;
    private UUID person_id;
}