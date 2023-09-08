package org.example.myClassWork.september_06.crm.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
    private UUID customer_id;
    private UUID user_id;
    private String name;
}
