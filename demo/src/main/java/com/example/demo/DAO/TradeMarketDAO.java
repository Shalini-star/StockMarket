package com.example.demo.DAO;

import com.example.demo.stockmarkettype.StockMarket;
import com.example.demo.stockmarkettype.TradeMarket;

import java.util.List;

public interface TradeMarketDAO {

        void addTrade(TradeMarket trade);
        List<TradeMarket> getTrades(StockMarket stock, int minutes);
        List<TradeMarket> getAllTrades();
}
