Feature: Carga de contenido dinámico bajo demanda

  Background:
    Given the user navigates to the main page "Carga dinámica"
  @dynamic_loading
  Scenario: Cargar contenido dinámico, verificar el indicador de espera y la interacción con el botón cargado
    When the user selects the "Carga dinámica" from the store main page
    And the user requests to load dynamic content
    Then a loading indicator should be displayed during 4 seconds
    And a new button with a dynamic ID should appear
    When the user clicks the newly loaded button
    Then the interaction confirmation message should be displayed
  @delayed_enable
  Scenario: Escribir en un campo de texto tras ser habilitado después de 3 segundos
    When the user selects the "Carga dinámica" from the store main page
    And the input field "Texto de prueba" should be disabled initially
    When the user clicks the "Habilitar campo" button
    Then the input field should become enabled after 3 seconds
    When the user types "Prueba QA" into the input field
    Then the input field should contain the text "Prueba QA"

  @node_management
  Scenario: Actualizar versión y eliminar elemento del DOM
    When the user selects the "Carga dinámica" from the store main page
    And the user clicks the "Reemplazar" button
    Then a new node with an incremented version should be displayed
    And the previous node reference should be disconnected from the DOM
    When the user clicks the "Eliminar" button
    Then the target node should be removed from the DOM

  # Criterios 1 y 2: Latencia válida (0 - 5000 ms) y coincidencia de código de estado HTTP
  @API-HTTP
  Scenario Outline: Validar respuestas exitosas y de error simuladas con latencias válidas
    When the user selects the "Carga dinámica" from the store main page
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
  Scenario Outline: Validar restricciones de entrada para retrasos fuera de rango (0 - 5000 ms)
    When the user selects the "Carga dinámica" from the store main page
    And the user sets the delay to "<invalid_delay>" ms
    Then the delay field should display the validation message "<validation_message>"

    Examples:
      | invalid_delay | validation_message                        |
      | -100          | El valor debe ser superior o igual a 0    |
      | 6000          | El valor debe ser inferior o igual a 5000 |