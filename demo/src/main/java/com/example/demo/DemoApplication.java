package com.example.demo;

import com.example.demo.stockinterface.DetailsImplementation.StockClassImplementation;
import com.example.demo.stockinterface.DetailsImplementation.TradeClassImplementation;
import com.example.demo.stockinterface.StockClass;
import com.example.demo.stockinterface.TradeClass;
import com.example.demo.stockmarkettype.StockMarket;
import com.example.demo.stockmarkettype.StockMarketType;
import com.example.demo.stockmarkettype.TradeMarket;
import com.example.demo.stockmarkettype.TradeMarketType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.lang.String;

@SpringBootApplication
public class DemoApplication {

    private static StockClass stockClass = StockClassImplementation.getInstance();
    private static TradeClass tradeClass = TradeClassImplementation.getInstance();
     private static Scanner scanner;

    public static void main(String[] args) {
        getStockandReadStocks();
        printMenu();
        Scanner scanner = new Scanner(System.in);
        String choice = null;
        while (true) {
            choice = scanner.nextLine();
            if ("q".equals(choice)) {
                scanner.close();
                System.exit(0);
            } else {
                    int option = Integer.parseInt(choice);
                    StockMarket stockFromUser;
                    double priceFromUser;

                    switch (option) {
                        case 1:
                            stockFromUser = getStockFromUser();
                            priceFromUser = getStockPriceFromUser();
                            calculateDividendYield(stockFromUser, priceFromUser);
                            break;
                        case 2:
                            stockFromUser = getStockFromUser();
                            priceFromUser = getStockPriceFromUser();
                            calculatePERatio(stockFromUser, priceFromUser);
                            break;
                        case 3:
                            stockFromUser = getStockFromUser();
                            int quantityFromUser = getQuantityFromUser();
                            TradeMarketType tradeTypeFromUser = getTradeType();
                            priceFromUser = getStockPriceFromUser();
                            recordTrade(stockFromUser, quantityFromUser, tradeTypeFromUser, priceFromUser);
                            break;
                        case 4:
                            stockFromUser = getStockFromUser();
                            calculateVolumeWeightedStockPrice(stockFromUser);
                            break;
                        case 5:
                            calculateGBCE();
                            break;
                        default:
                            break;
                    }
            }
                System.out.println("");
                printMenu();
            }
        }

            private static StockMarket getStockFromUser () throws NullPointerException {
            System.out.println("Please input stock symbol");
            String stockSymbol = scanner.nextLine();
            StockMarket stock = stockClass.getStock(stockSymbol);
            if (stock == null) {
                throw new NullPointerException("Stock not found");
            }
            return stock;
        }

        private static double getStockPriceFromUser () throws NullPointerException{
            System.out.println("Please input stock price");
            String stockPrice = scanner.nextLine();
            try {
                double result = Double.parseDouble(stockPrice);
                if (result <= 0) {
                    throw new NullPointerException("Invalid price: Must be greated than 0");
                }
                return result;
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid price: Not a number");
            }
        }

        private static TradeMarketType getTradeType () throws IllegalArgumentException {
            System.out.println("Please input trade type (BUY/SELL)");
            String type = scanner.nextLine();
            try {
                return TradeMarketType.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid trade type: Must be BUY or SELL");
            }
        }

        private static int getQuantityFromUser () throws NullPointerException {
            System.out.println("Please input quantity");
            String quantity = scanner.nextLine();
            try {
                int result = Integer.parseInt(quantity);
                if (result <= 0) {
                    throw new NullPointerException("Invalid quantity: Must be greated than 0");
                }
                return result;
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid quantity: Not a number");
            }
        }

        private static void printMenu () {
            System.out.println("JPMorgan - Super simple stock market");
            System.out.println("1: Calculate dividend yield for stock");
            System.out.println("2: Calculate P/E ratio for stock");
            System.out.println("3: Record a trade for stock");
            System.out.println("4: Calculate Volume Weighted Stock Price for stock");
            System.out.println("5: Calculate GBCE All Share Index");
            System.out.println("q: Quit");
        }

        private static void calculateDividendYield (StockMarket stock,double price){
            double result = stockClass.calculateDividendYield(stock, price);
            System.out.println("Dividend Yield: " + result);
        }

        private static void calculatePERatio (StockMarket stock,double price){
            double result = stockClass.calculatePERatio(stock, price);
            System.out.println("PE Ratio: " + result);
        }

        private static void calculateVolumeWeightedStockPrice (StockMarket stock){
            List<TradeMarket> trades = tradeClass.getTrades(stock, 15);
            if (trades == null || trades.isEmpty()) {
                System.out.println("Volume Weighted Stock Price: No trades");
            } else {
                double result = stockClass.calculateVolumeWeightedStockPrice(trades);
                System.out.println("Volume Weighted Stock Price: " + result);
            }
        }

        private static void recordTrade (StockMarket stock,int quantity, TradeMarketType type,double price){
            //tradeService.recordTrade(new Trade(stock, Calendar.getInstance().getTime(),
                  //  quantity, type, price));
            tradeClass.recordTrade(new TradeMarket(stock, Calendar.getInstance().getTime(),
                    quantity, type, price));
            System.out.println("Trade recorded");
        }

        private static void calculateGBCE () {
            List<TradeMarket> allTrades = tradeClass.getAllTrades();
            if (allTrades == null || allTrades.isEmpty()) {
                System.out.println("Unable to calculate GBCE: No trades");
            } else {
                System.out.println("GBCE: " + stockClass.calculateGBCE(allTrades));
            }
        }

        private static void getStockandReadStocks () {
            stockClass.addStock(new StockMarket("TEA", StockMarketType.COMMON, 0, 0, 100));
            stockClass.addStock(new StockMarket("POP", StockMarketType.COMMON, 8, 0, 100));
            stockClass.addStock(new StockMarket("ALE", StockMarketType.COMMON, 23, 0, 60));
            stockClass.addStock(new StockMarket("GIN", StockMarketType.PREFERRED, 8, 2, 100));
            stockClass.addStock(new StockMarket("JOE", StockMarketType.PREFERRED, 13, 0, 250));
        }

    }
