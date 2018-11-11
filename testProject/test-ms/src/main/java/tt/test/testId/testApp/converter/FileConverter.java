package tt.test.testId.testApp.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import tt.test.testId.testApp.model.Data;


/**
 *
 * Created on 10/11/2018
 * @author Dimitris Stavroulakis.
 */
@Component
public class FileConverter implements Converter {

	@Override
	public Data convert(Object input1) {
		String[] stringData = (String[]) input1;
		Data data=new Data();
		boolean firstTime=true;
		boolean date=true;
		boolean timeForAction=false;
		for(String t:stringData){
			if (StringUtils.containsIgnoreCase(t,"min")&&firstTime){
				data.setMovieLength(Integer.parseInt(StringUtils.removeEndIgnoreCase(t,"min")));
				firstTime=false;
			}else if(StringUtils.containsIgnoreCase(t,"min")){
				data.setMinWatched(Integer.parseInt(StringUtils.removeEndIgnoreCase(t,"min")));
				timeForAction=true;
			}else if(!timeForAction && !date){
				if(StringUtils.isBlank(data.getMovie())){
					data.setMovie(t);
				}else{
					data.setMovie(data.getMovie()+" "+t);
				}
			}else if(timeForAction){
				data.setGenre(t);
			}
			if(date){
				data.setDate(t);
				date=false;
			}
		}
		return data;
	}

}
