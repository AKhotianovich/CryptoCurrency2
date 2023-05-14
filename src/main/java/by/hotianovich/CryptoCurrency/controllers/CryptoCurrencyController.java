package by.hotianovich.CryptoCurrency.controllers;

import by.hotianovich.CryptoCurrency.models.CryptoCoin;
import by.hotianovich.CryptoCurrency.repositories.CryptoCoinRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;

@RestController
@RequestMapping("/coin")
public class CryptoCurrencyController {

    @Autowired
    private CryptoCoinRepository coinRepository;

    @GetMapping()
    @Scheduled(fixedRate = 6000) // запускаем метод каждую минуту
    public void updateCoinTicker() throws IOException, InterruptedException {
        String url = "https://api.coinlore.net/api/ticker/?id=90";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        System.out.println(ResponseEntity.ok(responseBody));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);
        System.out.println("JOBfdfdfdffffffffffffffffff");
        int id = rootNode.get(0).get("id").asInt();
        String symbol = rootNode.get(0).get("symbol").asText();
        Double price = rootNode.get(0).get("price_usd").asDouble();
        System.out.println("TEST VALUE " + symbol);
        CryptoCoin coin = new CryptoCoin(id, symbol, price, new Date());
        coinRepository.save(coin);
    }
}
