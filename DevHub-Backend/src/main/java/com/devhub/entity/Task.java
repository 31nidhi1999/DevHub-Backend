package com.devhub.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="tasks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Task extends BaseEntity {
	@Column(unique = true,nullable = false)
	private String title;
	
	@Column(nullable = false)
    private String description;
	
    private boolean completed=false;	
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="projectId")
    private Project project;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="userId")
    private User user;
    
    
}
