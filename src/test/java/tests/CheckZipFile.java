package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckZipFile {

    ClassLoader classLoader = CheckZipFile.class.getClassLoader();

    @Test
    void zipTest() throws Exception {
        ZipFile zipFile = new ZipFile("src/test/resources/1.zip");
        InputStream inputStreamFile = classLoader.getResourceAsStream("1.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStreamFile);
        ZipEntry entry;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            try (InputStream inputStream = zipFile.getInputStream(entry)) {
                if (entry.getName().contains(".pdf")) {
                    System.out.println("pdf");
                    PDF pdf = new PDF(inputStream);
                    assertThat(pdf.author).isEqualTo("Stefan Bechtold, Sam Brannen, " +
                            "Johannes Link, Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein");
                } else if (entry.getName().contains(".xls")) {
                    System.out.println("xls");
                    XLS xls = new XLS(inputStream);
                    assertThat(
                            xls.excel.getSheetAt(0)
                                    .getRow(22)
                                    .getCell(2)
                                    .getStringCellValue().trim()
                    ).isEqualTo("Бумага для цветной печати");
                } else if (entry.getName().contains(".csv")) {
                    System.out.println("csv");
                    CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                    List<String[]> csvContentFile = csvReader.readAll();
                    assertThat(csvContentFile).contains(
                            new String[]{"teacher", "lesson", "date"},
                            new String[]{"Tuchs", "JUnit", "03.06"},
                            new String[]{"Eroshenko", "allure", "07.06"}
                    );
                } else {
                    Assertions.fail("!!! zip file contains unknown file type!!!");
                }
            }
        }
    }
}
