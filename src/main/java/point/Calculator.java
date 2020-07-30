package point;

import java.math.BigDecimal;
import java.util.List;

public class Calculator {
    BigDecimal calculate(List<Goods> goods) {
        BigDecimal totalPoint = BigDecimal.ZERO;
       for (int index=0;index < goods.size(); index++) {
           Goods currentGoods=goods.get(index);
           if(currentGoods.isInSale()){
               totalPoint=totalPoint.add(currentGoods.getSubTotal().multiply(BigDecimal.valueOf(2)));
           }
           else{
               totalPoint=totalPoint.add(currentGoods.getSubTotal());
           }
       }
        return totalPoint;
    }

}
