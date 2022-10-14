package dev.academy.function.clean;

import dev.academy.function.unclean.ConvertFiles;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(Lifecycle.PER_CLASS)
class ConvertFilesCleanIntegrationTest {
    String _inputPath = "src/test/resources/convertFilesTestResource";
    private File tempFile;

    @BeforeEach
    public void before() throws IOException {
        this.tempFile = File.createTempFile("convert", ".csv");
    }

    @AfterEach
    public void after() {
        tempFile.deleteOnExit();
    }

    @Test
    public void testDoMain() {
        // setup
        String[] args = {"-i", new File(_inputPath).getPath(), "-o", tempFile.getAbsolutePath(), "-s", ","};

        // to test
        ConvertFilesClean.main(args);

        // verify
        List<String> lines = readFile(tempFile);
        assertThat(lines).containsExactlyElementsOf(
                List.of(
                        "A_1,B_1,A_2,B_2",
                        "00,01,10,11",
                        "-00,-01,-10,-11"));
    }

    private static List<String> readFile(File outputFile) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(outputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
