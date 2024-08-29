package com.subash.projects.findajob.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "companies")
@TypeAlias("company")
public class Company {

    @Id
    private String id;
    private String name;
    private String description;
    private List<String> jobs;
    private List<String> reviews;
}
