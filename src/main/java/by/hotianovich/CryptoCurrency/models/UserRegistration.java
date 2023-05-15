package by.hotianovich.CryptoCurrency.models;


import jakarta.persistence.*;

@Entity
@Table(name = "UserRegistration")
public class UserRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "initialPrice")
    private Double initialPrice;

    public UserRegistration(String username, Double initialPrice) {
        this.username = username;
        this.initialPrice = initialPrice;
    }

    public UserRegistration() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Double initialPrice) {
        this.initialPrice = initialPrice;
    }
}
