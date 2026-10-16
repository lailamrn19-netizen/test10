package com.pages;

import org.openqa.selenium.By;

public class Locators {
    // Locators para Alerts & Windows
    public static final By btnAlert = By.id("show-alert");
    public static final By btnConfi = By.id("show-confirm");
    public static final By btnProm = By.id("show-prompt");
    public static final By btnNewPes = By.id("open-window");
    public static final By btnconfres = By.xpath("//button[text()='Confirmar reserva']");
    public static final By resultText = By.id("alert-result");

    // Locators de Formulario de Reserva
    public static final By selectExperience = By.id("booking-activity");
    public static final By inputDate = By.id("booking-date");
    public static final By inputEmail = By.id("booking-email");
    public static final By inputTickets = By.id("booking-tickets");
    public static final By statusMessage = By.id("booking-result");
    public static final By btnReset = By.xpath("//button[text()='Restablecer']");

    // Locators para Descargas y Reportes
    public static final String downloadFolderPath = System.getProperty("user.home") + "/Downloads";
    public static final By selectFormat = By.id("report-format");
    public static final By btnDownload = By.id("download-report");
    public static final By downloadNotification = By.id("download-result");

    // Locators para Carga de Archivos
    public static final By fileInput = By.id("upload-file");
    public static final By btnUpload = By.xpath("//button[contains(text(),'Subir archivo')]");
    public static final By responseContainer = By.id("upload-result");

    public static String mainTabHandle;

    // Locators para Authentication / Login
    public static final By userNameInput = By.id("login-username");
    public static final By passwordInput = By.id("login-password");
    public static final By ButtonEnter = By.xpath("//button[contains(@class,'button-primary') and text() ='Entrar']");
    // Mensajes de validación y confirmación
    public static final By loginResult = By.id("login-result");
    // Locators para carga
    // Locadores extraídos del DOM real de la app
    public static final By loadButton = By.id("load-element");
    public static final By loadTargetContainer = By.id("load-target");
    public static final By loadedDynamicButton = By.xpath("//div[@id='load-target']//button[contains(@id,'loaded-')]");

    // Locadores basados en los elementos del ejercicio
    public static final By inputField = By.xpath("//input[@type='text' or contains(@placeholder,'escribir')]");

    // Locadores interfaz "Campo diferido" y manipulación DOM
    public static final By enableFieldButton = By.id("enable-field");
    public static final By deferredInput = By.id("delayed-input");
    public static final By Btnremplazar = By.id("replace-element");
    public static final By Btnremove = By.id("remove-element");
    public static final By Btntarget = By.xpath("//p[@class='stale-target']");
    

    // Locadores tarjeta "04 / API HTTP - Latencia y errores"
    public static final By delayInput = By.id("simulate-delay");
    public static final By httpStatusSelect = By.id("simulate-status");
    public static final By sendRequestButton = By.xpath("//button[contains(@class,'button-secondary') and text()='Enviar petición']");
    public static final By responseStatusResult = By.id("simulate-result");

    // Locadores del catálogo y carrito
    public static final By productsList = By.xpath("//div[@id='product-grid']/article"); // Ajusta la clase según el DOM de tu app
    public static final By productNameLocator = By.xpath(".//h3");
    public static final By productPriceLocator = By.xpath(".//div[@class='product-bottom']/strong");
    public static final By cartBadge = By.id("cart-count");
    public static final By cartTotal = By.id("cart-total");
    // Campos de entrada y botón de confirmación
    public static final By addressInput = By.id("checkout-address");
    public static final By cardNumberInput = By.id("checkout-card");
    public static final By confirmOrderButton = By.xpath("//button[contains(@class,'button-primary') and text() ='Confirmar pedido']");
    // Mensajes de validación y confirmación
    public static final By errorMessage = By.xpath("//*[contains(@class,'error') or contains(@class,'alert-danger')]");
    public static final By confirmationMessage = By.xpath("//*[contains(text(),'Pedido confirmado') or contains(text(),'Gracias por su compra') or contains(@class,'success')]");

    // Locadores - Controles Estándar y Dinámicos
    public static final By nativeCategorySelect = By.id("native-select");
    public static final By customRegionDropdownButton = By.id("custom-select-trigger");
    public static final By selectsStatusMessage = By.id("select-result");
    public static final By addDynamicOptionsButton = By.id("add-choice");
    public static final By dynamicStatusMessage = By.xpath("//*[contains(text(),'Controles dinámicos añadidos')]");

    // Locadores - iFrame
    public static final By iframeLocator = By.tagName("iframe");
    public static final By iframeSubmitButton = By.xpath("//button[contains(normalize-space(),'Enviar desde iFrame')]");
    public static final By iframeInput = By.cssSelector("input");
    public static final By iframeNameInput = By.id("iframe-name");
    public static final By iframeResult = By.id("iframe-result");

    // Locadores - Shadow DOM
    public static final By shadowHostLocator = By.id("shadow-component");

    // Locadores - Modales
    public static final By btnModal = By.id("open-modal");
    public static final By btnFondo = By.id("behind-modal");
    public static final By btnGuardarCerrar = By.id("save-modal");
    public static final By modalHeader = By.id("demo-modal");
    public static final By inputModal = By.id("modal-input");
    public static final By txtResultadoGuardado = By.id("modal-result");
}


