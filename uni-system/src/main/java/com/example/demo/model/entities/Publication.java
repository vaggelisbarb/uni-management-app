package com.example.demo.model.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "publication")
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

    public  Publication() {}

    public Publication(Long id, String title, Professor professor, List<String> authors, Date publicationDate, String journalOrConference, String doi, String abstractText, List<String> keywords, String url) {
        this.id = id;
        this.title = title;
        this.professor = professor;
        this.authors = authors;
        this.publicationDate = publicationDate;
        this.journalOrConference = journalOrConference;
        this.doi = doi;
        this.abstractText = abstractText;
        this.keywords = keywords;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getJournalOrConference() {
        return journalOrConference;
    }

    public void setJournalOrConference(String journalOrConference) {
        this.journalOrConference = journalOrConference;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", publicationDate=" + publicationDate +
                ", journalOrConference='" + journalOrConference + '\'' +
                ", doi='" + doi + '\'' +
                ", abstractText='" + abstractText + '\'' +
                ", keywords=" + keywords +
                ", url='" + url + '\'' +
                '}';
    }
}
