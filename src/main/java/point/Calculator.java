package point;

import java.math.BigDecimal;
import java.util.List;

class Calculator {
    BigDecimal calculate(Goods goods, List<Point> points) {
        BigDecimal ratioSum = points.stream()
                .map(point -> point.chargePoint(goods))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return ratioSum.multiply(BigDecimal.valueOf(goods.getPoint())).setScale(0);
    }
}
