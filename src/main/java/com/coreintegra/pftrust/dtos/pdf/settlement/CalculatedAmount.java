package com.coreintegra.pftrust.dtos.pdf.settlement;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CalculatedAmount {

    private BigDecimal amount;
    private Integer month;


    public CalculatedAmount() {
    }

    public CalculatedAmount(BigDecimal amount, Integer month) {
        this.amount = amount;
        this.month = month;
    }

    @XmlElement(name = "amount")
    public String getAmount() {
        return formateNumbers(amount);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal bigDecimal(){

        if (amount == null){
            return new BigDecimal(0);
        }

        return this.amount;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    private String formateNumbers(BigDecimal bigDecimal){

     

        if (bigDecimal == null || bigDecimal.doubleValue() < 1){
            return "0.00";
        }

        NumberFormat formatter = new DecimalFormat("##,##,###.00");
        return formatter.format(bigDecimal);
    }

}
