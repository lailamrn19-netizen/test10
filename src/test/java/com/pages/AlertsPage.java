package com.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.io.File;


public class AlertsPage extends BasePage {

    // Locators para Alerts & Windows
    private final By btnAlert = By.id("show-alert");
    private final By btnConfi = By.id("show-confirm");
    private final By btnProm = By.id("show-prompt");
    private final By btnNewPes = By.id("open-window");
    private final By btnconfres = By.xpath("//button[text()='Confirmar reserva']");
    private final By resultText = By.id("alert-result");

    // Locators de Formulario de Reserva
    private final By selectExperience = By.id("booking-activity");
    private final By inputDate = By.id("booking-date");
    private final By inputEmail = By.id("booking-email");
    private final By inputTickets = By.id("booking-tickets");
    private final By statusMessage = By.id("booking-result");
    private final By btnReset = By.xpath("//button[text()='Restablecer']");

    // Locators para Descargas y Reportes
    private final String downloadFolderPath = System.getProperty("user.home") + "/Downloads";
    private final By selectFormat = By.id("report-format");
    private final By btnDownload = By.id("download-report");
    private final By downloadNotification = By.id("download-result");

    // Locators para Carga de Archivos
    private final By fileInput = By.id("upload-file");
    private final By btnUpload = By.xpath("//button[contains(text(),'Subir archivo')]");
    private final By responseContainer = By.id("upload-result");

    private String mainTabHandle;

    public void clickButton(String buttonName) {
        switch (buttonName.trim().toLowerCase()) {
            case "abrir alert":
                safeClick(btnAlert);
                break;
            case "abrir confirm":
                safeClick(btnConfi);
                break;
            case "abrir prompt":
                safeClick(btnProm);
                break;
            case "abrir nueva pestaña":
                mainTabHandle = driver.getWindowHandle();
                safeClick(btnNewPes);
                break;
            case "confirmar reserva":
                scrollToBottom();
                safeClick(btnconfres);
                break;
            case "restablecer":
                scrollToBottom();
                safeClick(btnReset);
                break;
            default:
                By genericButton = By.xpath("//button[contains(normalize-space(), '" + buttonName + "')]");
                safeClick(genericButton);
                break;
        }
    }

    public void clickDownloadButton() {
        scrollToBottom();
        safeClick(btnDownload);
    }

    public void handleDialog(String action, String inputText) {
        if (!"N/A".equalsIgnoreCase(inputText)) {
            sendKeysToAlertAndAccept(inputText);
        } else if ("accept".equalsIgnoreCase(action)) {
            acceptAlertAndGetText();
        } else if ("dismiss".equalsIgnoreCase(action)) {
            dismissAlertAndGetText();
        }
    }

    public String getStatusMessage() {
        return getText(resultText);
    }

    public boolean isPathInUrl(String path) {
        return wait.until(ExpectedConditions.urlContains(path));
    }

    public void switchToNewTab() {
        if (mainTabHandle == null) {
            mainTabHandle = driver.getWindowHandle();
        }
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainTabHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    public void fillReservationForm(String experience, String date, String email, String tickets) {
        scrollToBottom();
        // 1. Desplegable Experiencia
        if (!"N/A".equalsIgnoreCase(experience)) {
            if ("EMPTY".equalsIgnoreCase(experience) || "VACIO".equalsIgnoreCase(experience)) {
                selectByVisibleText(selectExperience, "Selecciona una experiencia"); // Opción por defecto
            } else {
                selectByVisibleText(selectExperience, experience);
            }
        }

        // 2. Inputs de texto y número
        typeIfPresent(inputDate, date);
        typeIfPresent(inputEmail, email);
        typeIfPresent(inputTickets, tickets);
    }

    public String getReservationStatusMessage() {
        return getText(statusMessage);
    }

    public void selectReportFormat(String format) {
        String optionText = format;
        if ("TXT".equalsIgnoreCase(format)) {
            optionText = "Texto (.txt)";
        } else if ("CSV".equalsIgnoreCase(format)) {
            optionText = "CSV (.csv)";
        }
        selectByVisibleText(selectFormat, optionText);
    }

    public boolean isOriginalTabAccessible() {
        try {
            driver.switchTo().window(mainTabHandle);
            return driver.getTitle() != null || driver.getCurrentUrl() != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFileDownloaded(String fileName) {
        File dir = new File(downloadFolderPath);
        int timeoutInSeconds = 10;

        for (int i = 0; i < timeoutInSeconds; i++) {
            File[] dirContents = dir.listFiles();
            if (dirContents != null) {
                for (File file : dirContents) {
                    if (file.getName().equals(fileName)) {
                        return true;
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return false;
    }

    public boolean verifyFileContent(String fileName, String expectedContent) {
        File file = new File(downloadFolderPath + File.separator + fileName);
        if (!file.exists()) {
            return false;
        }

        try {
            String content = Files.readString(file.toPath());
            if ("header and data row".equalsIgnoreCase(expectedContent)) {
                return content.contains(",") && content.split("\n").length >= 2;
            }
            return content.contains(expectedContent);
        } catch (IOException e) {
            return false;
        }
    }

    public String getNotificationText() {
        return getText(downloadNotification);
    }

    public String createDummyFile(String fileName, long sizeInMB) {
        File file = new File(System.getProperty("java.io.tmpdir") + File.separator + fileName);
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.setLength(sizeInMB * 1024 * 1024);
        } catch (Exception ignored) {
        }
        return file.getAbsolutePath();
    }

    public void uploadFile(String filePath) {
        scrollToBottom();
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
        input.sendKeys(filePath);
    }

    public void clickUploadButton() {
        scrollToBottom();
        safeClick(btnUpload);
    }

    public String GreetingText() {
        // 1. Validar si existe mensaje HTML5 nativo
        String html5Error = getHtml5ValidationMessage(By.cssSelector("input[type='file']"));
        if (!html5Error.isEmpty()) {
            return html5Error;
        }

        // 2. Obtener el texto de respuesta tras actualizarse el contenedor
        try {
            wait.until(d -> {
                String txt = getText(responseContainer);
                return !txt.isEmpty() && !txt.contains("máximo 5 MB");
            });
            return getText(responseContainer);
        } catch (Exception e) {
            return getText(responseContainer);
        }
    }

    public String getResponseMessage() {
        scrollToBottom();
        return getText(responseContainer);
    }
    public boolean isFormReset() {
        // 1. Obtener el valor del Select (debe volver a la opción por defecto o estar vacío)
        Select select = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(selectExperience)));
        String selectedOption = select.getFirstSelectedOption().getText();
        boolean isExperienceReset = selectedOption.contains("Selecciona una experiencia");

        // 2. Obtener el atributo 'value' de los inputs
        String dateValue = driver.findElement(inputDate).getAttribute("value");
        String emailValue = driver.findElement(inputEmail).getAttribute("value");
        String ticketsValue = driver.findElement(inputTickets).getAttribute("value");

        // 3. Comprobar que los campos estén vacíos o con su valor inicial (entradas suele volver a 1 o quedar vacío)
        boolean isDateEmpty = dateValue == null || dateValue.isEmpty();
        boolean isEmailEmpty = emailValue == null || emailValue.isEmpty();
        boolean isTicketsReset = ticketsValue == null || ticketsValue.isEmpty() || ticketsValue.equals("1");

        return isExperienceReset && isDateEmpty && isEmailEmpty && isTicketsReset;
    }
}