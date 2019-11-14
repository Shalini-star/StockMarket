package com.example.demo.stockmarkettype;

import java.util.Date;

public class TradeMarket<TradeType> implements Comparable<TradeMarket>
{
        private StockMarket stock;

        private Date timestamp;

        private int quantity;

        private TradeType type;

        private double price;

        public TradeMarket(StockMarket stock, Date timestamp, int quantity, TradeType type, double price) {
            super();
            this.stock = stock;
            this.timestamp = timestamp;
            this.quantity = quantity;
            this.type = type;
            this.price = price;
        }

        public StockMarket getStock() {
            return stock;
        }

        public void setStock(StockMarket stock) {
            this.stock = stock;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public TradeType getType() {
            return type;
        }

        public void setType(TradeType type) {
            this.type = type;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int compareTo(TradeMarket trade) {
            return trade.getTimestamp().compareTo(this.timestamp);
        }

    }
