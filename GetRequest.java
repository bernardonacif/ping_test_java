import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class GetRequest {

    private String responseBody;

    public GetRequest(String username, String password) {
        this.responseBody = fetchData(username, password);
    }

    // Método que faz a requisição GET e retorna a resposta como String
    private String fetchData(String username, String password) {
        try {
            // URL da API para requisição GET
            String urlGet = "https://api.exemplo.com/endpoint-get";

            // Codifica o usuário e a senha em base64
            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

            // Cria o cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Cria a requisição GET com o cabeçalho de autenticação
            HttpRequest requestGet = HttpRequest.newBuilder()
                .uri(URI.create(urlGet))
                .header("Authorization", "Basic " + encodedAuth)
                .build();

            // Envia a requisição GET e obtém a resposta
            HttpResponse<String> responseGet = client.send(requestGet, HttpResponse.BodyHandlers.ofString());
            System.out.println("Código de resposta GET: " + responseGet.statusCode());
            return responseGet.body();  // Retorna o corpo da resposta

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para acessar a resposta da requisição GET
    public String getResponseBody() {
        return responseBody;
    }
}
