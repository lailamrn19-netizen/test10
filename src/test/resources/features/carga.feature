Feature: Carga de contenido dinámico bajo demanda

  Background:
    Given the user navigates to the main page "Carga dinámica"
    When the user selects the "Carga dinámica" from the store main page
  # --- Criterio 1:Como usuario, quiero cargar contenido bajo demanda para verlo cuando esté disponible ---
  @dynamic_loading
  Scenario: Load dynamic content, check the loading indicator, and interact with the “Loaded” button
    And the user requests to load dynamic content
    Then a loading indicator should be displayed during 4 seconds
    And a new button with a dynamic ID should appear
    When the user clicks the newly loaded button
    Then the interaction confirmation message should be displayed
  # --- Criterio 2:Como usuario, quiero escribir en un campo cuando termine de habilitarse ---
  @delayed_enable
  Scenario: Type in a text field after it becomes active following a 3-second delay
    And the input field "Texto de prueba" should be disabled initially
    When the user clicks the "Habilitar campo" button
    Then the input field should become enabled after 3 seconds
    When the user types "Prueba QA" into the input field
    Then the input field should contain the text "Prueba QA"
  # --- Criterio 3:Como usuario, quiero ver un elemento actualizado o retirado según la acción elegida ---
  @node_management
  Scenario Outline: Update the version and remove an element from the DOM
    And the user click the "Reemplazar" button <clicks> times
    Then the node version should be updated to "<expectedVersion>"
    And the previous node reference should be disconnected from the DOM
    When the user clicks the "Eliminar" button
    Then the target node should be removed from the DOM
    Examples:
      | clicks | expectedVersion |
      | 1      | versión 2       |
      | 3      | versión 4       |
  # --- Criterios 4: Como cliente de la API, quiero simular latencias y fallos para comprobar la gestión de respuestas---
  @API-HTTP
  Scenario Outline: Validate simulated successful and error responses with valid latencies
    And the user sets the delay to "<delay>" ms
    And the user selects the HTTP status code "<status>"
    And the user clicks the "Enviar petición" button
    Then the response time should take at least "<delay>" ms
    And the displayed response status should contain "<expected_message>"

    Examples:
      | delay | status           | expected_message                        |
      | 1500  | 200 OK           | HTTP 200: Respuesta correcta. (1500 ms) |
      | 1000  | 400 Bad Request  | Error simulado.                         |
      | 500   | 404 Not Found    | Error simulado.                         |
      | 2000  | 500 Server Error | Error simulado.                         |
      | 1200  | 503 Unavailable  | Error simulado.                         |

  @inv-API-HTTP
  # Criterio 3: Validación HTML5 en el campo de entrada cuando los valores están fuera de rango
  Scenario Outline: Validate input constraints for delays outside the range (0–5,000 ms)
    And the user sets the delay to "<invalid_delay>" ms
    Then the delay field should display the validation message "<validation_message>"

    Examples:
      | invalid_delay | validation_message                        |
      | -100          | El valor debe ser superior o igual a 0    |
      | 6000          | El valor debe ser inferior o igual a 5000 |