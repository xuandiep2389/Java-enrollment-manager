package com.will.enrollmentmanager.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    private String gender;

    @NotNull
    private String age;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "enrollment",
        joinColumns = {@JoinColumn(name = "student_id")},
        inverseJoinColumns = { @JoinColumn(name = "course_id")})
    private Set<Course> courses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Student(@NotNull @Size(max = 100) String name, @NotNull String gender, @NotNull String age, Set<Course> courses) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.courses = courses;
    }

    public Student(@NotNull @Size(max = 100) String name, @NotNull String gender, @NotNull String age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public Student() {
    }
}
