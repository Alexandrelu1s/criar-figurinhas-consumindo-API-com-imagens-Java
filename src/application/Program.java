package application;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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

		// String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";

		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";

		URI endereco = URI.create(url);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();

		// pegar/extrair só os dados que interessam (titulo, poster, classificacao)

		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);

		// exibir e manipular os dados

		var geradora = new GeradoraDeFigurinhas();
		var diretorio = new File("figurinhas/");
    	diretorio.mkdir();
		
		
		for (Map<String, String> filme : listaDeFilmes) {
			
        	String urlImagem = filme.get("image");
        	String titulo = filme.get("title");
        	InputStream inputStream = new URL(urlImagem).openStream();
        	String nomeArquivo = "figurinhas/" + titulo + ".png";

        	geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);

            System.out.println("\n");
        }

	}

}
