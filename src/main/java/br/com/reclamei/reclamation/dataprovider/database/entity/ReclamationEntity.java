package br.com.reclamei.reclamation.dataprovider.database.entity;

import br.com.reclamei.reclamation.core.type.ReclamationStatusType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reclamation")
public class ReclamationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "citizen_id", nullable = false)
    private Long citizenId;

    @Column(name = "service_subtype_id", nullable = false)
    private Long serviceSubtypeId;

    @Column(name = "description", nullable = false)
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "analyzed_at", nullable = false)
    private LocalDateTime analyzedAt;

    @Lob
    @Column(name = "photo", nullable = false)
    private String photo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReclamationStatusType status;

    @OneToOne(mappedBy = "reclamation", cascade = CascadeType.ALL, orphanRemoval = true)
    private LocalizationEntity localization;

    @OneToMany(mappedBy = "reclamation", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ResponseEntity> responses;

}
