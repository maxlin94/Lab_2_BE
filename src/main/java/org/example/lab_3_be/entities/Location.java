package org.example.lab_3_be.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @ColumnDefault("1")
    @Column(name = "public")
    private Boolean publicField;

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_edited")
    private Instant lastEdited;
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getPublicField() {
        return publicField;
    }

    public void setPublicField(Boolean publicField) {
        this.publicField = publicField;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Instant lastEdited) {
        this.lastEdited = lastEdited;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

/*
 TODO [Reverse Engineering] create field to map the 'coordinates' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "coordinates", columnDefinition = "point not null")
    private Object coordinates;
*/
}