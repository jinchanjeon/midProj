package kr.or.ddit.movie.main;
public class Inventory {
    private int id;
    private String itemName;
    private int quantity;

    public Inventory(int id, String itemName, int quantity) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
}
