package point;

public enum InSaleGoodsType {
    APPLE("苹果"),BEEF("牛肉"),WATERMELON("西瓜"),TELEVISION("电视机");

    private String name;

    InSaleGoodsType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
