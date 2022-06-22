package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import guru.qa.models.Teacher;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParseTest {
//    classloader - это механизм, который в Java отвечает за то, чтобы искать и загружать
//    классы и файлы, находящиеся в ресурсах

    //    "classLoader" это ClassLoader, которым класс FileParseTest будет загружен
    ClassLoader classLoader = FileParseTest.class.getClassLoader();

    @Test
    void pdfTest() throws Exception {
//        'com.codeborne:pdf-test:1.7.0'
        Selenide.open("https://junit.org/junit5/docs/current/user-guide/");
        File downloadedPdfFile = $("a[href*='junit-user-guide-5.8.2.pdf']").download();
        PDF pdf = new PDF(downloadedPdfFile);
        assertThat(pdf.author).isEqualTo("Stefan Bechtold, Sam Brannen, Johannes Link, " +
                "Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein");
    }

    @Test
    void pdfTestClassloader() throws Exception {
//        'com.codeborne:pdf-test:1.7.0'
        try (InputStream inputStreamFile = classLoader.getResourceAsStream("junit-user-guide-5.8.2.pdf")) {
            assert inputStreamFile != null;
            PDF pdf = new PDF(inputStreamFile);
            assertThat(pdf.author).isEqualTo("Stefan Bechtold, Sam Brannen, Johannes Link, " +
                    "Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein");
        }
    }

    @Test
    void xlsTest() throws Exception {
//        'com.codeborne:xls-test:1.4.3'
        Selenide.open("http://romashka2008.ru/price");
        File downloadedXlsFile = $(".site-main a[href*='/f/prajs_ot_1606_1.xls']").download();
        XLS xls = new XLS(downloadedXlsFile);
        assertThat(
                xls.excel.getSheetAt(0)
                        .getRow(22)
                        .getCell(2)
                        .getStringCellValue().trim()
        ).isEqualTo("Бумага для цветной печати");
    }

    @Test
    void xlsTestClassLoader() throws Exception {
//        'com.codeborne:xls-test:1.4.3'
        try (InputStream inputStreamFile = getClass().getClassLoader().getResourceAsStream("prajs_ot_1606_1.xls")) {
            XLS xls = new XLS(inputStreamFile);
            assertThat(
                    xls.excel.getSheetAt(0)
                            .getRow(22)
                            .getCell(2)
                            .getStringCellValue().trim()
            ).isEqualTo("Бумага для цветной печати");
        }
    }

    @Test
    void csvTest() throws Exception {
//        'com.opencsv:opencsv:5.6'
//        CSVReader - это объект, который мы передали в Java механизм чтения файлов (в данном случае, т.к. CSVReader
//        работает с посимвольным чтением, а не с побайтовым, надо было создать InputStreamReader - преобразование
//        из байтового стрима в reader посимвольный. И чтобы это преобразование случилось, мы должны передать
//        stream и сказать с помощью какой кодировке мы будем байты в символы перекодировать
        try (InputStream inputStreamFile = classLoader.getResourceAsStream("example.csv");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStreamFile, StandardCharsets.UTF_8)) {
            CSVReader csvReader = new CSVReader(inputStreamReader);
            List<String[]> csvContentFile = csvReader.readAll();
            assertThat(csvContentFile).contains(
                    new String[]{"teacher", "lesson", "date"},
                    new String[]{"Tuchs", "JUnit", "03.06"},
                    new String[]{"Eroshenko", "allure", "07.06"}
            );
        }
    }

    @Test
    void zipTest() throws Exception {
        ZipFile zipFile = new ZipFile("src/test/resources/1.zip");
        try (InputStream inputStreamFile = classLoader.getResourceAsStream("1.zip");
             ZipInputStream zipInputStream = new ZipInputStream(inputStreamFile)) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                try (InputStream inputStream = zipFile.getInputStream(entry)) {
                    assertThat(entry.getName()).isEqualTo("sample.txt");
                }
            }
        }
    }

    @Test
    void jsonTest() throws Exception {
        try (InputStream inputStreamFile = classLoader.getResourceAsStream("teacher.json");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStreamFile)) {
            Gson gson = new Gson();
            JsonObject jsonObjectFile = gson.fromJson(inputStreamReader, JsonObject.class);
            assertThat(jsonObjectFile.get("names").getAsJsonArray().get(0).getAsString()).isEqualTo("Dmitrii");
            assertThat(jsonObjectFile.get("names").getAsJsonArray().get(1).getAsString()).isEqualTo("Artem");
            assertThat(jsonObjectFile.get("profession").getAsString()).isEqualTo("teacher");
            assertThat(jsonObjectFile.get("isGoodTeacher").getAsBoolean()).isEqualTo(true);
            assertThat(jsonObjectFile.get("passport").getAsJsonObject().get("number").getAsInt()).isEqualTo(1234);
        }
    }

    @Test
    void jsonTestGson() throws Exception {
        try (InputStream inputStreamFile = classLoader.getResourceAsStream("teacher.json");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStreamFile)) {
            Gson gson = new Gson();
            Teacher jsonObjectFile = gson.fromJson(inputStreamReader, Teacher.class);
            assertThat(jsonObjectFile.getNames().get(0)).isEqualTo("Dmitrii");
            assertThat(jsonObjectFile.getNames().get(1)).isEqualTo("Artem");
            assertThat(jsonObjectFile.getProfession()).isEqualTo("teacher");
            assertThat(jsonObjectFile.isGoodTeacher()).isEqualTo(true);
            assertThat(jsonObjectFile.getPassport().getNumber()).isEqualTo(1234);
        }
    }
}
