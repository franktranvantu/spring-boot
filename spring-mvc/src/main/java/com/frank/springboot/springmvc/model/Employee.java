package com.frank.springboot.springmvc.model;

public class Employee {

    private Integer id;
    private String name;
    private String team;

    public Employee(Integer id, String name, String team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team='" + team + '\'' +
                '}';
    }
}
