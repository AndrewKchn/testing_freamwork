package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.util.Strings;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    private static final Logger LOGGER = LogManager.getLogger();

    @Deprecated(since = "\nCan't find file from resources. Need to update!!!")
    public static String loadFileAsString(String path) {
        LOGGER.info("Load file as String from resources {}", path);
        String fileContent = null;
        try (InputStream is = FileUtil.class.getResourceAsStream(path)) {
            fileContent = IOUtils.toString(is, StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return fileContent;
    }

    /**
     * Gets file name without path
     *
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        Path path = Paths.get(filePath);
        Path fileName = path.getFileName();
        if (null != fileName) {
            return fileName.toString();
        }
        return Strings.EMPTY;
    }

    /**
     * Reads file from root directory
     *
     * @param filePath
     * @return
     */
    public static String readFile(String filePath) {
        LOGGER.info("Load file as String from resources {}", filePath);
        StringBuilder result = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                result.append(currentLine).append("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result.toString();
    }

    /**
     * Writes string to file
     *
     * @param filePath
     * @param content
     */
    public static void writeToFile(String filePath, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
