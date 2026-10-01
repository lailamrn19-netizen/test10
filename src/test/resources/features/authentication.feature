Feature: Acceso de usuario y autenticación

  Background:
    Given the user navigates to the main page "Autenticación"

  # Criterio 1: Los campos vacíos muestran un error inmediato
  @empty_fields
  Scenario Outline: Iniciar sesión con campos vacíos muestra un error inmediato
    When the user selects the "Autenticación" from the store main page
    And the user enters username "<username>" and password "<password>"
    And the user clicks the login button
    Then an immediate error message "<errorMessage>" should be displayed

    Examples:
      | username | password    | errorMessage                          |
      |          |             | Completa usuario y contraseña.        |
      | admin    |             | Completa usuario y contraseña.        |
      |          | password123 | Completa usuario y contraseña.        |

  # Criterio 2: admin / password123 permite el acceso tras dos segundos
  @successful_login
  Scenario: Iniciar sesión con credenciales válidas permite el acceso
    When the user selects the "Autenticación" from the store main page
    And the user enters username "admin" and password "password123"
    And the user clicks the login button
    Then a success message "Sesión iniciada correctamente." should be displayed

  # Criterio 3: locked devuelve usuario bloqueado y las credenciales erróneas muestran un error
  @invalid_login
  Scenario Outline: Iniciar sesión con credenciales bloqueadas o incorrectas muestra un error
    When the user selects the "Autenticación" from the store main page
    And the user enters username "<username>" and password "<password>"
    And the user clicks the login button
    Then an auth error message "<errorMessage>" should be displayed

    Examples:
      | username | password    | errorMessage                         |
      | locked   | password123 | Usuario bloqueado.                   |
      | admin    | wrongpass   | Usuario o contraseña incorrectos.    |
      | unknown  | password123 | Usuario o contraseña incorrectos.    |