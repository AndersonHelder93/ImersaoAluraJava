import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[45m";

    public static void main(String[] args) throws Exception {

        // realizar uma conexão http e buscar o ranking dos filmes
        String url = "https://gist.githubusercontent.com/lucasfugisawa/cbb0d10ee3901bd0541468e431c629b3/raw/1fe1f3340dfe5b5876a209c0e8226d090f6aef10/Top250Movies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // coletar só os dados que importam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println("\nTitle: " + filme.get("title"));
            System.out.println("Image: " + filme.get("image"));
            var movieRate = Double.parseDouble(filme.get("imDbRating"));
            String stars = " ";

            switch ((int) movieRate) {
                case 10:
                    stars += "⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐";
                    break;
                case 9:
                    stars += "⭐⭐⭐⭐⭐⭐⭐⭐⭐";
                    break;
                case 8:
                    stars += "⭐⭐⭐⭐⭐⭐⭐⭐";
                    break;
                case 7:
                    stars += "⭐⭐⭐⭐⭐⭐⭐";
                    break;
                case 6:
                    stars += "⭐⭐⭐⭐⭐⭐";
                    break;
                case 5:
                    stars += "⭐⭐⭐⭐⭐";
                    break;
                case 4:
                    stars += "⭐⭐⭐⭐";
                    break;
                case 3:
                    stars += "⭐⭐⭐";
                    break;
                case 2:
                    stars += "⭐⭐";
                    break;
                case 1:
                    stars += "⭐";
                    break;
            }
            System.out.println(ANSI_PURPLE + "Rating: " + (int) movieRate + stars + " " + ANSI_RESET);
        }

    }

}
