package br.com.carlosoliveira.desafio01.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "curso")
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Nome do curso", requiredMode = RequiredMode.REQUIRED)
    @NotBlank(message = "O campo [category] é obrigatório.")
    private String name;

    @Schema(example = "Categoria do curso", requiredMode = RequiredMode.REQUIRED)
    @NotBlank(message = "O campo [category] é obrigatório.")
    private String category;
    /**
     * @Enumerated(EnumType.STRING)
     *                              private CursoStatusEnum active;
     */
    private boolean active;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

}
