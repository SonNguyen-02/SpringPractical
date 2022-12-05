package com.mct.practical.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "tbl_author")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name is not empty!")
    @NotNull(message = "Name is not null!")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Email is not empty!")
    @NotNull(message = "Email is not null!")
    @Email(message = "Email invalid!")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Gender invalid!")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @OneToMany(targetEntity = Book.class, mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;

    public Author() {
    }

    public Author(Long id, String name, String email, Gender gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(int code) {
        try {
            this.gender = Gender.values()[code];
        } catch (Throwable t) {
            this.gender = Gender.OTHER;
        }
    }

    public void setGender(String gender) {
        try {
            this.gender = Gender.valueOf(gender);
        } catch (Throwable t) {
            this.gender = Gender.OTHER;
        }
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public enum Gender {
        MALE, FEMALE, OTHER;
    }
}