package com.example.demo.stockinterface.DetailsImplementation;

import com.example.demo.DAO.DAOImplementation.MemTradeDao;
import com.example.demo.DAO.TradeMarketDAO;
import com.example.demo.stockinterface.TradeClass;
import com.example.demo.stockmarkettype.StockMarket;
import com.example.demo.stockmarkettype.TradeMarket;

import java.util.List;

public class TradeClassImplementation  implements TradeClass
     {
         private static TradeClassImplementation instance = null;

         public static TradeClassImplementation getInstance() {
         if (instance == null) {
             instance = new TradeClassImplementation();
         }
         return instance;
     }
     private TradeMarketDAO tradeDao = new MemTradeDao();

        public void recordTrade(TradeMarket trade) {
            if (trade != null && trade.getStock() != null) {
                tradeDao.addTrade(trade);
            }
        }


        public List<TradeMarket> getTrades(StockMarket stock, int minutes) {
            return tradeDao.getTrades(stock, minutes);
        }


        public List<TradeMarket> getAllTrades() {
            return tradeDao.getAllTrades();
        }

    }

