package com.bloggingApp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
	
	private Long id;
    
    @NotEmpty
    private String firstName;
    
    @NotBlank
    private String lastName;
    
    @Email(message="Email is not valid !!!!S")
    private String email;
    
    @NotNull
    private String mobile;
    @Min(4)
    @Max(20)
    private String address;

   
}

