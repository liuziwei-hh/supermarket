package point;

import java.math.BigDecimal;
import java.util.List;

public class Calculator {
    BigDecimal getInSaleTotal(List<Goods> goods) {
        BigDecimal InSaleTotalPoint = BigDecimal.ZERO;
        for (int index=0;index < goods.size(); index++) {
            Goods currentGoods = goods.get(index);
            if (currentGoods.isInSale()){
                InSaleTotalPoint=InSaleTotalPoint.add(currentGoods.getSubTotal());
            }
        }
        return InSaleTotalPoint;
    }
    BigDecimal getNotInSaleTotal(List<Goods> goods) {
        BigDecimal NotInSaleTotalPoint = BigDecimal.ZERO;
        for (int index=0;index < goods.size(); index++) {
            Goods currentGoods = goods.get(index);
            if (!currentGoods.isInSale()){
                NotInSaleTotalPoint=NotInSaleTotalPoint.add(currentGoods.getSubTotal());
            }
        }
        return NotInSaleTotalPoint;
    }
    BigDecimal calculate(List<Goods> goods) {
        BigDecimal totalPoint = BigDecimal.ZERO;
       for (int index=0;index < goods.size(); index++) {
           Goods currentGoods = goods.get(index);
           if (currentGoods.getSubTotal().compareTo(BigDecimal.valueOf(1000)) < 1) {
               if (currentGoods.isInSale()) {
                   totalPoint = totalPoint.add(currentGoods.getSubTotal().multiply(BigDecimal.valueOf(2)));
               } else {
                   totalPoint = totalPoint.add(currentGoods.getSubTotal());
               }
           }
           else{
               if (!currentGoods.isInSale()){
                   totalPoint=BigDecimal.valueOf(1000).add(currentGoods.getSubTotal().subtract(BigDecimal.valueOf(1000)).divide(BigDecimal.valueOf(20))).setScale( 0, BigDecimal.ROUND_DOWN );
               }
               else{
                   if (currentGoods.isInSale()){
                       if (currentGoods.getSubTotal().compareTo(BigDecimal.valueOf(1000)) < 1){
                           totalPoint= this.getInSaleTotal(goods).multiply(BigDecimal.valueOf(2)).add(BigDecimal.valueOf(1000).subtract(this.getInSaleTotal(goods))).add(this.getNotInSaleTotal(goods));
                       }
                       else{
                           totalPoint=BigDecimal.valueOf(1000).multiply(BigDecimal.valueOf(2)).add(this.getInSaleTotal(goods).subtract(BigDecimal.valueOf(1000))).add(this.getNotInSaleTotal(goods).divide(BigDecimal.valueOf(20)));
                       }
                   }
               }
           }
       }
        return totalPoint;
    }

}
