package object_mappper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class DtoConvert {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String UTF_8 = "UTF-8";

    private DtoConvert() {
    }


    public static <T> T mapToDto(Class<T> dtoClass, Map<String, Object> content, boolean failOnUnknown) {
        ObjectMapper dtoObjectMapper = new ObjectMapper();
        dtoObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknown);
        try {
            return dtoObjectMapper.convertValue(content, dtoClass);
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    public static Map jsonFileToMap(String filePath, boolean failOnUnknown) {
        ObjectMapper dtoObjectMapper = new ObjectMapper();
        dtoObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknown);
        try (InputStream is = FileUtil.getFileInputStream(filePath, DtoConvert.class)) {
            return dtoObjectMapper.readValue(is, Map.class);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
