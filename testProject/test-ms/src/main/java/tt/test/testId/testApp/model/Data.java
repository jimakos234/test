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
public class Data implements Serializable {

    private String date;
    private String movie;
    private int movieLength;
    private int minWatched;
    private String genre;

}
