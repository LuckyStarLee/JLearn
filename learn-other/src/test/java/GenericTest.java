
public class GenericTest<E,T> {

    public <E extends A> E test(T xx){

        return (E)xx;
    }
}
