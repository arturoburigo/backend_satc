package com.example.tabelafipe.service;

import com.example.tabelafipe.models.FipeValorResponse;
import org.springframework.stereotype.Service;

@Service
public class FipeService {
    public String getMarcaCodigo(String marca) {
        // Lógica para obter o código da marca
        return "codigoMarca";
    }

    public String getModeloCodigo(String marcaCodigo, String modelo) {
        // Lógica para obter o código do modelo
        return "codigoModelo";
    }

    public String getAnoCodigo(String marcaCodigo, String modeloCodigo, int ano) {
        // Lógica para obter o código do ano
        return "codigoAno";
    }

    public FipeValorResponse getValor(String marcaCodigo, String modeloCodigo, String anoCodigo) {
        // Lógica para obter o valor do veículo
        FipeValorResponse response = new FipeValorResponse();
        response.setValor(50000.0);
        response.setMes("Setembro de 2024");
        return response;
    }
}
