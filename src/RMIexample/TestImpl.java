package RMIexample;

/**
 * Created by Swaneet on 04.12.2014.
 */
public class TestImpl implements ITest {
    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }
}
