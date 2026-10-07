Feature: Native Browser Dialogs - Alert, Confirm, and Prompt

  Background:
    Given the user navigates to the main page "Alertas y ventanas"
    When the user selects the "Alertas y ventanas" from the store main page

  @NativeDialogs
  Scenario Outline: Validate browser native dialogs actions and messages

    And the user click on the button "<button_name>"
    And the user interacts with the dialog using action "<action>" and input "<input_text>"
    Then the status message should display "<expected_message>"

    Examples:
      | button_name   | action  | input_text | expected_message |
      | Abrir alert   | accept  | N/A        | N/A              |
      | Abrir confirm | accept  | N/A        | Confirmado.      |
      | Abrir confirm | dismiss | N/A        | Cancelado.       |
      | Abrir prompt  | accept  | Laila      | Hola, Laila      |
  @NewTab
  Scenario Outline: Validate opening new tab and performing reservation flow

    And the user click on the button "Abrir nueva pestaña"
    Then a new tab should open with the path "/window-demo"
    And the user switches to the new reservation tab
    When the user fills the reservation form with experience "<experience>", date "<date>", email "<email>", and tickets "<tickets>"
    And the user click on the button "Confirmar reserva"
    Then the reservation status message should display "<expected_message>"
    And the original tab should remain accessible

    Examples:
      | experience             | date       | email                 | tickets | expected_message                                                                            |
      | Taller creativo · 25 € | 15/10/2026 | lailamrn19@gmail.com  | 3       | Reserva confirmada: 3 entrada(s) para 2026-10-15. Confirmación enviada a lailamrn19@gmail.com. |
      | N/A                    | N/A        | N/A                   | N/A     | Completa todos los campos con datos válidos.                                                |

  @DownloadReport
  Scenario Outline: Validate downloading reports in different formats

    When the user requests to download the report in "<format>" format
    Then the downloaded file name should be "<file_name>"
    And the downloaded file content should contain "<expected_content>"
    And the page should display a notification message with "<file_name>"

    Examples:
      | format | file_name      | expected_content         |
      | TXT    | reporte-qa.txt | QA-001                   |
      | CSV    | reporte-qa.csv | header and data row      |
  @FileUpload
  Scenario Outline: Validate file upload behavior with different criteria
    And the user uploads a file named "<fileName>" with size "<fileSize>"
    Then the response status should be "<expectedResult>"

    Examples:
      | fileName       | fileSize | expectedResult                                      |
      | test-file.pdf  | 2 MB     | Archivo recibido. test-file.pdf (2097152 bytes)     |
      |                | 0 MB     | Selecciona un archivo                               |
      | heavy-file.zip | 6 MB     | Archivo demasiado grande (máximo 5 MB).             |