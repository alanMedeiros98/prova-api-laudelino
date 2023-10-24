package br.com.senai.tiposmktplaceapi.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.tiposmktplaceapi.entity.TipoCardapio;
import br.com.senai.tiposmktplaceapi.entity.enums.Status;
import br.com.senai.tiposmktplaceapi.service.TipoCardapioService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/tipos-cardapios")
//@RunWith(MockitoJUnitRunner.class)
public class TipoCardapioController {

	@Autowired
	@Qualifier("tipoCardapioServiceProxy")
	private TipoCardapioService service;
	
	//converte os dados para JSon
		private Map<String, Object> converter(TipoCardapio tipoCardapio){
			Map<String, Object> tipoCardapioMap = new HashMap<String, Object>();
			tipoCardapioMap.put("id", tipoCardapio.getId());
			tipoCardapioMap.put("nome", tipoCardapio.getNome());
			tipoCardapioMap.put("descricao", tipoCardapio.getDescricao());
			tipoCardapioMap.put("status", tipoCardapio.getStatus());
			return tipoCardapioMap;
		}
	
		@PostMapping
		public ResponseEntity<?> inserir(
				@RequestBody
				TipoCardapio tipoCardapio){
			TipoCardapio tipo = service.salvar(tipoCardapio);
			return ResponseEntity.created(URI.create("/tipo-cardapios/id" + tipo.getId())).build();
		}
		
		@Transactional
		@PatchMapping("/id/{id}/status/{status}")
		public ResponseEntity<?> atualizarStatusPor(
				@PathVariable("id")
				Integer id,
				@PathVariable("status")
				Status status){
			this.service.atualizarStatusPor(id, status);
			return ResponseEntity.ok().build();
		}
		
		@PutMapping
		public ResponseEntity<?> alterar(
				@RequestBody
				TipoCardapio tipoCardapioSalvo){
			TipoCardapio tipoCardapioAtualizado = service.salvar(tipoCardapioSalvo);
			return ResponseEntity.ok(converter(tipoCardapioAtualizado));
		}
		
		@GetMapping
		public ResponseEntity<?> listarPor(
				@RequestParam(name = "nome")
				String nome, 
				@RequestParam(name = "pagina")
				Optional<Integer> pagina){
			Pageable paginacao = null;
			if (pagina.isPresent()) {
				paginacao = PageRequest.of(pagina.get(), 20);
			} else {
				paginacao = PageRequest.of(0, 20);
			}
			Page<TipoCardapio> page = service.listarPor(nome, paginacao);
			Map<String, Object> pageMap = new HashMap<String, Object>();
			pageMap.put("paginaAtual", page.getNumber());
			pageMap.put("totalDeItens", page.getTotalElements());
			pageMap.put("totalDePaginas", page.getTotalPages());
			List<Map<String, Object>> listagem = new ArrayList<Map<String, Object>>();
			for (TipoCardapio tipoCardapio : page.getContent()) {
				listagem.add(converter(tipoCardapio));
			}
			pageMap.put("listagem", listagem);
			return ResponseEntity.ok(pageMap);
		}
		
		@GetMapping("/id/{id}")
		public ResponseEntity<?> buscarPor(
				@PathVariable("id")
				Integer id){
			TipoCardapio tipoCardapioEncontrado = service.buscarPor(id);
			return ResponseEntity.ok(converter(tipoCardapioEncontrado));
		}
		
		
}
