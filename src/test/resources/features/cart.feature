Feature: this test for Catálogo y carrito
  Como comprador
  Quiero ver los productos disponibles y añadirlos al carrito
  Para preparar mi compra

  Background:
    Given the user navigates to the main page "carito"
    When the user selects the "Catálogo y carrito" card from the store main page

  @scenario1
  Scenario: Verify that the catalog displays all four products with their names and prices
    Then the catalog should display 4 products
    And each product should have a visible name and price

  @scenario2
  Scenario Outline: Adding a product increments the cart counter and updates the total
    And the user adds the product "<productName>" with Count "<quantity>" to the cart
    Then the cart counter should display "<expectedCount>"
    And the total cart amount should be "<expectedTotal>"

    Examples:
      | productName        | quantity | expectedCount | expectedTotal |
      | Auriculares Studio | 1        | 1             | 89,90 €       |
      | Mochila Urbana     | 1        | 1             | 64,00 €       |

  @scenario3
  Scenario Outline: You can add the same product more than once
    And the user adds the product "<productName>" with Count "<quantity>" to the cart
    Then the cart counter should display "<expectedCount>"
    And the total cart amount should be "<expectedTotal>"

    Examples:
      | productName        | quantity | expectedCount | expectedTotal |
      | Mochila Urbana     | 4        | 4             | 256,00 €      |
      | Reloj Minimal      | 2        | 2             | 258,00 €      |
  # --- Criterio 3: Quitar productos del carrito ---
  @remove_product
  Scenario Outline: Removing an item from the cart updates the quantity and the total amount
    And the user adds the product "<productName>" with Count "<quantity>" to the cart
    And the user removes the product "<productName>" from the cart
    Then the cart counter should display "<expectedCount>"
    And the total cart amount should be "<expectedTotal>"

    Examples:
      | productName        | quantity | expectedCount | expectedTotal |
      | Mochila Urbana     | 2        | 1             | 64,00 €       |
      | Auriculares Studio | 1        | 0             | 0,00 €        |

  # --- Criterio 4: Validaciones al confirmar pedido ---
  @checkout_validation
  Scenario Outline: Prevent the purchase if the cart is empty or the shipping and payment information is invalid
    And the user adds the product "<productName>" with Count "<quantity>" to the cart
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
  Scenario: With a valid card, the order is confirmed and the shopping cart is emptied
    And the user adds the product "Auriculares Studio" with Count "1" to the cart
    And the user enters the shipping address "Avenida de la Constitución 15"
    And the user enters the card number "4242424242424242"
    And the user attempts to confirm the order
    Then a confirmation message should be displayed
    And the cart counter should display "0"
    And the total cart amount should be "0,00 €"
  # --- Criterio 6:Probar si puede anadir dos productos differentes en mismo tiempo  ---
  @checkout_multiple_items
  Scenario Outline: Add two products to the cart and verify total amount
    And the user adds the product "<firstProduct>" with Count "1" to the cart
    And the user adds the product "<secondProduct>" with Count "1" to the cart
    Then the cart counter should display "<expectedCount>"
    And the total cart amount should be "<expectedTotal>"

    Examples:
      | firstProduct       | secondProduct | expectedCount | expectedTotal |
      | Auriculares Studio | Reloj Minimal | 2             | 218,90 €      |


