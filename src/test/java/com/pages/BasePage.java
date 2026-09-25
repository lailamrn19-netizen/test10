package com.pages;

import com.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    scrollToElement(element);
    element.click();
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
  public void scrollToElement(By locator) {
    WebElement element = driver.findElement(locator);
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
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
}