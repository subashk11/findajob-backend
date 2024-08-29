package com.subash.projects.findajob.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "review")
@TypeAlias("review")
@Getter
@Setter
@NoArgsConstructor
public class Review {

    @Id
    private String id;
    private String title;
    private String description;
    private double rating;
    private String companyId;
}
