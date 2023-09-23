package com.itstep.hello_spring.models.mongo;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "myDocumentCollection")
public class MongoDocument {
    @Id
    private String id;
    private String name;
}
