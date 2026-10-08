package com.pages;

import com.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

  protected WebDriver driver;
  protected WebDriverWait wait;

  public BasePage() {
    this.driver = DriverManager.getDriver();
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  public void navigateTo(String url) {
    driver.get(url);
  }

  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  public void navigateBack() {
    driver.navigate().back();
  }

  public void click(By locator) {
    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    scrollToElement(element);

    try {
      wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    } catch (Exception e) {
      // Fallback: Si el clic nativo falla por un elemento superpuesto o fuera de vista, ejecuta el clic por JavaScript
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
  }

  public void type(By locator, String text) {
    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    element.clear();
    element.sendKeys(text);
  }

  public String getText(By locator) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
  }

  public boolean isDisplayed(By locator) {
    try {
      return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    } catch (TimeoutException e) {
      return false;
    }
  }

  public void scrollToElement(WebElement element) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
  }
  public void scrollToBottom() {
    ((JavascriptExecutor) driver).executeScript("window.scrollTo(100, document.body.scrollHeight);");
  }
  public void scrollToLastDynamicElement() {
    // Localiza el último label u opción añadida en la lista
    By lastOptionLocator = By.xpath("(//div[@id='choice-list']//label)[last()]");
    WebElement lastElement = wait.until(ExpectedConditions.presenceOfElementLocated(lastOptionLocator));

    // Forzar el scroll hasta ese último elemento
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", lastElement);
  }


  public void doubleClick(By locator) {
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    new Actions(driver).doubleClick(element).perform();
  }

  public void rightClick(By locator) {
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    new Actions(driver).contextClick(element).perform();
  }

  // ==========================================
  // MÉTODOS OPTIMIZADOS PARA ALERTAS (JavaScript)
  // ==========================================

  public Alert waitForAlert() {
    return wait.until(ExpectedConditions.alertIsPresent());
  }

  public String acceptAlertAndGetText() {
    Alert alert = waitForAlert();
    String text = alert.getText();
    alert.accept();
    return text;
  }

  public String dismissAlertAndGetText() {
    Alert alert = waitForAlert();
    String text = alert.getText();
    alert.dismiss();
    return text;
  }

  public void sendKeysToAlertAndAccept(String text) {
    Alert alert = waitForAlert();
    alert.sendKeys(text);
    alert.accept();
  }

  // ==========================================
  // MÉTODOS OPTIMIZADOS PARA VENTANAS / PESTAÑAS
  // ==========================================

  public String getNewWindowOrTabText() {
    String originalWindow = driver.getWindowHandle();
    Set<String> allWindows = driver.getWindowHandles();

    for (String windowHandle : allWindows) {
      if (!windowHandle.equals(originalWindow)) {
        driver.switchTo().window(windowHandle);
        break;
      }
    }

    String contentText = "";

    // Reducimos temporalmente el timeout para no congelar la ejecución si no hay H1/Header
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    try {
      contentText = driver.findElement(By.id("sampleHeading")).getText();
    } catch (Exception e) {
      // Si es un pop-up como 'New Window Message' lee el texto del body
      contentText = driver.findElement(By.tagName("body")).getText();
    } finally {
      // Restauramos el tiempo de espera por defecto
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    driver.close();
    driver.switchTo().window(originalWindow);

    return contentText.trim();
  }
  protected WebElement waitForVisibility(By locator) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  protected WebElement waitForClickable(By locator) {
    return wait.until(ExpectedConditions.elementToBeClickable(locator));
  }

  protected void hoverOver(WebElement element) {
    scrollToElement(element);

    // Eventos sintéticos JS para asegurar reaccion en marcos React
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String jsHover =
            "var mouseOver = document.createEvent('MouseEvents');" +
                    "mouseOver.initEvent('mouseover', true, false);" +
                    "arguments[0].dispatchEvent(mouseOver);" +
                    "var mouseEnter = document.createEvent('MouseEvents');" +
                    "mouseEnter.initEvent('mouseenter', true, false);" +
                    "arguments[0].dispatchEvent(mouseEnter);";
    js.executeScript(jsHover, element);

    // 4. Mover el cursor físico como respaldo
    Actions actions = new Actions(driver);
    actions.moveToElement(element).perform();
  }
  public void scrollToTop() {
    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);"); }

  // Metodo para realizar Drag and Drop seguro entre dos WebElements
  public void dragAndDrop(WebElement source, WebElement target) {
    scrollToElement(source);

    int yOffset = target.getLocation().getY() - source.getLocation().getY();

    // Si el movimiento es hacia abajo (ej. One a Three), sumamos más píxeles
    // para sobrepasar el centro del elemento destino.
    if (yOffset > 0) {
      yOffset += (target.getSize().getHeight() / 2) + 10;
    } else {
      yOffset -= (target.getSize().getHeight() / 2) + 10;
    }

    Actions actions = new Actions(driver);
    actions.clickAndHold(source)
            .pause(java.time.Duration.ofMillis(300))
            .moveToElement(target, 0, yOffset > 0 ? 15 : -15)
            .pause(java.time.Duration.ofMillis(300))
            .release()
            .build()
            .perform();
  }
  public void dragAndDropJS(WebElement source, WebElement target) {
    scrollToElement(source);

    String script =
            "var source = arguments[0];" +
                    "var target = arguments[1];" +
                    "var dataTransfer = new DataTransfer();" +

                    "var dragStartEvent = new DragEvent('dragstart', {" +
                    "    bubbles: true, cancelable: true, dataTransfer: dataTransfer" +
                    "});" +
                    "source.dispatchEvent(dragStartEvent);" +

                    "var dragOverEvent = new DragEvent('dragover', {" +
                    "    bubbles: true, cancelable: true, dataTransfer: dataTransfer" +
                    "});" +
                    "target.dispatchEvent(dragOverEvent);" +

                    "var dropEvent = new DragEvent('drop', {" +
                    "    bubbles: true, cancelable: true, dataTransfer: dataTransfer" +
                    "});" +
                    "target.dispatchEvent(dropEvent);" +

                    "var dragEndEvent = new DragEvent('dragend', {" +
                    "    bubbles: true, cancelable: true, dataTransfer: dataTransfer" +
                    "});" +
                    "source.dispatchEvent(dragEndEvent);";

    ((JavascriptExecutor) driver).executeScript(script, source, target);
  }
  /**
   * Clic robusto: Intenta hacer clic nativo tras un scroll; si se intercepta, usa JavaScript.
   */
  public void safeClick(By locator) {
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    scrollToElement(element);
    try {
      element.click();
    } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
  }

  public void safeClick(WebElement element) {
    wait.until(ExpectedConditions.elementToBeClickable(element));
    scrollToElement(element);
    try {
      element.click();
    } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
  }
  /**
   * Obtiene el mensaje de validación nativo HTML5 de un input.
   */
  public String getHtml5ValidationMessage(By locator) {
    try {
      WebElement element = driver.findElement(locator);
      String message = (String) ((JavascriptExecutor) driver)
              .executeScript("return arguments[0].validationMessage;", element);
      return message != null ? message.trim() : "";
    } catch (Exception e) {
      return "";
    }
  }

  /**
   * Retorna la lista de elementos localizados tras esperar su presencia en el DOM.
   */
  public List<WebElement> findElementsWithWait(By locator) {
    return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
  }
  /**
   * Espera a que un elemento contenga un texto específico y devuelve su contenido.
   */
  public String getTextWhenContains(By locator, String expectedText, int timeoutInSeconds) {
    WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    customWait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
    return driver.findElement(locator).getText().trim();
  }
  public void selectByVisibleText(By locator, String text) {
    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    new Select(element).selectByVisibleText(text);
  }

  public void typeIfPresent(By locator, String text) {
    if (text == null || text.equalsIgnoreCase("N/A")) {
      return; // No hace nada, deja el campo como está
    }

    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    if (text.equalsIgnoreCase("EMPTY") || text.equalsIgnoreCase("VACIO")) {
      element.clear();
      // Truco adicional por si `.clear()` no dispara el evento onChange en algunos inputs de React/Angular
      element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
    } else {
      element.clear();
      element.sendKeys(text);
    }
  }

}