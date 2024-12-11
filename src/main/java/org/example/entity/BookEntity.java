package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books", schema = "bookschema")
public class BookEntity {

    @Id
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "release_year")
    private String releaseYear;
}
