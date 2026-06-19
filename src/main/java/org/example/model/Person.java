package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personid")
    private int personid;

    @Column(name = "fullname")
    @Size(min = 5, max = 100, message = "Полное имя человека не должно быть менее 5 и более 100 символов")
    private String fullName;

    @Column(name = "birthyear")
    @Max(value = 2025, message = "Год рождения должен быть не более 2025")
    @Min(value = 1925, message = "Год рождения должен быть не раньше 1925")
    private int birthYear;

    @OneToMany(mappedBy = "reader")
    private List<Book> books;

    public Person(int personid, String fullName, int birthYear) {
        this.personid = personid;
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public Person() {}

    public int getPersonid() {
        return personid;
    }
    public void setPersonid(int personid) {
        this.personid = personid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
