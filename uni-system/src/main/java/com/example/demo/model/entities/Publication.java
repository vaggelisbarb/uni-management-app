package com.example.demo.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "publication")
@Data  // Lombok generates getters, setters, equals, hashCode, toString
@NoArgsConstructor  // Generates a no-args constructor
@AllArgsConstructor  // Generates an all-args constructor
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto-generated ID
    private Long id; // Unique identifier for the publication

    @Column(nullable = false)  // Not null constraint for title
    private String title; // Title of the publication

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor; // The professor associated with the publication

    @ElementCollection  // To handle the list of authors
    @CollectionTable(name = "publication_authors", joinColumns = @JoinColumn(name = "publication_id"))
    @Column(name = "author")
    private List<String> authors; // List of authors' names

    @Column(nullable = false)
    private Date publicationDate; // Date when the publication was released

    @Column(name = "journal_or_conference")
    private String journalOrConference; // Journal or conference where it was published

    private String doi; // Digital Object Identifier (DOI) for the publication

    @Column(length = 2000)
    private String abstractText; // Summary of the publication

    @ElementCollection  // To handle keywords as a collection
    @CollectionTable(name = "publication_keywords", joinColumns = @JoinColumn(name = "publication_id"))
    @Column(name = "keyword")private List<String> keywords; // Keywords associated with the publication
    private String url; // URL to access the full paper (if available)
}
