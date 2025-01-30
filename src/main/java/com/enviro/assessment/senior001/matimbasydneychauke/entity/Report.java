package com.enviro.assessment.senior001.matimbasydneychauke.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table
@Data
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String reportName;

    private String reportType;

    private String description;

    private LocalDate createdDateTime;
}
