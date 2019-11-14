package com.example.demo.stockmarkettype;

public class StockMarket {

    private String stockSymbol;

    private StockMarketType type;

    private int lastDividend;

    private int fixedDividend;

    private int parValue;

    public StockMarket(String symbol, StockMarketType type, int lastDividend,
                       int fixedDividend, int parValue) {

        this.stockSymbol = symbol;
        this.type = type;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public StockMarketType getType() {
        return type;
    }

    public void setType(StockMarketType type) {
        this.type = type;
    }

    public double getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(int lastDividend) {
        this.lastDividend = lastDividend;
    }

    public double getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(int fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    public int getParValue() {
        return parValue;
    }

    public void setParValue(int parValue) {
        this.parValue = parValue;
    }


}
