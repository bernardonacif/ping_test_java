public class Main {

    public static void main(String[] args) {
        // Dados de autenticação
        String username = "seu_usuario";
        String password = "sua_senha";

        // Faz a requisição GET e obtém a resposta
        GetRequest getRequest = new GetRequest(username, password);
        String responseData = getRequest.getResponseBody();

        if (responseData != null) {
            // Agora, faz a requisição POST com a resposta obtida do GET
            PostRequest postRequest = new PostRequest();
            postRequest.sendPostRequest(responseData);
        } else {
            System.out.println("Erro ao obter resposta da requisição GET.");
        }
    }
}
