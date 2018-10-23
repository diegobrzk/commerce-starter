package br.com.dss.commerce.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class Cart implements Serializable {

    private String code;
    private BigDecimal total;
    private List<Entry> entries;

    public Cart() {
    }

    public Cart(String code, BigDecimal total, List<Entry> entries) {
        this.code = code;
        this.total = total;
        this.entries = entries;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
