package org.example.vo;

import java.time.LocalDate;

public class SalesReportVo {
    private String productName;
    private long quantity;
    private LocalDate lastSale;

    public SalesReportVo(String productName, long quantity, LocalDate lastSale) {
        this.productName = productName;
        this.quantity = quantity;
        this.lastSale = lastSale;
    }

    public String getProductName() {
        return productName;
    }

    public long getQuantity() {
        return quantity;
    }

    public LocalDate getLastSale() {
        return lastSale;
    }

    @Override
    public String toString() {
        return "SalesReportVo{" +
                "productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", lastSale=" + lastSale +
                '}';
    }
}
