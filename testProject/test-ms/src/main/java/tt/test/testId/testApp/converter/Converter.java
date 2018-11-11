package tt.test.testId.testApp.converter;

/**
 * Created on 10/11/2018
 * @author Dimitris Stavroulakis
 */
public interface Converter<I, O> {

	O convert(I input);

}
