package com.devhub.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="projects")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseEntity {
	@Column(unique = true,nullable = false)
	  private String name;
	@Column(nullable = false)
	  private String description;
	    
	  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	  private List<Task> tasks = new ArrayList<>();
}
