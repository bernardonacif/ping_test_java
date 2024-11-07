import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.Base64;

import com.google.gson.Gson;

public class PostRequest {

    public static void main(String[] args) {
        // Dados de autenticação
        String username = "seu_usuario";
        String password = "sua_senha";

        // Arquivo JSON para o corpo da requisição POST
        File jsonFile = new File("body/file.json");

        // Faz a requisição POST com o JSON no corpo
        sendPostRequest(username, password, jsonFile);
    }

    public static void sendPostRequest(String username, String password, File jsonFile) {
        try {
            // Lê o conteúdo do arquivo JSON
            String jsonBody = new String(Files.readAllBytes(jsonFile.toPath()));

            // Adiciona o prefixo "046:" ao nome de usuário e codifica em Base64
            String auth = "046:" + username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

            // Cria o cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // URL da API para requisição POST
            String urlPost = "https://api.exemplo.com/endpoint-post";

            // Cria a requisição POST com o corpo JSON e cabeçalho de autenticação
            HttpRequest requestPost = HttpRequest.newBuilder()
                .uri(URI.create(urlPost))
                .header("Authorization", "Basic " + encodedAuth)  // Cabeçalho de autenticação
                .header("Content-Type", "application/json")       // Tipo de conteúdo JSON
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))  // Envia o corpo JSON
                .build();

            // Envia a requisição POST e obtém a resposta
            HttpResponse<String> responsePost = client.send(requestPost, HttpResponse.BodyHandlers.ofString());

            // Exibe o código de status e o corpo da resposta
            System.out.println("Código de resposta POST: " + responsePost.statusCode());
            System.out.println("Resposta POST: " + responsePost.body());

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo JSON: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
