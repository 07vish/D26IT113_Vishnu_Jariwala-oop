@FunctionalInterface
public interface DiscountRule {
    double apply(double originalPrice);
}
