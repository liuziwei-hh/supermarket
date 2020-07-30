package point;

import org.junit.Test;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    @Test
    public void should_return_point_when_calculate_given_bought_not_in_sales() {
        //given
        List<Goods> goods = Arrays.asList(new Goods("黄瓜", BigDecimal.valueOf(10), 10));
        Calculator calculator = new Calculator();

        //when
        BigDecimal point = calculator.calculate(goods);

        //then
        assertThat(point, is(BigDecimal.valueOf(100)));
    }

    @Test
    public void should_return_point_when_calculate_given_bought_goods_in_sales() {
        //given
        List<Goods> goods = Arrays.asList(new Goods("苹果", BigDecimal.valueOf(10), 1),new Goods("西瓜", BigDecimal.valueOf(30), 1),new Goods("洗衣液", BigDecimal.valueOf(20), 1));
        Calculator calculator = new Calculator();

        //when
        BigDecimal point = calculator.calculate(goods);

        //then
        assertThat(point, is(BigDecimal.valueOf(100)));
    }

    @Test
    public void should_return_point_when_calculate_given_bought_not_in_sales_more_than_1000() {
        //given
        List<Goods> goods = Arrays.asList(new Goods("冰箱", BigDecimal.valueOf(2350), 1));
        Calculator calculator = new Calculator();

        //when
        BigDecimal point = calculator.calculate(goods);

        //then
        assertThat(point, is(BigDecimal.valueOf(1067)));
    }
}



