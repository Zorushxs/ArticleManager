package com.korber.task.api.domain;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private String id;
    private String description;
    private Double weight;
    private Double volume;
    private Boolean isActive;

}
