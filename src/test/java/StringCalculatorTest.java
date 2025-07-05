import com.example.StringCalculator;
import com.example.StringCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorTest {

        @Test
        void nullStringShouldReturnZero() {
            StringCalculator stringCalculator = new StringCalculator();
            assertEquals(0,stringCalculator.add(""));
        }

    @Test
    void singleNumberReturnsThatNumber() {
        StringCalculator calc = new StringCalculator();
        assertEquals(7, calc.add("7"));
    }

    @Test
    void twoNumbersShouldReturnTheirSum() {
        StringCalculator calc = new StringCalculator();
        assertEquals(3, calc.add("1,2"));
    }

    @Test
    void multipleNumbersShouldReturnTheirSum() {
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add("1,2,3"));

        }

    @Test
    void numbersWithNewlinesShouldReturnSum() {
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add("1\n2,3"));
    }

    @Test
    void customDelimiterShouldReturnSum() {
        StringCalculator calc = new StringCalculator();
        assertEquals(5, calc.add("//;\n3;2"));
    }

    @Test
    void negativeNumberShouldThrowException() {
        StringCalculator calc = new StringCalculator();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            calc.add("1,-5,3");
        });
        assertEquals("negatives not allowed: -5", ex.getMessage());
    }

    @Test
    void multipleNegativesShouldThrowExceptionWithAllNegatives() {
        StringCalculator calc = new StringCalculator();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            calc.add("1,-5,3,-7,-9");
        });
        assertEquals("negatives not allowed: -5, -7, -9", ex.getMessage());
    }

    @Test
    void getCalledCountShouldReturnNumberOfAddCalls() {

        StringCalculator.resetCallCount();
        StringCalculator calc = new StringCalculator();

        calc.add("1,2");
        calc.add("3,4");
        calc.add("5");

        assertEquals(3, calc.getCalledCount());
    }

    @Test
    void numberLargerThanThousandShouldIgnored() {
            StringCalculator calc = new StringCalculator();
            assertEquals(2,calc.add("//*\n1001*2"));

    }

}
