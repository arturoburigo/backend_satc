package com.example.tabelafipe.service;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.tabelafipe.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FipeService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getMarcaCodigo(String marca) {
        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
        Marca[] marcas = restTemplate.getForObject(url, Marca[].class);

        for (Marca m : marcas) {
            if (m.getNome().equalsIgnoreCase(marca)) {
                return m.getCodigo();
            }
        }
        return null;
    }

    public String getModeloCodigo(String marcaCodigo, String modelo) {
        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas/" + marcaCodigo + "/modelos";

        ModelosResponse modelosResponse = restTemplate.getForObject(url, ModelosResponse.class);

        for (Modelo m : modelosResponse.getModelos()) {
            if (m.getNome().equalsIgnoreCase(modelo)) {
                return m.getCodigo();
            }
        }
        return null;
    }

    public String getAnoCodigo(String marcaCodigo, String modeloCodigo, String ano) {
        // Aqui você faz a chamada para obter o código do ano do veículo
        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas/" + marcaCodigo + "/modelos/" + modeloCodigo + "/anos";
        System.out.println("URL de Anos: " + url);  // Print do URL de anos
        Ano[] anos = restTemplate.getForObject(url, Ano[].class);

        for (Ano a : anos) {
            if (a.getNome().contains(String.valueOf(ano))) {
                return a.getCodigo();
            }
        }
        return null;
    }

    public FipeValorResponse getValor(String marcaCodigo, String modeloCodigo, String anoCodigo) {
        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas/" + marcaCodigo + "/modelos/" + modeloCodigo + "/anos/" + anoCodigo;
        System.out.println("URL para obter o valor: " + url);

        try {
            // Obtém a resposta da API como ResponseEntity
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            System.out.println("Código de Status: " + responseEntity.getStatusCode());
            System.out.println("Resposta da API: " + responseEntity.getBody());

            // Usa ObjectMapper para mapear a String para o objeto FipeValorResponse
            ObjectMapper mapper = new ObjectMapper();
            FipeValorResponse fipeResponse = mapper.readValue(responseEntity.getBody(), FipeValorResponse.class);

            // Exibe o valor e o mês de referência
            System.out.println("Valor: " + fipeResponse.getValor());
            System.out.println("Mês de Referência: " + fipeResponse.getMesReferencia());

            return fipeResponse;
        } catch (Exception e) {
            System.out.println("Erro ao chamar a API: " + e.getMessage());
            return new FipeValorResponse();
        }
    }




}

