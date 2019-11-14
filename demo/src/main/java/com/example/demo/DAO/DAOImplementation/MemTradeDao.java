package com.example.demo.DAO.DAOImplementation;

import com.example.demo.DAO.TradeMarketDAO;
import com.example.demo.stockmarkettype.StockMarket;
import com.example.demo.stockmarkettype.TradeMarket;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

    public class MemTradeDao implements TradeMarketDAO {

        private Map<String, List<TradeMarket>> tradeMap = new HashMap<String, List<TradeMarket>>(); // Performance


        public void addTrade(TradeMarket trade) {
            List<TradeMarket> trades = new ArrayList<TradeMarket>();
            if (tradeMap.containsKey(trade.getStock().getStockSymbol())) {
                trades = tradeMap.get(trade.getStock().getStockSymbol());
            }
            trades.add(trade);
            tradeMap.put(trade.getStock().getStockSymbol(), trades);
        }

        public List<TradeMarket> getTrades(StockMarket stock, int minutes) {
            List<TradeMarket> result = new ArrayList<TradeMarket>();
            Date afterDate = getDateXMinutesEarlier(minutes);
            List<TradeMarket> trades = tradeMap.get(stock.getStockSymbol());
            Collections.sort(trades); // Order by latest trade first.
            Iterator<TradeMarket> it = trades.iterator();
            while (it.hasNext()) {
                TradeMarket trade = it.next();
                if (trade.getTimestamp().before(afterDate)) { // Trades are in order. Break for performance.
                    break;
                }
                result.add(trade);
            }
            return result;
        }

        public List<TradeMarket> getAllTrades() {
            List<TradeMarket> result = new ArrayList<TradeMarket>();
            for (String stockSymbol: tradeMap.keySet()) {
                result.addAll(tradeMap.get(stockSymbol));
            }
            return result;
        }

        private Date getDateXMinutesEarlier(int minutes){
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MINUTE, -minutes);
            return c.getTime();
        }

    }




