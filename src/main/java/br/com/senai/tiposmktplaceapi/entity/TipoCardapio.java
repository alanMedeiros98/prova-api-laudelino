package br.com.senai.tiposmktplaceapi.entity;

import br.com.senai.tiposmktplaceapi.entity.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "TipoCardapio")
@Table(name = "tipos_cardapios")
public class TipoCardapio {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Size(min = 3, max = 100, message = "O nome deve conter entre 3 e 100 caracteres")
	@NotBlank(message = "O nome é obrigatório")
	@Column(name = "nome")
	private String nome;
	
	@NotBlank(message = "A descrição é obrigatória")
	@Column(name = "descricao")
	private String descricao;
	
	@NotNull(message = "O status é obrigatório")
	@Enumerated(value = EnumType.STRING)
	@Column(name = "status")
	private Status status;
	
	@Transient
	public boolean isPersistido() {
		return getId() != null && getId() > 0;
	}
	
	@Transient
	public boolean isAtivo() {
		return getStatus() == Status.A;
	}
	
}
