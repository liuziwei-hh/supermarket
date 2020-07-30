package point;

import java.math.BigDecimal;
import java.util.List;

public class Calculator {
    BigDecimal getTotal(List<Goods> goods) {
        BigDecimal total = BigDecimal.ZERO;
        for (int index = 0; index < goods.size(); index++) {
            Goods currentGoods = goods.get(index);
            total = total.add(currentGoods.getSubTotal());
        }
        return total;
    }

    BigDecimal getInSaleGoods(List<Goods> goods) {
        BigDecimal InSaletotal = BigDecimal.ZERO;
        for (int index = 0; index < goods.size(); index++) {
            Goods currentGoods = goods.get(index);
            if (currentGoods.isInSale()) {
                InSaletotal = InSaletotal.add(currentGoods.getSubTotal());
            }
        }
        return InSaletotal;
    }

    BigDecimal getNotInSaleGoods(List<Goods> goods) {
        BigDecimal NotInSaletotal = BigDecimal.ZERO;
        for (int index = 0; index < goods.size(); index++) {
            Goods currentGoods = goods.get(index);
            if (!currentGoods.isInSale()) {
                NotInSaletotal = NotInSaletotal.add(currentGoods.getSubTotal());
            }
        }
        return NotInSaletotal;
    }

    BigDecimal calculate(List<Goods> goods) {
        BigDecimal totalPoint = BigDecimal.ZERO;
        BigDecimal total = getTotal(goods);
        BigDecimal inSaleGoods = getInSaleGoods(goods);
        BigDecimal notInSaleGoods = getNotInSaleGoods(goods);
        if (total.compareTo(BigDecimal.valueOf(1000)) == 1) {
            if (inSaleGoods.equals(BigDecimal.valueOf(0))) {
                totalPoint = BigDecimal.valueOf(1000).add(total.subtract(BigDecimal.valueOf(1000)).divide(BigDecimal.valueOf(20))).setScale(0, BigDecimal.ROUND_DOWN);
            }
            else if (notInSaleGoods.equals(BigDecimal.valueOf(0))) {
                totalPoint = BigDecimal.valueOf(1000).multiply(BigDecimal.valueOf(2)).add(total.subtract(BigDecimal.valueOf(1000)));
            }
            else if (notInSaleGoods.compareTo(BigDecimal.valueOf(0)) > 0 && inSaleGoods.compareTo(BigDecimal.valueOf(0))>0) {
                if (inSaleGoods.compareTo(BigDecimal.valueOf(1000)) == 1) {
                    totalPoint = BigDecimal.valueOf(1000)
                            .multiply(BigDecimal.valueOf(2))
                            .add(inSaleGoods.subtract(BigDecimal.valueOf(1000)))
                            .add(notInSaleGoods.divide(BigDecimal.valueOf(20)));
                } else {
                    totalPoint = inSaleGoods
                            .multiply(BigDecimal.valueOf(2))
                            .add(BigDecimal.valueOf(1000).subtract(inSaleGoods))
                            .add(notInSaleGoods
                                    .subtract(BigDecimal.valueOf(1000).subtract(inSaleGoods))
                                            .divide(BigDecimal.valueOf(20)));
                }
            }
        } else {
            for (int index = 0; index < goods.size(); index++) {
                Goods currentGoods = goods.get(index);
                if (currentGoods.isInSale()) {
                    totalPoint = totalPoint.add(currentGoods.getSubTotal().multiply(BigDecimal.valueOf(2)));
                } else {
                    totalPoint = totalPoint.add(currentGoods.getSubTotal());
                }
            }
        }
        return totalPoint;
    }
}
