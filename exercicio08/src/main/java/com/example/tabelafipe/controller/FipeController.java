package com.example.tabelafipe.controller;

import com.example.tabelafipe.models.CarroRequest;
import com.example.tabelafipe.models.FipeValorResponse;
import com.example.tabelafipe.service.FipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FipeController {

    private final FipeService fipeService;

    @Autowired
    public FipeController(FipeService fipeService) {
        this.fipeService = fipeService;
    }

    @PostMapping("/valor")
    public FipeValorResponse getFipeValor(@RequestBody CarroRequest request) {
        String marcaCodigo = fipeService.getMarcaCodigo(request.getMarca());
        if (marcaCodigo == null) {
            throw new IllegalArgumentException("Marca não encontrada.");
        }

        String modeloCodigo = fipeService.getModeloCodigo(marcaCodigo, request.getModelo());
        if (modeloCodigo == null) {
            throw new IllegalArgumentException("Modelo não encontrado.");
        }

        String anoCodigo = fipeService.getAnoCodigo(marcaCodigo, modeloCodigo, request.getAno());
        if (anoCodigo == null) {
            throw new IllegalArgumentException("Ano não encontrado.");
        }

        return fipeService.getValor(marcaCodigo, modeloCodigo, anoCodigo);
    }
}
