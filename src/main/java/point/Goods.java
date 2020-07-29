package point;

public class Goods {
    private final GoodsType goodsType;
    private final int price;
    private final Origin origin;

    Goods(GoodsType goodsType, int price, Origin origin) {
        this.goodsType = goodsType;
        this.price = price;
        this.origin = origin;
    }

    int getPoint() {
        return price;
    }

    GoodsType getType() {
        return goodsType;
    }

    boolean isSale() {
        return origin.equals(Origin.SALE);
    }
}
