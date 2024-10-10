package kr.or.ddit.movie.service;
import java.util.HashMap;
import java.util.Map;

public class SalesService {
    private Map<String, Double> salesData = new HashMap<>();

    public void addSale(String date, double amount) {
        salesData.put(date, amount);
    }

    public Map<String, Double> getSalesData() {
        return salesData;
    }
}
