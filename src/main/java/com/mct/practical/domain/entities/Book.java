package com.mct.practical.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "tbl_book")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Positive(message = "Author invalid!")
    @Column(name = "author_id")
    private Long authorId;

    @NotBlank(message = "Name is not empty!")
    @NotNull(message = "Name is not null!")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Price is not empty!")
    @Positive(message = "Price must positive numbers!")
    @Column(name = "price")
    private Double price;

    @NotNull(message = "Quantity is not empty!")
    @Positive(message = "Quantity must positive numbers!")
    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private Author author;

    public Book() {
    }

    public Book(Long id, Long authorId, String name, Double price, Integer quantity) {
        this.id = id;
        this.authorId = authorId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
