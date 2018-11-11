package tt.test.testId.testApp.model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 10/11/2018
 *
 * @author Dimitris Stavroulakis
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User implements Serializable {

    private String name;
    private List<Data> movieData;

    public User(String name) {
        this.name = name;
    }

    public void addMovieData(Data data){
        if(movieData==null){
            movieData = new ArrayList<>();
        }
         movieData.add(data);
    }
}
