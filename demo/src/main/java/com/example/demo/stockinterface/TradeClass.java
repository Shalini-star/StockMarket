package com.example.demo.stockinterface;

import com.example.demo.stockmarkettype.StockMarket;
import com.example.demo.stockmarkettype.TradeMarket;

import java.util.List;

public interface TradeClass {

    public void recordTrade(TradeMarket trade);

    public List<TradeMarket> getTrades(StockMarket stock, int numberOfMinutes);

    public List<TradeMarket> getAllTrades();


}
