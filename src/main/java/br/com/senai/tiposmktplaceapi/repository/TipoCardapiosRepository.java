package br.com.senai.tiposmktplaceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.tiposmktplaceapi.entity.TipoCardapio;
import br.com.senai.tiposmktplaceapi.entity.enums.Status;

@Repository
public interface TipoCardapiosRepository extends JpaRepository<TipoCardapio, Integer> {

	@Query(value = "SELECT tc "
				 + "FROM TipoCardapio tc "
				 + "WHERE tc.nome = :nome "
				 + "ORDER BY tc.nome DESC ",
				 countQuery = "SELECT Count(tc) "
						 	+ "FROM TipoCardapio tc "
						 	+ "WHERE tc.nome = :nome ")
	public Page<TipoCardapio> listarPor(String nome, Pageable paginacao);
	
	@Query(value = "SELECT tc "
				 + "FROM TipoCardapio tc "
				 + "WHERE tc.id = :id ")
	public TipoCardapio buscarPor(Integer id);
	
	@Modifying
	@Query(value = "UPDATE TipoCardapio tc "
				 + "SET tc.status = :status "
				 + "WHERE tc.id = :id")
	public void atualizarStatusPor(Integer id, Status status);
	
}
