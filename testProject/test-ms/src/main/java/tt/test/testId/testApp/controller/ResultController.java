package tt.test.testId.testApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tt.test.testId.testApp.model.Data;
import tt.test.testId.testApp.model.Occurrences;
import tt.test.testId.testApp.model.Suggestion;
import tt.test.testId.testApp.service.TestDocsService;
import tt.test.testId.testApp.util.WriteUtil;

import java.util.List;
import java.util.Map;

/**
 * Created on 10/11/2018
 *
 * @author Dimitris Stavroulakis
 */
@RestController
@RequestMapping("/test")
public class ResultController {

    private static Logger logger = LoggerFactory.getLogger(ResultController.class);
    private final TestDocsService testDocsService;
    @Value("${output:#{null}}")
    private String output;

    @Autowired
    public ResultController(TestDocsService testDocsService) {
        this.testDocsService = testDocsService;
    }

    @GetMapping("/question1")
    public ResponseEntity<Map<String, List<Data>>> resultQ1() {
        Map<String, List<Data>> response = testDocsService.question1();
        WriteUtil.writeToFile("Question 1",output,response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/question2")
    public ResponseEntity<Map<String, Double>> resultQ2() {
        Map<String, Double> response = testDocsService.question2();
        WriteUtil.writeToFile("Question 2",output,response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/question3")
    public ResponseEntity<List<Occurrences>> resultQ3() {
        List<Occurrences> occurrences = testDocsService.question3();
        WriteUtil.writeToFile("Question 3",output,occurrences);;
        return ResponseEntity.ok(occurrences);
    }

    @GetMapping("/question4")
    public ResponseEntity<Suggestion> resultQ4() {
        Suggestion suggestion = testDocsService.question4();
        WriteUtil.writeToFile("Question 4",output,suggestion);
        return ResponseEntity.ok(suggestion);
    }


}

