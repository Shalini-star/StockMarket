package com.example.demo.DAO.DAOImplementation;

import com.example.demo.DAO.StockMarketDAO;
import com.example.demo.stockmarkettype.StockMarket;

import java.util.Map;
import java.util.HashMap;

public class MemStockDao implements StockMarketDAO {

    private Map<String, StockMarket> stockMap = new HashMap<String, StockMarket>();

    public void addStock(StockMarket stock) {
        stockMap.put(stock.getStockSymbol(), stock);
    }

    public StockMarket getStock(String symbol) {
        return stockMap.get(symbol);
    }
}
