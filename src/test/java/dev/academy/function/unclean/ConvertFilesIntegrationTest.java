package dev.academy.function.unclean;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(Lifecycle.PER_CLASS)
class ConvertFilesIntegrationTest {
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
        String[] args = {"-i", new File(_inputPath).getPath(), "-o", tempFile.getAbsolutePath(), "-s", ","};
        new ConvertFiles().doMain(args);
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
