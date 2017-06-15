package net.proselyte.pmsystem.model;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents a Developer.
 *
 * @author Eugene Suleimanov
 */

public class Developer extends BaseEntity {

    private String firstName;

    private String lastName;

    private Integer age;

    private BigDecimal salary;

    private Integer yearsOfExperience;

    private String experience;

    private Set<Skill> skills;

    private Set<Specialty> specialties;

    public Developer() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", yearsOfExperience=" + yearsOfExperience +
                ", experience='" + experience + '\'' +
                ", skills=" + skills +
                ", specialties=" + specialties +
                '}';
    }
}

