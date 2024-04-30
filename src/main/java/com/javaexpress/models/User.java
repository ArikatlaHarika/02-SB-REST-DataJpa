package com.javaexpress.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String email;

	 @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	 @JsonIgnore //to avoid jackson infinite recursion problem
	private List<Order> orderList;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Review> reviews;


}
