package com.thoughtworks.capability.gtb.restfulapidesign.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements Comparable<Student>{

    private int id;
    private String name;
    private GenderType gender;
    private String note;



    @Override
    public int compareTo(Student o) {
        if (this.id > o.id)
            return 1;
        if (this.id < o.id)
            return -1;
        else
            return 0;
    }
}
