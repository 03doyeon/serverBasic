package com.avengers.avengerstaxi.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "school")
public class SchoolEntity {
    @Id
    @GeneratedValue //그냥 너가 자동생성 해줘

    public Long id;

    public String name;
    public int totalStudent;
    public int totalTeacher;
}
