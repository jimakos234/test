package tt.test.testId.testApp.service;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tt.test.testId.testApp.converter.FileConverter;
import tt.test.testId.testApp.exceptions.DataNotFoundException;
import tt.test.testId.testApp.exceptions.ExceptionCode;
import tt.test.testId.testApp.exceptions.TestException;
import tt.test.testId.testApp.model.Data;
import tt.test.testId.testApp.model.Occurrences;
import tt.test.testId.testApp.model.Suggestion;
import tt.test.testId.testApp.model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service
 * @author Dimitris Stavroulakis on 10/11/2018.
 */
@Component
public class TestDocsService {

	private static final Logger logger = LoggerFactory.getLogger(TestDocsService.class);

	@Value("${file1:#{'C:\\Users\\Jimis\\IdeaProjects\\test\\testProject\\test-ms\\src\\test\\resources\\test1.txt'}}")
	private String file1;
	@Value("${file2:#{'C:\\Users\\Jimis\\IdeaProjects\\test\\testProject\\test-ms\\src\\test\\resources\\test1.txt'}}")
	private String file2;
	@Value("${file3:#{'C:\\Users\\Jimis\\IdeaProjects\\test\\testProject\\test-ms\\src\\test\\resources\\test1.txt'}}")
	private String file3;


	private final FileConverter fileConverter;
	private List<String> list = new ArrayList<>();
	private Map<String,List<Data>> mapdata = new HashMap<>();
	private int i=0;
	private List<User> users = new ArrayList<>();

	@Autowired
	public TestDocsService(FileConverter fileConverter) {
		this.fileConverter = fileConverter;
	}


	/**
	 * function that initiated the user data from the files provided
	 */
	public void initUsers() {
		logger.info("Start file convert: {} ");
		try {
			list.clear();
			List<File> files= new ArrayList<>();
			files.add(new File(file1));
			files.add(new File(file2));
			files.add(new File(file3));
			users.add(new User(files.get(0).getName().replace(".txt","")));
			users.add(new User(files.get(1).getName().replace(".txt","")));
			users.add(new User(files.get(2).getName().replace(".txt","")));
			files.forEach(f->{
				try (Stream<String> stream = Files.lines(Paths.get(f.getAbsolutePath()))) {
					list.addAll(stream.collect(Collectors.toList()));
					for(String s:list){
						String data[] = StringUtils.split(s," ");
						users.get(i).addMovieData(fileConverter.convert(data));
					}
					list.clear();
					i++;
				} catch (IOException e) {
					logger.warn("Could not find file.", f);
					throw new DataNotFoundException(true);
				}
			});
		}catch (TestException e) {
			if(!e.isLogged()){
				logger.error("Could not execute test.",e);
				e.setLogged(true);
			}
			throw e;
		}catch (Exception e) {
			logger.error("Could not execute test",e);
			throw new TestException("Could not execute test",ExceptionCode.SYSTEM_ERROR,false);
		}

	}

	/**
	 * Question 1 return reversed order by time watched
	 * @return
	 */
	public Map<String, List<Data>> question1(){
		if(users ==null || users.isEmpty()){
			initUsers();
		}
		Map<String, List<Data>> stringListMap = new HashMap<>();
		users.forEach(t-> {
			stringListMap.put(t.getName()+" ",t.getMovieData().stream()
					.sorted(Comparator.comparingInt(Data::getMinWatched)
					.reversed()).collect(Collectors.toList()));

		});
		return stringListMap;
	}

	/**
	 * Question 2 about favorite genre
	 * @return
	 */
	public Map<String, Double> question2(){
		if(users ==null || users.isEmpty()){
			initUsers();
		}
		Map<String, Double> stringListMap = new HashMap<>();
		users.forEach(t-> {
			List<Integer> intList = t.getMovieData().stream()
					.sorted(Comparator.comparingInt(Data::getMinWatched).reversed())
					.map(Data::getMinWatched).collect(Collectors.toList());
			IntSummaryStatistics stats = intList.stream()
					.mapToInt((x) -> x)
					.summaryStatistics();
			stringListMap.put(t.getName(),stats.getAverage());
		});
		return stringListMap;
	}

	/**
	 * Question 3 about occurrences
	 * @return
	 */
	public List<Occurrences> question3(){
		if(users ==null || users.isEmpty()){
			initUsers();
		}
		List<Occurrences> userOccurrences= new ArrayList<>();
		users.forEach(t-> {
			Map<String, Long> occurrences = new HashMap<>();
			List<Integer> intList = t.getMovieData().stream()
					.sorted(Comparator.comparingInt(Data::getMinWatched).reversed())
					.map(Data::getMinWatched).collect(Collectors.toList());
			IntSummaryStatistics stats = intList.stream()
					.mapToInt((x) -> x)
					.summaryStatistics();
			List<Integer> over60 = intList.stream().filter(g->g>Double.valueOf(stats.getAverage()).intValue()).collect(Collectors.toList());
			List<String> datafavorite = new ArrayList<>();
			for(Data dataf:t.getMovieData()){
				for (int over:over60) {
					if (dataf.getMinWatched()<over){
					}else{
						/*
						 * Add favorite genre based on over 60% watch rate
						 */
						datafavorite.add(dataf.getGenre());
						break;
					}
				}
			}
			occurrences.putAll(	datafavorite.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting())));
			userOccurrences.add(new Occurrences(t.getName(),occurrences));
		});
		return userOccurrences;
	}

	/**
	 * Question 3 about ideal movie
	 * @return
	 */
	public Suggestion question4(){
		List<Occurrences> q4answer= question3();
		Occurrences all = new Occurrences();
		all.setName("Suggestion for all 3 together");
		all.setOccur(new HashMap<String,Long>());
		for (Occurrences occurrences:q4answer){
			for (String s:occurrences.getOccur().keySet()){
				if(all.getOccur().containsKey(s)){
					all.getOccur().put(s,all.getOccur().get(s)+occurrences.getOccur().get(s));
				}else{
					all.getOccur().put(s,occurrences.getOccur().get(s));
				}
			}

		}
		Map<String,Long> top = all.getOccur().entrySet().stream()
			.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			.limit(2)
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		all.setOccur(top);
		Map<String, Double> avgMap =question2();
		/*
		 * We will use the average of all the users since it was not clarified how to find the movie length
		 */
		double totalCounts = avgMap.values().stream()
				.collect(Collectors.summingDouble(Double::doubleValue));
		return new Suggestion(all,totalCounts/3);
	}


}
