package com.example.challengecode.domain.model;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private Long id ;
    @Size(min = 3, message = "El nombre debe tener mínimo 3 caracteres")
    private String name;
    @Size(min = 3,message = "El apellido debe tener mínimo 3 caracteres")
    private String lastname;
    @Pattern(regexp = "activo||inactivo", message = "El estado debe ser activo o inactivo")
    private String state;
    @Min(value = 1,message = "La edad mínima del estudiante debe ser de 1 año")
    private Integer age;
}
