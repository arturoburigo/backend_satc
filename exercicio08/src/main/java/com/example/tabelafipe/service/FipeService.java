package com.example.tabelafipe;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FipeService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getMarcaCodigo(String marca) {
        // Aqui você faz uma chamada à API Fipe para obter o código da marca baseado no nome
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
        // Aqui você faz a chamada para obter o código do modelo da marca
        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas/" + marcaCodigo + "/modelos";
        ModelosResponse modelosResponse = restTemplate.getForObject(url, ModelosResponse.class);

        for (Modelo m : modelosResponse.getModelos()) {
            if (m.getNome().equalsIgnoreCase(modelo)) {
                return m.getCodigo();
            }
        }
        return null;
    }

    public String getAnoCodigo(String marcaCodigo, String modeloCodigo, int ano) {
        // Aqui você faz a chamada para obter o código do ano do veículo
        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas/" + marcaCodigo + "/modelos/" + modeloCodigo + "/anos";
        Ano[] anos = restTemplate.getForObject(url, Ano[].class);

        for (Ano a : anos) {
            if (a.getNome().contains(String.valueOf(ano))) {
                return a.getCodigo();
            }
        }
        return null;
    }

    public FipeValorResponse getValor(String marcaCodigo, String modeloCodigo, String anoCodigo) {
        // Aqui você faz a chamada para obter o valor do veículo
        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas/" + marcaCodigo + "/modelos/" + modeloCodigo + "/anos/" + anoCodigo;
        FipeResponse fipeResponse = restTemplate.getForObject(url, FipeResponse.class);

        FipeValorResponse response = new FipeValorResponse();
        response.setValor(Double.parseDouble(fipeResponse.getValor().replace("R$", "").replace(".", "").replace(",", ".")));
        response.setMes(fipeResponse.getMesReferencia());

        return response;
    }
}
