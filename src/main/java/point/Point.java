package point;

import java.math.BigDecimal;

abstract class Point {
    abstract boolean isInScope(Goods goods);
    abstract BigDecimal getSalePoint();

    BigDecimal chargePoint(Goods goods) {
        if (isInScope(goods)) {
            return getSalePoint();
        }
        return  BigDecimal.valueOf(1.0);
    }

}
