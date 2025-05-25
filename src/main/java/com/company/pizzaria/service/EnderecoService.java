package com.company.pizzaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.company.pizzaria.model.entity.Endereco;
import com.company.pizzaria.repository.EnderecoRepository;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> buscarPorId(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void excluir(Long id) {
        enderecoRepository.deleteById(id);
    }
}
