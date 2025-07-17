package com.restaurant.api.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String homepage;

    private LocalDate releasedate;

    @Column(columnDefinition = "TEXT")
    private String overview;

    private String posterpath;

    private Integer runtime;

    private String tagline;

    @Column(precision = 5, scale = 2)
    private BigDecimal popularity;

    private String imdbid;

    @Column(precision = 3, scale = 1)
    private BigDecimal voteaverage;

    private Integer votecount;

    // Constructors
    public Film() {}

    public Film(String title, String homepage, LocalDate releasedate, String overview,
                String posterpath, Integer runtime, String tagline, BigDecimal popularity,
                String imdbid, BigDecimal voteaverage, Integer votecount) {
        this.title = title;
        this.homepage = homepage;
        this.releasedate = releasedate;
        this.overview = overview;
        this.posterpath = posterpath;
        this.runtime = runtime;
        this.tagline = tagline;
        this.popularity = popularity;
        this.imdbid = imdbid;
        this.voteaverage = voteaverage;
        this.votecount = votecount;
    }

    // Getters and Setters
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

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public LocalDate getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(LocalDate releasedate) {
        this.releasedate = releasedate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterpath() {
        return posterpath;
    }

    public void setPosterpath(String posterpath) {
        this.posterpath = posterpath;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public BigDecimal getPopularity() {
        return popularity;
    }

    public void setPopularity(BigDecimal popularity) {
        this.popularity = popularity;
    }

    public String getImdbid() {
        return imdbid;
    }

    public void setImdbid(String imdbid) {
        this.imdbid = imdbid;
    }

    public BigDecimal getVoteaverage() {
        return voteaverage;
    }

    public void setVoteaverage(BigDecimal voteaverage) {
        this.voteaverage = voteaverage;
    }

    public Integer getVotecount() {
        return votecount;
    }

    public void setVotecount(Integer votecount) {
        this.votecount = votecount;
    }
}