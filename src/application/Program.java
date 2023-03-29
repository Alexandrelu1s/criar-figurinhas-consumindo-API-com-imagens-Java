package application;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Program {

	public static void main(String[] args) throws Exception {

		// fazer uma conexão HTTP e buscar os filmes mais populares

		// String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

		// String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";

		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";

		// String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";

		URI endereco = URI.create(url);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();

		// pegar/extrair só os dados que interessam (titulo, poster, classificacao)

		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);

		// exibir e manipular os dados

		for (Map<String, String> filme : listaDeFilmes) {

			System.out.println("\u001b[1mTitulo:\u001b[0m " + "\u001b[3m" + filme.get("title") + "\u001b[0m");
            System.out.println("\u001b[1mURL imagem:\u001b[0m " + "\u001b[3m" + filme.get("image") + "\u001b[0m");
            System.out.print("\u001b[1mClassificação:\u001b[0m ");
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numEstrelinhas = (int) classificacao;
            for(int n = 1; n <= numEstrelinhas; n++) {
            	System.out.print("\u001b[33m★\u001b[0m");
            }
            System.out.println("\n");

		}

	}

}
