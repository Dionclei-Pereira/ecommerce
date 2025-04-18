package me.dionclei.ecommerce_user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
		
	    @NotBlank(message = "Nome é obrigatório")
	    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
	    String name,

	    @NotBlank(message = "Email é obrigatório")
	    @Email(message = "Email inválido")
	    String email,

	    @NotBlank(message = "Senha é obrigatória")
	    @Size(min = 6, max = 100, message = "Senha deve ter entre 6 e 100 caracteres")
	    String password) {

}
