package point;

import java.math.BigDecimal;

public class Goods {
    private final BigDecimal price;
    private int count;
    private String name;

    Goods(String name, BigDecimal price, int count) {
        this.count = count;
        this.price = price;
        this.name=name;
    }

    BigDecimal getPoint() {
        return price;
    }


    boolean isInSale(){

        for (int index=0;index<InSaleGoodsType.values().length;index++){
            if(InSaleGoodsType.values()[index].getName().equals(name)){
                return true;
            }
        }
        return  false;
    }

    public BigDecimal getSubTotal() {
        return this.price.multiply(BigDecimal.valueOf(count));
    }


}
