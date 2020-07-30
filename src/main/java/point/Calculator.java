package point;

import java.math.BigDecimal;
import java.util.List;

public class Calculator {
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
               totalPoint=BigDecimal.valueOf(1000).add(currentGoods.getSubTotal().subtract(BigDecimal.valueOf(1000)).divide(BigDecimal.valueOf(20))).setScale( 0, BigDecimal.ROUND_DOWN );
           }
       }
        return totalPoint;
    }

}
