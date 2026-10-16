package com.steps;

import com.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

  @Before
  public void setUp() {
    DriverManager.getDriver();
  }

  @After
  public void tearDown(Scenario scenario) {
    // Si la prueba falla, tomamos la evidencia visual antes de cerrar el navegador
    if (scenario.isFailed()) {
      try {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
          byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
          scenario.attach(screenshot, "image/png", "Fallo en el escenario: " + scenario.getName());
        }
      } catch (Exception e) {
        System.err.println("No se pudo adjuntar la captura de pantalla del error: " + e.getMessage());
      }
    }

    // Cerramos el driver de forma limpia para liberar recursos
    DriverManager.quitDriver();
  }
}