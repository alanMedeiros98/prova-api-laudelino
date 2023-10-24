package br.com.senai.tiposmktplaceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.tiposmktplaceapi.entity.TipoCardapio;
import br.com.senai.tiposmktplaceapi.entity.enums.Status;
import br.com.senai.tiposmktplaceapi.repository.TipoCardapiosRepository;
import br.com.senai.tiposmktplaceapi.service.TipoCardapioService;

@Service
public class TipoCardapioServiceImpl implements TipoCardapioService {

	@Autowired
	private  TipoCardapiosRepository repository;
	
	@Override
	public TipoCardapio salvar(TipoCardapio tipoCardapio) {
		TipoCardapio tipo = new TipoCardapio();
		tipo.setNome(tipoCardapio.getNome());
		tipo.setDescricao(tipoCardapio.getDescricao());
		tipo.setStatus(tipoCardapio.getStatus());
		return this.repository.saveAndFlush(tipo);
	}

	@Override
	public Page<TipoCardapio> listarPor(String nome, Pageable paginacao) {
		return repository.listarPor(nome, paginacao);
	}

	@Override
	public TipoCardapio buscarPor(Integer id) {
		TipoCardapio tipoCardapioEncontrado = repository.buscarPor(id);
		Preconditions.checkNotNull(tipoCardapioEncontrado, "Não foi encontrado o tipo do cardápio");
		Preconditions.checkArgument(tipoCardapioEncontrado.isAtivo(), "O tipo do cardápio está inativo");
		return tipoCardapioEncontrado;
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {
		TipoCardapio tipoCardapio = buscarPor(id);
		this.repository.atualizarStatusPor(id, status);
	}

}
