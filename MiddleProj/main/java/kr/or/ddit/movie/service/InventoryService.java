package kr.or.ddit.movie.service;

import java.util.List;

import kr.or.ddit.movie.Inventory; // Inventory 클래스 import
import kr.or.ddit.movie.dao.InventoryDao; // InventoryDao 클래스 import

public class InventoryService {
    private InventoryDao inventoryDao = new InventoryDao();

    public void addInventory(Inventory inventory) {
        inventoryDao.addInventory(inventory);
    }

    public void updateInventory(int id, Inventory inventory) {
        inventoryDao.updateInventory(id, inventory);
    }

    public void deleteInventory(int id) {
        inventoryDao.deleteInventory(id);
    }

    public List<Inventory> getAllInventories() {
        return inventoryDao.getAllInventories();
    }
}
