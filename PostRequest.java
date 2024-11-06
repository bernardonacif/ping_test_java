import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class PostRequest {

    public void sendPostRequest(String responseData) {
        try {
            // URL da API para requisição POST
            String urlPost = "https://api.exemplo.com/endpoint-post";

            // Prepara os dados para o corpo da requisição POST
            Map<String, String> data = new HashMap<>();
            data.put("campo1", "valor1");
            data.put("campo2", "valor2");
            data.put("campoExtra", responseData);  // Usando a resposta da requisição GET

            // Codifica os dados no formato x-www-form-urlencoded
            StringJoiner sj = new StringJoiner("&");
            for (Map.Entry<String, String> entry : data.entrySet()) {
                sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            String formData = sj.toString();

            // Cria o cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Cria a requisição POST com o corpo de dados
            HttpRequest requestPost = HttpRequest.newBuilder()
                .uri(URI.create(urlPost))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formData))  // Envia os dados no corpo
                .build();

            // Envia a requisição POST e obtém a resposta
            HttpResponse<String> responsePost = client.send(requestPost, HttpResponse.BodyHandlers.ofString());
            System.out.println("Código de resposta POST: " + responsePost.statusCode());
            System.out.println("Resposta POST: " + responsePost.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
