package com.example.jobs.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(50) not null")
    @NotEmpty(message = "Title is required.")
    @Size(min = 5, message = "Title Length must be more than 4 characters.")
    private String title;

    @NotEmpty(message = "Description is required.")
    @Pattern(regexp = "^[a-zA-Z0-9\\s.,!?-]+$", message = "Description must contain only letters, numbers, and common punctuation marks.")
    @Column(columnDefinition = "text(100)")
    private String description;


    @Column(columnDefinition = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postingDate;
}

