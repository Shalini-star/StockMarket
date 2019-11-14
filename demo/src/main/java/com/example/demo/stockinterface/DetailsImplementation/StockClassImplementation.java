package com.example.demo.stockinterface.DetailsImplementation;

import com.example.demo.DAO.DAOImplementation.MemStockDao;
import com.example.demo.DAO.StockMarketDAO;
import com.example.demo.stockinterface.StockClass;
import com.example.demo.stockmarkettype.StockMarket;
import com.example.demo.stockmarkettype.StockMarketType;
import com.example.demo.stockmarkettype.TradeMarket;

import java.util.List;

import static jdk.nashorn.internal.objects.NativeMath.round;

public class StockClassImplementation implements StockClass {

    private static StockClassImplementation instance = null;

    public static StockClassImplementation getInstance() {
        if (instance == null) {
            instance = new StockClassImplementation();
        }
        return instance;
    }
    private StockMarketDAO StockMarketDAO = new MemStockDao();
    public void addStock(StockMarket stock)
    {
        StockMarketDAO.addStock(stock);
    }

    @Override
    public StockMarket getStock(String symbol) {
        return StockMarketDAO.getStock(symbol);
    }



    @Override
    public double calculateDividendYield(StockMarket stock, double price) {
        if (StockMarketType.PREFERRED.equals(stock.getType())) {
            return (stock.getFixedDividend() * stock.getParValue()) / price;
        }
        double result = stock.getLastDividend() / price;
        return round(result, 2);
    }



    @Override
    public double calculatePERatio(StockMarket stock, double price) {
        double result = price / stock.getLastDividend();
        return round(result, 2);
    }

    @Override
    public double calculateVolumeWeightedStockPrice(List<TradeMarket> trades) {
        double sumOfPriceQuantity = 0;
        int sumOfQuantity = 0;

        for (TradeMarket trade : trades) {
            sumOfPriceQuantity = sumOfPriceQuantity + (trade.getPrice() * trade.getQuantity());
            sumOfQuantity = sumOfQuantity + trade.getQuantity();
        }
        double result = sumOfPriceQuantity / sumOfQuantity;
        return round(result, 2);
    }

    @Override
    public double calculateGBCE(List<TradeMarket> trades) {
        double total = 1;
        for (TradeMarket trade : trades) {
            total = total * trade.getPrice();
        }
        double result = Math.pow(total, (1D / trades.size()));
        return round(result, 2);
    }
}
