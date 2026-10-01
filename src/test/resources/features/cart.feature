Feature: this test for Catálogo y carrito
  Como comprador
  Quiero ver los productos disponibles y añadirlos al carrito
  Para preparar mi compra

  Background:
    Given the user navigates to the main page "carito"

  @scenario1
  Scenario: Verificar que el catálogo muestra los cuatro productos con nombre y precio
    When the user selects the "Catálogo y carrito" card from the store main page
    Then the catalog should display 4 products
    And each product should have a visible name and price

  @scenario2
  Scenario Outline: Añadir un producto incrementa el contador del carrito y actualiza el total
    When the user selects the "Catálogo y carrito" card from the store main page
    And the user adds the product "<productName>" with Count "<quantity>" to the cart
    Then the cart counter should display "<expectedCount>"
    And the total cart amount should be "<expectedTotal>"

    Examples:
      | productName        | productPrice | quantity | expectedCount | expectedTotal |
      | Auriculares Studio | 89,90 €      | 1        | 1             | 89,90 €       |
      | Mochila Urbana     | 64,00 €      | 1        | 1             | 64,00 €       |

  @scenario3
  Scenario Outline: Se puede añadir el mismo producto más de una vez
    When the user selects the "Catálogo y carrito" card from the store main page
    And the user adds the product "<productName>" with Count "<quantity>" to the cart
    Then the cart counter should display "<expectedCount>"
    And the total cart amount should be "<expectedTotal>"

    Examples:
      | productName        | productPrice | quantity | expectedCount | expectedTotal |
      | Mochila Urbana     | 64,00 €      | 4        | 4             | 256,00 €      |
      | Reloj Minimal      | 129,00 €     | 2        | 2             | 258,00 €      |
  # --- Criterio 3: Quitar productos del carrito ---
  @remove_product
  Scenario Outline: Quitar un producto del carrito actualiza el contador y el importe total
    When the user adds the product "<productName>" with Count "<quantity>" to the cart
    And the user removes the product "<productName>" from the cart
    Then the cart counter should display "<expectedCount>"
    And the total cart amount should be "<expectedTotal>"

    Examples:
      | productName        | quantity | expectedCount | expectedTotal |
      | Mochila Urbana     | 2        | 1             | 64,00 €       |
      | Auriculares Studio | 1        | 0             | 0,00 €        |

  # --- Criterio 4: Validaciones al confirmar pedido ---
  @checkout_validation
  Scenario Outline: Impedir la compra si el carrito está vacío o los datos de envío y pago son inválidos
    When the user adds the product "<productName>" with Count "<quantity>" to the cart
    And the user enters the shipping address "<address>"
    And the user enters the card number "<cardNumber>"
    And the user attempts to confirm the order
    Then an error message "<errorMessage>" should be displayed
    And the order should not be processed

    Examples:
      | productName        | quantity | address             | cardNumber       | errorMessage                                         |
      |                    | 0        | Calle Mayor 123     | 4242424242424242 | Añade al menos un producto.                          |
      | Auriculares Studio | 1        | Calle 1             | 4242424242424242 | Aumenta la longitud del texto a 8 caracteres como mínimo (actualmente, el texto tiene 7 caracteres). |
      | Mochila Urbana     | 1        | Calle Mayor 123     | 1234             | La tarjeta debe tener 16 dígitos válidos.            |

  # --- Criterio 5: Confirmación de compra exitosa ---
  @checkout_success
  Scenario: Confirmar el pedido con éxito vacía el carrito y muestra la confirmación
    Given the user adds the product "Auriculares Studio" with Count "1" to the cart
    And the user enters the shipping address "Avenida de la Constitución 15"
    And the user enters the card number "4242424242424242"
    When the user attempts to confirm the order
    Then a confirmation message should be displayed
    And the cart counter should display "0"
    And the total cart amount should be "0,00 €"

