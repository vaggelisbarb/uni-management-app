package com.example.demo.model.entities;

import java.util.Date;
import java.util.List;

public class Publication {

    private String title; // Title of the publication
    private List<String> authors; // List of authors' names
    private Date publicationDate; // Date when the publication was released
    private String journalOrConference; // Journal or conference where it was published
    private String doi; // Digital Object Identifier (DOI) for the publication
    private String abstractText; // Summary of the publication
    private List<String> keywords; // Keywords associated with the publication
    private String url; // URL to access the full paper (if available)

    public  Publication() {}

    public Publication(String title, List<String> authors, Date publicationDate, String journalOrConference, String doi, String abstractText, List<String> keywords, String url) {
        this.title = title;
        this.authors = authors;
        this.publicationDate = publicationDate;
        this.journalOrConference = journalOrConference;
        this.doi = doi;
        this.abstractText = abstractText;
        this.keywords = keywords;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
