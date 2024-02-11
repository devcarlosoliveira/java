package br.com.carlosoliveira.desafio01.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {

    @Schema(example = "Nome do curso")
    private String name;

    @Schema(example = "Categoria do curso")
    private String category;
}
