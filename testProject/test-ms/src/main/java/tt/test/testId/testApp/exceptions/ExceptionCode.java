package tt.test.testId.testApp.exceptions;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 10/11/2018
 * @author Dimitris Stavroulakis
 */
public enum ExceptionCode {

    DATA_NOT_FOUND("System error. Data Not Found"),
    SYSTEM_ERROR("System error. Generic Error"),
    BAD_DATA("Bad Data");


    private String code;

    // reverse map to efficiently convert String codes to this enum
    private static final Map<String, ExceptionCode> reverseMap = new HashMap<>();
    static {
        Arrays.stream(values()).forEach(enumeration -> reverseMap.put(enumeration.getCode(), enumeration));
    }

    ExceptionCode(String description) {
        this.code = description;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public static ExceptionCode fromString(String s) {
        return reverseMap.get(s);
    }

}
