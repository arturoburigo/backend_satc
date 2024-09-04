package org.example;

import java.util.ArrayList;

public class BasePorto {
    protected String nome;
    protected ArrayList<Barco> barcosAtracados;

    public BasePorto(String nome) {
        this.nome = nome;
        this.barcosAtracados = new ArrayList<>();
    }

    public void atracarBarco(Barco barco) {
        barcosAtracados.add(barco);
        System.out.println("Barco " + barco.getNome() + " atracado no porto " + nome + ".");
    }

    public void desatracarBarco(Barco barco) {
        if (barcosAtracados.remove(barco)) {
            System.out.println("Barco " + barco.getNome() + " desatracado do porto " + nome + ".");
        } else {
            System.out.println("Barco " + barco.getNome() + " não encontrado no porto " + nome + ".");
        }
    }
}
