package com.example.banco_dados.Controler;


import com.example.banco_dados.Model.Pessoa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

    private List<Pessoa> pessoas = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong();

    //Listar pessoas
    @GetMapping
    public String listarPessoas(Model model) {
        model.addAttribute("pessoas", pessoas);
        return "Listar-pessoas";
    }

    //Adicionar pessoas
    @GetMapping("/novo")
    public String novoPessoa(Model model) {
        model.addAttribute("pessoas", new Pessoa());
        return "form-pessoa";
    }

    //Salvar pessoas
    @PostMapping
    public String salvarPessoa(@ModelAttribute Pessoa pessoa) {
        if(pessoa.getId() == null){
            pessoa.setId(idCounter.getAndIncrement());
            pessoas.add(pessoa);
        }
        else {
            pessoas.replaceAll(p -> p.getId().equals(pessoa.getId()) ? pessoa : p);
        }
        return "redirect:/pessoas";
    }

    // Editar
    @GetMapping( "/editar/{id}")
    public String editarPessoa(@PathVariable long id, Model model) {
        Pessoa pessoa = pessoas.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Pessoa Inválida: " + id));
        model.addAttribute("pessoa", pessoa);
        return "form-pessoa";
    }

    // Deletar
    @GetMapping("/deletar/{id}")
    public String deletarPessoa(@PathVariable long id) {
        pessoas.removeIf(p -> p.getId().equals(id));
        return "redirect:/pessoas";
    }
}
