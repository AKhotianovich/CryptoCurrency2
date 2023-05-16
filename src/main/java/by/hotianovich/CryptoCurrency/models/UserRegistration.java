package by.hotianovich.CryptoCurrency.models;


import jakarta.persistence.*;

@Entity
@Table(name = "userregistration")
public class UserRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "initialprice")
    private Double initialPrice;

    @Column(name = "symbol")
    private String symbol;

    public UserRegistration(String username, Double initialPrice, String symbol) {
        this.username = username;
        this.initialPrice = initialPrice;
        this.symbol = symbol;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
