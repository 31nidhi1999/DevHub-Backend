package com.devhub.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tasks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task extends BaseEntity {
	@Column(unique = true,nullable = false)
	private String title;
	
	@Column(nullable = false)
    private String description;
    private boolean completed;	
    
    @ManyToOne
    private Project project;
}
