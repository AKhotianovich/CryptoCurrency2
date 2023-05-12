package by.hotianovich.CryptoCurrency.repositories;

import by.hotianovich.CryptoCurrency.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
