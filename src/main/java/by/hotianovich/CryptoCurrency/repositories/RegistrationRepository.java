package by.hotianovich.CryptoCurrency.repositories;


import by.hotianovich.CryptoCurrency.models.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<UserRegistration, Long> {
    List<UserRegistration> findBySymbol(String symbol);
}
