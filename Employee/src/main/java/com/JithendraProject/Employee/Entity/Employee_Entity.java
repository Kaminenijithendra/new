package com.JithendraProject.Employee.Entity;



import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Component
@Entity
@Table(name="Employee12")
@NoArgsConstructor
public class Employee_Entity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id = 0L;
    
    @Column(name = "name")
    private String Name;
    
    @Column(name = "email")
    private String Email;
    
    @Column(name = "mobileNO")
    private String MobileNO;

    
    @Column(name = "degignation")
    private String Designation;
    
    @Column(name = "gender")
    private String Gender;
    
    @Column(name = "courses")
    private String courses; 
    @Lob
    @Column(name = "image")
    private byte[] image;
    
    // Getters and setters
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobileNO() {
        return MobileNO;
    }

    public void setMobileNO(String mobileNO) {
        MobileNO = mobileNO;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Employee_Entity [Id=" + Id + ", Name=" + Name + ", Email=" + Email + ", MobileNO=" + MobileNO
                + ", Designation=" + Designation + ", Gender=" + Gender + ", Courses=" + courses + "]";
    }
}
