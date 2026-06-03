package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookid")
    private int bookid;

    @Column(name = "name")
    @Size(min = 2, max = 100, message = "Название книги не должно быть менее 2 и более 50 символов")
    private String name;

    @Column(name = "author")
    @Size(min = 5, max = 100, message = "Имя автора не должно быть менее 5 и более 100 символов")
    private String author;

    @Column(name = "year")
    @Min(value = 1501, message = "Год написания книги должен быть более 1500")
    private int year;

    @ManyToOne
    @JoinColumn(name = "personid", referencedColumnName = "personid")
    private Person reader;

    public Book(int bookid, String name, String author, int year) {
        this.bookid = bookid;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {}

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getReader() {
        return reader;
    }

    public void setReader(Person reader) {
        this.reader = reader;
    }
}
