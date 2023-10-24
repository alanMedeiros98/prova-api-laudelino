package br.com.senai.tiposmktplaceapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import br.com.senai.tiposmktplaceapi.entity.TipoCardapio;
import br.com.senai.tiposmktplaceapi.entity.enums.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
public interface TipoCardapioService {

	public TipoCardapio salvar(
			@Valid
			@NotNull(message = "O tipo do cardápio é obrigatório")
			TipoCardapio tipoCardapio);
	
	public Page<TipoCardapio> listarPor(
			@NotNull(message = "O nome do tipo do cardápio é obrigatório")
			String nome, Pageable paginacao);
	
	public TipoCardapio buscarPor(
			@NotNull(message = "O id é obrigatório")
			@Positive(message = "O id deve ser positivo")
			Integer id);
	
	public void atualizarStatusPor(
			@NotNull(message = "O id é obrigatório")
			@Positive(message = "O id deve ser positivo")
			Integer id,
			@NotNull(message = "O status é obrigatório")
			Status status);
	
}
