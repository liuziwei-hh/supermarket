package point;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class SalePoint extends Point {
    private static final BigDecimal SALE_POINT = BigDecimal.valueOf(2.0);
    private List<GoodsType> SaleGoodsTypes = Arrays.asList(GoodsType.APPLE, GoodsType.WATERMELON, GoodsType.BEEF);

    @Override
    boolean isInScope(Goods goods) {
        return !isSale(goods.getType());
    }

    @Override
    BigDecimal getSalePoint() {
        return SALE_POINT;
    }

    private boolean isSale(GoodsType goodsType) {
        return SaleGoodsTypes.contains(goodsType);
    }
}
