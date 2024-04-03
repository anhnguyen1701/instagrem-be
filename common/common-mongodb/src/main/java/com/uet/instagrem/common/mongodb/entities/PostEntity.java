package com.uet.instagrem.common.mongodb.entities;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("posts")
@Builder
public class PostEntity {
    @Id
    private String id;
    private String content;
    private String createdBy;
}
