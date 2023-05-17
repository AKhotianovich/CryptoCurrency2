package by.hotianovich.CryptoCurrency.services;

import by.hotianovich.CryptoCurrency.models.CryptoCoin;
import by.hotianovich.CryptoCurrency.models.Person;
import by.hotianovich.CryptoCurrency.repositories.CryptoCoinRepository;
import by.hotianovich.CryptoCurrency.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class CoinService {

    private final CryptoCoinRepository cryptoCoinRepository;

    @Autowired
    public CoinService(CryptoCoinRepository cryptoCoinRepository) {
        this.cryptoCoinRepository = cryptoCoinRepository;
    }

    public List<CryptoCoin> findAll() {
        return cryptoCoinRepository.findAll();
    }

    public CryptoCoin findOne(String symbol) {
        Optional<CryptoCoin> foundCoin = Optional.ofNullable(cryptoCoinRepository.findFirstBySymbolOrderByUpdateDateDesc(symbol));
        return foundCoin.orElse(null);
    }
}
