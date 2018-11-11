package tt.test.testId.testApp.model;

import lombok.*;

import java.io.Serializable;

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
public class Suggestion implements Serializable {

    private Occurrences occurrences;
    private double averageMovieMin;
}
