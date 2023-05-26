package com.financeiro.patrimonio.controller;

import com.financeiro.patrimonio.model.Patrimonio;
import com.financeiro.patrimonio.repository.PatrimonioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/patrimonio")
@CrossOrigin(origins = "http://localhost:4200")
public class PatrimonioController {


    private PatrimonioRepository repository;


    @Autowired
    public PatrimonioController(PatrimonioRepository patrimonioRepository) {
        this.repository = patrimonioRepository;
    }


    @GetMapping
    public List<Patrimonio> obterTodos(){
        return repository.findAllByOrderByAnoAscMesAsc();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Patrimonio salvarPatrimonio(@RequestBody @Valid Patrimonio patrimonio) {
        return repository.save(patrimonio);
    }

    @GetMapping("/{id}")
    public Patrimonio buscarPorId(@PathVariable Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patrimônio não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        repository
                .findById(id)
                .map(patrimonio -> {
                    repository.delete(patrimonio);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patrimônio não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Patrimonio patrimonioAtualizado) {
        repository
                .findById(id)
                .map(patrimonio -> {
                    patrimonio.setValor(patrimonioAtualizado.getValor());
                    patrimonio.setMes(patrimonioAtualizado.getMes());
                    patrimonio.setAno(patrimonioAtualizado.getAno());
                    return repository.save(patrimonio);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patrimonio não encontrado"));
    }


}
