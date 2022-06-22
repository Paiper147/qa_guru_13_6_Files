package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideTest {

    @Test
    void downloadTest() throws Exception {
        Selenide.open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("#raw-url").download();

//      Чтение файла с помощью FileUtils
//        String downloadedFileToString = FileUtils.readFileToString(downloadedFile, StandardCharsets.UTF_8);
//        assertThat(downloadedFileToString).contains("Contributions to JUnit 5");

//        В Java за Чтение и запись файла отвечают механизмы: inputStream/outputStream и reader/writer
//        inputStream - это поток байт
//        Любой файл, который хранится на диске - это набор байт. Некоторые файлы можно прочитать как набор символов
//        То, что можно прочитать как набор символов может быть прочитано с помощью механизма reader
//        (записано с помощью writer) - например, текстовый файл, а то, что можно прочитать как набор байт -
//        прочитано с помощью inputStream (записано с помощью outputStream)
//        Любой файл можно прочитать как набор байт с помощью inputStream (inputStream/outputStream - более универсальный способ),
//        а reader/writer работает только там, где чтение посимвольное

        try (InputStream inputStreamFile = new FileInputStream(downloadedFile)){
            byte[] inputStreamFileContentBytes = inputStreamFile.readAllBytes();
            String fileContentToString = new String(inputStreamFileContentBytes, StandardCharsets.UTF_8);
            assertThat(fileContentToString).contains("Contributions to JUnit 5");
        }
    }

    @Test
    void uploadTest(){
        Selenide.open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("1.txt");
        $$("#fine-uploader-gallery dialog .qq-dialog-message-selector")
                .find(Condition.text("files/1.txt has an invalid extension. Valid extension(s): jpeg, jpg, gif, png."))
                .shouldBe(Condition.visible);
    }

}
