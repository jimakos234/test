package tt.test.testId.testApp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class WriteUtil {

    private static final Logger logger = LoggerFactory.getLogger(WriteUtil.class);

    public static void writeToFile(String question, String filename, Object write) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println();
            printWriter.println(question + ": "+write.toString());
            printWriter.close();
        }catch (IOException e){
            logger.warn("Could not write to file");
        }
    }
}
