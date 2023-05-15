package by.hotianovich.CryptoCurrency.repositories;

import by.hotianovich.CryptoCurrency.models.CryptoCoin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Properties;

public interface CryptoCoinRepository extends JpaRepository<CryptoCoin, Integer> {
    CryptoCoin findBySymbol(String symbol);
}
