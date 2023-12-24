package com.ruler.auth.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name="updated_at")
    private LocalDateTime updatedDate;
    @Column(name="created_at", updatable=false)
    private LocalDateTime createdDate;
    @Column(name="deleted_at")
    private LocalDateTime deletedDate;

}
