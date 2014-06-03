package calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.BiFunction;

public interface Operator extends BiFunction<BigDecimal, BigDecimal, BigDecimal> {
	public static final Operator add = (x, y) -> x.add(y, MathContext.DECIMAL64);
	public static final Operator subtract = (x, y) -> x.subtract(y, MathContext.DECIMAL64);
	public static final Operator multiply = (x, y) -> x.multiply(y, MathContext.DECIMAL64);
	public static final Operator divide = (x, y) -> x.divide(y, MathContext.DECIMAL64);
	public static final Operator remainder = (x, y) -> x.remainder(y, MathContext.DECIMAL64);
}
