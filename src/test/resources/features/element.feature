Feature: Complex DOM Elements - Selects, Dropdowns, and Dynamic Controls

  Background:
    Given the user navigates to the main page "Elementos complejos"

  @FlujoCompleto
  Scenario Outline: Validate full selection of native category and custom region
    When the user selects the "Elementos complejos" from the store main page
    And the user selects the category "<category>" from the native select
    And the user clicks the "custom region dropdown"
    And the user selects the region "<region>" from the custom dropdown
    Then the selected category should be "<category>"
    And the region dropdown button should display "<region>"
    And the status message should contain "<region>"

    Examples:
      | category   | region |
      | Audio      | Norte  |
      | Fotografía | Centro |
      | Viaje      | Sur    |

  @DynamicControls
  Scenario Outline: Validate dynamic checkboxes and radio buttons creation
    When the user selects the "Elementos complejos" from the store main page
    And the user clicks the "Añadir opciones dinámicas" button <times> times
    Then the dynamic status message should display "<expected_status>"
    When the user selects the dynamic checkbox "<checkbox_label>"
    And the user selects the dynamic radio button "<radio_label>"
    Then the dynamic checkbox "<checkbox_label>" should be selected
    And the dynamic radio button "<radio_label>" should be selected

    Examples:
      | times | expected_status                         | checkbox_label    | radio_label      |
      | 1     | Controles dinámicos añadidos (grupo 1). | Opción dinámica 1 | Envío dinámico 1 |
      | 2     | Controles dinámicos añadidos (grupo 2). | Opción dinámica 2 | Envío dinámico 2 |
      | 4     | Controles dinámicos añadidos (grupo 4). | Opción dinámica 4 | Envío dinámico 4 |
  @iframe
  Scenario Outline: Enviar nombre en formulario dentro de un iFrame
    When the user selects the "Elementos complejos" from the store main page
    And the user enters the name "<name>" in the iframe input
    And the user clicks the "Enviar desde iFrame"
    Then a greeting message "<expected_greeting>" should be displayed inside the iframe

    Examples:
      | name  | expected_greeting         |
      | Laila | Hola, Laila               |
      |       | Completa este campo       |

  @shadow-DOM
  Scenario Outline: Validar un código en un componente encapsulado Shadow DOM
    When the user selects the "Elementos complejos" from the store main page
    When the user enters the secret code "<code_input>" in the shadow DOM input
    And the user clicks the "Validar código"
    Then the shadow DOM result message should display "<expected_message>"

    Examples:
      | code_input | expected_message          |
      | 15         | Código recibido: 15       |
      | Laila      | Código recibido: Laila    |
      |            | Código recibido: (vacío)  |
  @cap-modal
  Scenario Outline: Validar el ingreso de datos en el diálogo bloqueante (Capa Modal)
    Given the user selects the "Elementos complejos" from the store main page
    When the user clicks the "Abrir modal"
    Then the modal "Modal de prueba" should be displayed
    When the user enters "<dato>" in the modal input
    And the user clicks the "Guardar y cerrar"
    Then the modal should be closed
    And the saved result message should display "Guardado: <resultado_esperado>"
    When the user clicks the "Acción de fondo"
    Then the action result message should display "<resultado_esperado>"


    Examples:
      | dato | resultado_esperado |
      | hola | hola               |
      |      | (vacío)            |
