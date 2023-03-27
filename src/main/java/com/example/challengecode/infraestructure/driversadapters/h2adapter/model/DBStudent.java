package com.example.challengecode.infraestructure.driversadapters.h2adapter.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alumno")
public class DBStudent  {
    @Id
    @Column("id")
    private Long id;
    private String name;
    private String lastname;
    private String state;
    private Integer age;
}
