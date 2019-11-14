package com.example.demo.stockinterface;

import com.example.demo.stockmarkettype.StockMarket;
import com.example.demo.stockmarkettype.TradeMarket;

import java.util.List;

public interface StockClass {

    public  void addStock(StockMarket stock);

    public StockMarket getStock(String symbol);

    public double calculateDividendYield(StockMarket stock, double price);

    public double calculatePERatio(StockMarket stock, double price);

    public double calculateVolumeWeightedStockPrice(List<TradeMarket> trades);

    public double calculateGBCE(List<TradeMarket> trades);

}
