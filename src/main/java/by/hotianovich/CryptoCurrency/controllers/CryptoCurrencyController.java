package by.hotianovich.CryptoCurrency.controllers;

import by.hotianovich.CryptoCurrency.models.CryptoCoin;
import by.hotianovich.CryptoCurrency.models.RegistrationRequest;
import by.hotianovich.CryptoCurrency.models.UserRegistration;
import by.hotianovich.CryptoCurrency.repositories.CryptoCoinRepository;
import by.hotianovich.CryptoCurrency.repositories.RegistrationRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/coin")
public class CryptoCurrencyController {

    @Autowired
    private CryptoCoinRepository coinRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    private static final Logger logger = LoggerFactory.getLogger(CryptoCurrencyController.class);

    @GetMapping()
    public String home() {
        return "Hello, World!";
    }

    @GetMapping("/cryptocurrencies")
    public List<CryptoCoin> getAllCryptoCurrencies() {
        return this.coinRepository.findAll();
    }

    @GetMapping("/cryptocurrencies/{symbol}/price")
    public Double getCoinPrice(@PathVariable String symbol) {
        CryptoCoin coin = this.coinRepository.findFirstBySymbolOrderByUpdateDateDesc(symbol);
        if (coin != null) {
            return coin.getPrice();
        } else {
            // Обработка случая, когда указанная криптовалюта не найдена
            return 0.00;
        }
    }


    @GetMapping("/notify")
    public String registerForPriceChange(@RequestParam("username") String username, @RequestParam("symbol") String symbol) {
        CryptoCoin coin = coinRepository.findFirstBySymbolOrderByUpdateDateDesc(symbol);
        if (coin != null) {
            // Сохраняем информацию о регистрации пользователя
            UserRegistration registrationUser = new UserRegistration(username, coin.getPrice(), coin.getSymbol());
            registrationRepository.save(registrationUser);
            return "UserRegistration successful";
        } else {
            // Обработка случая, когда указанная криптовалюта не найдена
            return "Invalid cryptocurrency symbol";
        }
    }

    @Scheduled(fixedRate = 60000) // запускаем метод каждую минуту
    public void updateCoinTicker() throws IOException, InterruptedException {
        String url = "https://api.coinlore.net/api/ticker/?id=90,80,48543";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);
        logger.warn("Price change alert - URL: {}, ", url);
        for (int i = 0; i < 3; i++) {
            int id = rootNode.get(i).get("id").asInt();
            String symbol = rootNode.get(i).get("symbol").asText();
            Double price = rootNode.get(i).get("price_usd").asDouble();
            CryptoCoin coin = new CryptoCoin(id, symbol, price, new Date());
            coinRepository.saveAndFlush(coin);

            List<UserRegistration> registrations = registrationRepository.findBySymbol(symbol);
            for (UserRegistration registration : registrations) {
                if (Math.abs(price - registration.getInitialPrice()) / registration.getInitialPrice() > 0.01) {
                    String username = registration.getUsername();
                    Double initialPrice = registration.getInitialPrice();
                    Double percentChange = (price - initialPrice) / initialPrice * 100;
                    logger.warn("Price change alert - Symbol: {}, User: {}, Initial Price: {}, Percent change: {}", symbol, username, initialPrice, percentChange);
                }

            }

        }

    }
}
