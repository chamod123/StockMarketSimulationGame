package Old.Model1;

import java.math.BigDecimal;

public class Transaction {
    static int number = 0;
    int id;
    String name;
    int turn;
    String type;
    String stock;
    int quantity;
    BigDecimal total;
    BigDecimal amount;

    public Transaction(String name, int turn, String type, String stock, int quantity, BigDecimal total) {
        super();
        this.id = number;
        number++;
        this.name = name;
        this.turn = turn;
        this.type = type;
        this.stock = stock;
        this.quantity = quantity;
        this.total = total;
    }

    public Transaction(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        Transaction.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
