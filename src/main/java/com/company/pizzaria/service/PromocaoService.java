package com.company.pizzaria.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.company.pizzaria.model.entity.Promocao;
import com.company.pizzaria.repository.PromocaoRepository;

@Service
public class PromocaoService {

	private final PromocaoRepository repository;

	
	public PromocaoService(PromocaoRepository repository) {
		this.repository = repository;
	}

	public List<Promocao> listarAtivas() {
		return repository.findByStatus("ativa");
	}

	public List<Promocao> listarExpirandoEmBreve() {
		LocalDate hoje = LocalDate.now();
		LocalDate daqui3dias = hoje.plusDays(3);
		return repository.findByValidadeBetween(hoje, daqui3dias);
	}

	public Promocao salvar(Promocao promocao) {
		promocao.setUsos(0);
		promocao.setStatus("ativa");
		return repository.save(promocao);
	}

	public void invalidar(String codigo) {
		
		Promocao promocao = repository.findByCodigo(codigo).orElse(null);

		if (promocao != null) {
			promocao.setStatus("invalida");
			repository.save(promocao);
		}
	}
	
	public Promocao buscarPorCodigo(String codigo) {
	    return repository.findByCodigo(codigo).orElse(null); // ✅

	}
	
	public void excluir(String codigo) {
	    Promocao promocao = repository.findByCodigo(codigo).orElse(null);
	    if (promocao != null) {
	        repository.delete(promocao);
	    }
	}

}
