package point;

import java.math.BigDecimal;
import java.util.List;

 class Calculator {

    private static final BigDecimal TOTAL_POINT_LINE = BigDecimal.valueOf(1000);
    private static final BigDecimal POINT_DOUBLE = BigDecimal.valueOf(2);
    private static final BigDecimal POINT_DIVISOR = BigDecimal.valueOf(20);

    BigDecimal getTotal(List<Goods> goods) {
        BigDecimal total = BigDecimal.ZERO;
        for (int index = 0; index < goods.size(); index++) {
            Goods currentGoods = goods.get(index);
            total = total.add(currentGoods.getSubTotal());
        }
        return total;
    }

    BigDecimal getInSaleGoods(List<Goods> goods) {
        BigDecimal inSaleTotal = BigDecimal.ZERO;
        for (Goods currentGoods : goods) {
            if (currentGoods.isInSale()) {
                inSaleTotal = inSaleTotal.add(currentGoods.getSubTotal());
            }
        }
        return inSaleTotal;
    }

    BigDecimal getNotInSaleGoods(List<Goods> goods) {
        BigDecimal NotInSaletotal = BigDecimal.ZERO;
        for (Goods currentGoods : goods) {
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
        if (total.compareTo(TOTAL_POINT_LINE) == 1) {
            totalPoint = getPointMoreThan1000(totalPoint, total, inSaleGoods, notInSaleGoods);
        } else {
            totalPoint = getPointLessThan1000(goods, totalPoint);
        }
        return totalPoint;
    }

    private BigDecimal getPointMoreThan1000(BigDecimal totalPoint, BigDecimal total, BigDecimal inSaleGoods, BigDecimal notInSaleGoods) {
        if (inSaleGoods.equals(BigDecimal.ZERO)) {
            totalPoint = getPointInSales(total);
        }
        if (notInSaleGoods.equals(BigDecimal.ZERO)) {
            totalPoint = getPointNotInSales(total);
        }
        if (notInSaleGoods.compareTo(BigDecimal.ZERO) > 0 && inSaleGoods.compareTo(BigDecimal.ZERO)>0) {
            totalPoint = getPointContainsSalesAndNotSales(inSaleGoods, notInSaleGoods);
        }
        return totalPoint;
    }

    private BigDecimal getPointNotInSales(BigDecimal total) {
        BigDecimal totalPoint;
        totalPoint = TOTAL_POINT_LINE
                .multiply(POINT_DOUBLE)
                .add(total.subtract(TOTAL_POINT_LINE));
        return totalPoint;
    }

    private BigDecimal getPointInSales(BigDecimal total) {
        BigDecimal totalPoint;
        totalPoint = TOTAL_POINT_LINE
                .add(total.subtract(TOTAL_POINT_LINE).divide(POINT_DIVISOR))
                .setScale(0, BigDecimal.ROUND_DOWN);
        return totalPoint;
    }

    private BigDecimal getPointContainsSalesAndNotSales(BigDecimal inSaleGoods, BigDecimal notInSaleGoods) {
        BigDecimal totalPoint;
        if (inSaleGoods.compareTo(TOTAL_POINT_LINE) == 1) {
            totalPoint = TOTAL_POINT_LINE
                    .multiply(POINT_DOUBLE)
                    .add(inSaleGoods.subtract(TOTAL_POINT_LINE))
                    .add(notInSaleGoods.divide(POINT_DIVISOR));
        } else {
            totalPoint = inSaleGoods
                    .multiply(POINT_DOUBLE)
                    .add(TOTAL_POINT_LINE.subtract(inSaleGoods))
                    .add(notInSaleGoods
                            .subtract(TOTAL_POINT_LINE.subtract(inSaleGoods))
                                    .divide(POINT_DIVISOR));
        }
        return totalPoint;
    }

    private BigDecimal getPointLessThan1000(List<Goods> goods, BigDecimal totalPoint) {
        for (Goods currentGoods : goods) {
            if (currentGoods.isInSale()) {
                totalPoint = totalPoint.add(currentGoods.getSubTotal().multiply(POINT_DOUBLE));
            } else {
                totalPoint = totalPoint.add(currentGoods.getSubTotal());
            }
        }
        return totalPoint;
    }
}
