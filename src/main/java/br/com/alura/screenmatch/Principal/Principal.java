package br.com.alura.screenmatch.Principal;

import java.util.Scanner;

import br.com.alura.screenmatch.services.ConsumoApi;


public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY= "&apikey=f8c1f65b";

    public void exibeMenu(){
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
		var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        //https://www.omdbapi.com/?t=gilmore+girls&season="+i+"&apikey=f8c1f65b
    }
}
