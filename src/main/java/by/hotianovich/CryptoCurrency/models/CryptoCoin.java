package by.hotianovich.CryptoCurrency.models;


import jakarta.persistence.*;

import javax.persistence.Column;

import java.util.Date;

@Entity
@Table(name = "coin")
public class CryptoCoin {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coin_seq")
    @SequenceGenerator(name = "coin_seq", sequenceName = "coin_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "id_coin")
    private int idCoin;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "price")
    private Double price;

    @Column(name = "update_date")
    private Date updateDate;

    public CryptoCoin(int idCoin, String symbol, Double price, Date updateDate) {
        this.id = 0;
        this.idCoin = idCoin;
        this.symbol = symbol;
        this.price = price;
        this.updateDate = updateDate;
    }

    public CryptoCoin() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCoin() {
        return idCoin;
    }

    public void setIdCoin(int idCoin) {
        this.idCoin = idCoin;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
