package br.com.senai.tiposmktplaceapi.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senai.tiposmktplaceapi.entity.TipoCardapio;
import br.com.senai.tiposmktplaceapi.entity.enums.Status;
import br.com.senai.tiposmktplaceapi.service.TipoCardapioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class TipoCardapioServiceProxy implements TipoCardapioService {

	@Autowired
	@Qualifier("tipoCardapioServiceImpl")
	private TipoCardapioService service;
	
	@Override
	public TipoCardapio salvar(TipoCardapio tipoCardapio) {
		return service.salvar(tipoCardapio);
	}

	@Override
	public Page<TipoCardapio> listarPor(String nome, Pageable paginacao) {
		return service.listarPor(nome, paginacao);
	}

	@Override
	public TipoCardapio buscarPor(Integer id) {
		return service.buscarPor(id);
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {
		this.service.atualizarStatusPor(id, status);

	}

}
