package tt.test.testId.testApp.model;

import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * Created on 10/11/2018
 *
 * @author Dimitris Stavroulakis
 */
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Occurrences implements Serializable {

    private String name;
    private Map<String, Long> occur;
}
