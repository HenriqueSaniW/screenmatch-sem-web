package br.com.alura.screenmatch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.services.ConsumoApi;
import br.com.alura.screenmatch.services.ConverteDados;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=f8c1f65b");
		System.out.println(json);
		// json = consumoApi.obterDados("http://coffee.alexflipnote.dev/random.json");
		// System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
		json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=f8c1f65b");
		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);

		List<DadosTemporada> temporadas = new ArrayList<>();
		
		
		for (int i = 1; i <= dados.totalTemporadas(); i++) {
		json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season="+i+"&apikey=f8c1f65b");
		DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
		temporadas.add(dadosTemporada);
		}

		temporadas.forEach(System.out::println);
	}

}
