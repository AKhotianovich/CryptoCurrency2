package by.hotianovich.CryptoCurrency.repositories;

import by.hotianovich.CryptoCurrency.models.CryptoCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Properties;

public interface CryptoCoinRepository extends JpaRepository<CryptoCoin, Integer> {

    //@Query("SELECT c FROM CryptoCoin c WHERE c.symbol = :symbol ORDER BY c.id DESC")
    CryptoCoin findFirstBySymbolOrderByUpdateDateDesc(String symbol);

}
