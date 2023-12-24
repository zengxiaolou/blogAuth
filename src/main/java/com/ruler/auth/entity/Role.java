package com.ruler.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Getter
public class Role {
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "roles_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )

    private Set<Permission> permissions = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }

    @PreRemove
    protected void onDelete() {
        this.deletedDate = LocalDateTime.now();
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
