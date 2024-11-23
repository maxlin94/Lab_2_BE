package org.example.lab_3_be.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.locationtech.jts.geom.Point;


import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "location")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Point coordinates;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

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
    @Column(name = "last_edited", insertable = false)
    private Instant lastEdited;
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", updatable = false, insertable = false)
    private Instant createdAt;

}