package com.dlnu;

import com.dlnu.entity.user.StockinData;
import com.dlnu.entity.user.StockoutData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class DatabaseSpringBootApplicationTests {
    public static int getDateDifference(String format, String date1Str, String date2Str) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date date1 = dateFormat.parse(date1Str);
            Date date2 = dateFormat.parse(date2Str);
            long difference = date1.getTime() - date2.getTime();
            int daysDifference = (int) (difference / (24 * 60 * 60 * 1000));
            return daysDifference;
        } catch (Exception e) {
            return 0;
        }
    }

    public void calculateStorageFee(StockoutData[] stockoutData, StockinData[] stockinData) {
        int stockinsum = 0;
        int stockoutsum = 0;
        double feesum = 0;

        int i = 0;
        int e = 0;

        while (i < stockoutData.length) {
            if (stockinsum > 0) {
                int obton = Integer.parseInt(stockoutData[i].getObton());
                if (stockinsum > obton) {
                    stockinsum -= obton;
                    int totalTime = getDateDifference("yyyyMMdd", stockoutData[i].getDetime(), stockinData[e].getWhtime()) + 1;

                    if (totalTime > 14) {
                        totalTime -= 14;
                        double totalCost = totalTime * obton * 0.5;
                        stockoutData[i].setStoragefee(totalCost);
                    } else {
                        stockoutData[i].setStoragefee(0);
                    }
                    i++;
                } else if (stockinsum < obton) {
                    stockoutsum = obton - stockinsum;
                    int totalTime = getDateDifference("yyyyMMdd", stockoutData[i].getDetime(), stockinData[e].getWhtime()) + 1;
                    if (totalTime > 14) {
                        totalTime -= 14;
                        double totalCost = totalTime * stockinsum * 0.5;
                        feesum += totalCost;
                    } else {
                        feesum = 0;
                    }
                    stockinsum = 0;
                    e++;
                } else {
                    stockinsum = 0;
                    int totalTime = getDateDifference("yyyyMMdd", stockoutData[i].getDetime(), stockinData[e].getWhtime()) + 1;
                    if (totalTime > 14) {
                        totalTime -= 14;
                        double totalCost = totalTime * obton * 0.5;
                        stockoutData[i].setStoragefee(totalCost);
                    } else {
                        stockoutData[i].setStoragefee(0);
                    }
                    i++;
                    e++;
                }
            } else {
                int obton = Integer.parseInt(stockoutData[i].getObton());
                System.out.println(obton);
                int ibton = Integer.parseInt(stockinData[e].getIbton());
                System.out.println(ibton);
                if (obton > ibton) {
                    stockoutsum = obton - ibton;
                    int totalTime = getDateDifference("yyyyMMdd", stockoutData[i].getDetime(), stockinData[e].getWhtime()) + 1;
                    if (totalTime <= 14) {
                        stockoutData[i].setStoragefee(0);
                    } else {
                        totalTime -= 14;
                        double totalCost = totalTime * ibton * 0.5;
                        feesum += totalCost;
                    }
                    e++;
                } else if (obton < ibton) {
                    stockinsum = ibton - obton;
                    int totalTime = getDateDifference("yyyyMMdd", stockoutData[i].getDetime(), stockinData[e].getWhtime()) + 1;
                    if (totalTime <= 14) {
                        stockoutData[i].setStoragefee(0);
                    } else {
                        totalTime -= 14;
                        double totalCost = totalTime * obton * 0.5;
                        stockoutData[i].setStoragefee(totalCost);
                    }
                    i++;
                } else {
                    int totalTime = getDateDifference("yyyyMMdd", stockoutData[i].getDetime(), stockinData[e].getWhtime()) + 1;
                    if (totalTime <= 14) {
                        stockoutData[i].setStoragefee(0);
                    } else {
                        totalTime -= 14;
                        double totalCost = totalTime * obton * 0.5;
                        stockoutData[i].setStoragefee(totalCost);
                    }
                    i++;
                    e++;
                }
            }
        }
    }
    @Test
    void contextLoads() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));

    }

    @Test
    void suanfa(){
        StockinData[] stockinData = new StockinData[3];
        StockoutData[] stockoutData = new StockoutData[3];

        stockinData[0] = new StockinData();
        stockinData[0].setWhtime("20210801");
        stockinData[0].setIbton("30");
        stockinData[1] = new StockinData();
        stockinData[1].setWhtime("20210821");
        stockinData[1].setIbton("33");
        stockinData[2] = new StockinData();
        stockinData[2].setWhtime("20210818");
        stockinData[2].setIbton("33");
        stockoutData[0] = new StockoutData();
        stockoutData[0].setDetime("20210821");
        stockoutData[0].setObton("36");
        stockoutData[1] = new StockoutData();
        stockoutData[1].setDetime("20210821");
        stockoutData[1].setObton("30");
        stockoutData[2] = new StockoutData();
        stockoutData[2].setDetime("20210901");
        stockoutData[2].setObton("36");

        calculateStorageFee(stockoutData, stockinData);

        System.out.println(stockoutData[0].getStoragefee());
    }

}
