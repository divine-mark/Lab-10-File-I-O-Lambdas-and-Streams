import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class RemoveEvens {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19, 20
        ));

        System.out.println("Before: " + numbers);

        // Predicate is a functional interface: boolean test(T)
        Predicate<Integer> isEven = n -> n % 2 == 0;

        // removeIf takes a Predicate and removes every element that matches
        numbers.removeIf(isEven);

        System.out.println("After:  " + numbers);
    }
}