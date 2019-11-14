package com.example.demo.DAO;

import com.example.demo.stockmarkettype.StockMarket;

public interface StockMarketDAO {

    public void addStock(StockMarket stock);

    StockMarket getStock(String symbol);

}
