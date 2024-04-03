package com.uet.instagrem.modules.post.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
public class PostRequest {
    public String content;
    public String created;
}
