Feature: Login

  Background:
    Given the user navigates to the main page

  @scenario1
  Scenario Outline: Open side menu and verify options are displayed
    When the user selects the "<category_card>" card from the main page
    And the user clicks on the element option "<element_name>"
    Then the user should be redirected to the "<expected_url>" page

    Examples:
      | category_card| element_name                 | expected_url         |
      | Elements     | Text Box                     | /text-box            |
      | Elements     | Check Box                    | /checkbox            |
      | Elements     | Radio Button                 | /radio-button        |
      | Elements     | Web Tables                   | /webtables           |
      | Elements     | Buttons                      | /buttons             |
      | Elements     | Links                        | /links               |
      | Elements     | Broken Links - Images        | /broken              |
      | Elements     | Upload and Download          | /upload-download     |
      | Elements     | Dynamic Properties           | /dynamic-properties  |

  @scenario2
  Scenario: Verify Text Box form submission with valid data
    When the user selects the "Elements" card from the main page
    And the user clicks on the element option "Text Box"
    And the user enters the full name "Laila"
    And the user enters the email "laila@example.com"
    And the user enters the current address "Tangier, Morocco"
    And the user enters the permanent address "Tangier, Morocco"
    And the user clicks the submit button
    Then the output details should be displayed below the form

  @scenario3
  Scenario Outline: Verify Check Box selection and result display
    When the user selects the "Elements" card from the main page
    And the user clicks on the element option "Check Box"
    And the user expands all folders
    And the user selects the "<element>" checkboxes
    Then the result area should display "<elemento>" as selected

    Examples:
      | element                      | elemento                     |
      | Notes                        | notes                        |
      | React                        | react                        |
      | Public                       | public                       |
      | Excel File.doc               | excelFile                    |
      | Commands,Classified          | commands classified          |

  @scenario4
  Scenario Outline: Verify Radio Button selection and result display
    When the user selects the "Elements" card from the main page
    And the user clicks on the element option "Radio Button"
    And the user selects the "<option>" radio button
    Then the result area should display "<expected_result>" as selected
    Examples:
      | option     | expected_result |
      | Yes        | Yes             |
      | Impressive | Impressive      |

  @scenario5
  Scenario Outline: Verify Web Tables selection and result display
    When the user selects the "Elements" card from the main page
    And the user clicks on the element option "Web Tables"
    And the user clicks on the "Add" button
    And the user fills the registration form with "<firstName>","<lastName>","<email>","<age>","<salary>", and "<department>"
    And the user clicks on the submit button
    Then the table should display the user with email "<email>" in the list

    Examples:
      | firstName | lastName |  email            |age  | salary | department |
      | Laila     | Mrini    |  laila@test.com   |28   | 5000   | Quality    |
  @scenario6
  Scenario Outline: Verify searching for a record in Web Tables
    When the user selects the "Elements" card from the main page
    And the user clicks on the element option "Web Tables"
    And the user searches for "<keyword>" in the search box
    Then the table should contain "<keyword>" in the results

    Examples:
      | keyword |
      | Cierra  |
      | Legal   |
      | Gentry  |
  @scenario7
  Scenario Outline: Verify click on the button
    When the user selects the "Elements" card from the main page
    And the user clicks on the element option "Buttons"
    And the user performs a "<action_type>" click on the "<button_name>" button
    Then the message "<expected_message>" should be displayed

    Examples:
      | action_type | button_name     | expected_message              |
      | double      | Double Click Me | You have done a double click  |
      | right       | Right Click Me  | You have done a right click   |
      | dynamic     | Click Me        | You have done a dynamic click |
  @scenario8
  Scenario Outline: Verify links that open in a new tab
    When the user selects the "Elements" card from the main page
    And the user clicks on the element option "Links"
    And the user clicks on the "<link_name>" link
    Then a new tab should be opened with URL containing "<expected_url>"

    Examples:
      | link_name  | expected_url |
      | Home       | demoqa.com   |
      | HomeUP0k   | demoqa.com   |
  @scenario9
  Scenario Outline: Verify links that send API calls
    When the user selects the "Elements" card from the main page
    And the user clicks on the element option "Links"
    And the user clicks on the "<link_name>" link
    Then the response message should display status code "<status_code>" and status text "<status_text>"

    Examples:
      | link_name    | status_code | status_text        |
      | Created      | 201         | Created            |
      | No Content   | 204         | No Content         |
      | Moved        | 301         | Moved Permanently  |
      | Bad Request  | 400         | Bad Request        |
      | Unauthorized | 401         | Unauthorized       |
      | Forbidden    | 403         | Forbidden          |
      | Not Found    | 404         | Not Found          |

  @scenario10
  Scenario: Verify valid image, broken image, valid link and broken link
    When the user selects the "Elements" card from the main page
    And the user clicks on the element option "Broken Links - Images"
    Then the valid image should be displayed correctly
    And the broken image should not be displayed correctly
    When the user clicks on the "Click Here for Valid Link" link
    Then the user should be redirected to the main page
    When the user navigates back
    And the user clicks on the "Click Here for Broken Link" link
    Then the response status code should be "500"

  @scenario11
  Scenario: Verify file download and file upload functionality
    When the user selects the "Elements" card from the main page
    And the user clicks on the element option "Upload and Download"
    And the user clicks on the download button
    Then the file "sampleFile.jpeg" should be downloaded
    When the user uploads the file "sampleFile.jpeg"
    Then the uploaded file path should display "C:\fakepath\sampleFile.jpeg"

  @scenario12
  Scenario: Verify elements with dynamic properties
    When the user selects the "Elements" card from the main page
    And the user clicks on the element option "Dynamic Properties"
    Then the button "Will enable 5 seconds" should be enabled after 5 seconds
    And the button "Color Change" should change text color
    And the button "Visible After 5 Seconds" should be displayed after 5 seconds

  @scenario-1
  Scenario: Open side menu Forms and verify options are displayed
    When the user selects the "Forms" card from the main page
    And the user clicks on the element option "Practice Form"
    Then the user should be redirected to the "/automation-practice-form" page
  @scenario-2
  Scenario: Fill and submit Student Registration Form successfully
    When the user selects the "Forms" card from the main page
    And the user clicks on the element option "Practice Form"
    And the user fills the registration form with the following details:
      | firstName | Laila                 |
      | lastName  | QA                    |
      | email     | laila@example.com     |
      | gender    | Female                |
      | mobile    | 1234567890            |
      | subject   | Computer Science      |
      | hobby     | Music                 |
      | address   | Main Street 123       |
    And the user submits the form
    Then the submission modal should be displayed with title "Thanks for submitting the form"
  @scenario2-1
  Scenario Outline: Open side menu Alert and verify options are displayed
    When the user selects the "<category_card>" card from the main page
    And the user clicks on the element option "<element_name>"
    Then the user should be redirected to the "<expected_url>" page

    Examples:
      | category_card              | element_name                 | expected_url           |
      | Alerts, Frame & Windows    | Browser Windows              | /browser-windows       |
      | Alerts, Frame & Windows    | Alerts                       | /alerts                |
      | Alerts, Frame & Windows    | Frames                       | /frames                |
      | Alerts, Frame & Windows    | Nested Frames                | /nestedframes          |
      | Alerts, Frame & Windows    | Modal Dialogs                | /modal-dialogs         |
  @scenario2-2
  Scenario Outline: Verify opening new tabs and windows in Browser Windows section
    When the user selects the "Alerts, Frame & Windows" card from the main page
    And the user clicks on the element option "Browser Windows"
    And the user click on the "<button_name>" buttons
    Then a new tab or window should open with the heading "<expected_heading>"

    Examples:
      | button_name         | expected_heading          |
      | New Tab             | This is a sample page     |
      | New Window          | This is a sample page     |
      | New Window Message  | Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.     |

  @scenario2-3
  Scenario Outline: Verify JavaScript Alerts handling in Alerts section
    When the user selects the "Alerts, Frame & Windows" card from the main page
    And the user clicks on the element option "Alerts"
    And the user triggers the alert for "<alert_type>"
    And the user interacts with the alert for "<alert_type>" with text "<input_text>"
    Then the alert message or result should be "<expected_result>"

    Examples:
      | alert_type | input_text | expected_result                                   |
      | simple     | N/A        | You clicked a button                              |
      | timer      | N/A        | This alert appeared after 5 seconds               |
      | confirm    | N/A        | You selected Cancel                               |
      | prompt     | Laila      | You entered Laila                                 |
  @scenario2-4
  Scenario Outline: Verificar el texto dentro del primer iframe
    When the user selects the "Alerts, Frame & Windows" card from the main page
    And the user clicks on the element option "Frames"
    And the user switches to the iframe "<frame_id>"
    Then the heading text inside iframe should be "<expected_heading>"
    And the user switches back to the main content
    Examples:
      | frame_id | expected_heading      |
      | frame1   | This is a sample page |
      | frame2   | This is a sample page |
  @scenario2-5
  Scenario: Verify text in parent and child nested frames
    When the user selects the "Alerts, Frame & Windows" card from the main page
    And the user clicks on the element option "Nested Frames"
    And the user switches to the parent frame
    Then the parent frame text should be "Parent frame"
    When the user switches to the child frame inside the parent frame
    Then the child frame text should be "Child Iframe"
    And the user switches back to the main content
  @scenario2-6
  Scenario Outline: Verify opening and closing small and large modal dialogs
    When the user selects the "Alerts, Frame & Windows" card from the main page
    And the user clicks on the element option "Modal Dialogs"
    And the user clicks on the "<modal_type>" modal button
    Then the modal window should display the title "<expected_title>"
    When the user closes the modal window
    Then the modal window should no longer be visible
    Examples:
      | modal_type  | expected_title |
      | Small modal | Small Modal    |
      | Large modal | Large Modal    |
  @scenario3-1
  Scenario Outline: Open side menu Widgets and verify options are displayed
    When the user selects the "<category_card>" card from the main page
    And the user clicks on the element option "<element_name>"
    Then the user should be redirected to the "<expected_url>" page

    Examples:
      | category_card              | element_name                 | expected_url           |
      | Widgets                    | Accordian                    | /accordian             |
      | Widgets                    | Auto Complete                | /auto-complete         |
      | Widgets                    | Date Picker                  | /date-picker           |
      | Widgets                    | Slider                       | /slider                |
      | Widgets                    | Progress Bar                 | /progress-bar          |
      | Widgets                    | Tabs                         | /tabs                  |
      | Widgets                    | Tool Tips                    | /tool-tips             |
      | Widgets                    | Menu                         | /menu                  |
      | Widgets                    | Select Menu                  | /select-menu           |
  @scenario3-2
  Scenario Outline: Verify expanding and collapsing accordian sections
    When the user selects the "Widgets" card from the main page
    And the user clicks on the element option "Accordian"
    When the user expands the accordian section "<section_heading>"
    Then the content for section "<section_heading>" should be visible
    And the content for section "<section_heading>" should start with "<expected_content>"

    Examples:
      | section_heading           | expected_content                                                |
      | What is Lorem Ipsum?      | Lorem Ipsum is simply dummy text of the printing and typesetting |
      | Where does it come from?  | Contrary to popular belief, Lorem Ipsum is not simply random text |
      | Why do we use it?         | It is a long established fact that a reader will be distracted  |
  @scenario3-3
  Scenario Outline: Verify auto complete functionality for multiple and single color selection
    When the user selects the "Widgets" card from the main page
    And the user clicks on the element option "Auto Complete"
    When the user types "<input_text>" in the "<field_type>" auto complete field
    And the user selects "<color_name>" from the auto complete suggestion list
    Then the selected color "<color_name>" should be displayed in the "<field_type>" field

    Examples:
      | field_type | input_text | color_name |
      | Multiple   | Red        | Red        |
      | Multiple   | Blue       | Blue       |
      | Single     | Green      | Green      |
  @scenario3-4
  Scenario Outline: Verify selecting date and time from date picker
    When the user selects the "Widgets" card from the main page
    And the user clicks on the element option "Date Picker"
    When the user inputs "<date_value>" in the "Select Date" field
    And the user inputs "<date_time_value>" in the "Date And Time" field
    Then the "Select Date" field should contain "<date_value>"
    And the "Date And Time" field should contain "<date_time_value>"

    Examples:
      | date_value | date_time_value            |
      | 09/23/2026 | September 23, 2026 2:23 PM |
      | 10/15/2026 | October 15, 2026 10:00 AM  |
  @scenario3-5
  Scenario Outline: Verify slider movement and value update
    When the user selects the "Widgets" card from the main page
    And the user clicks on the element option "Slider"
    When the user sets the slider value to "<target_value>"
    Then the slider value field should display "<target_value>"

    Examples:
      | target_value |
      | 50           |
      | 85           |
      | 10           |

  @scenario3-6
  Scenario: Verify progress bar starts and reaches completion
    When the user selects the "Widgets" card from the main page
    And the user clicks on the element option "Progress Bar"
    When the user clicks the "Start" button on the progress bar page
    Then the progress bar should reach "100%"
    And the "Reset" button should be visible

  @scenario3-7
  Scenario Outline: Verify switching tabs and displaying corresponding content
    When the user selects the "Widgets" card from the main page
    And the user clicks on the element option "Tabs"
    When the user clicks on the "<tab_name>" tab
    Then the tab panel for "<tab_name>" should be active
    And the content for tab "<tab_name>" should start with "<expected_content>"

    Examples:
      | tab_name | expected_content                                                  |
      | What     | Lorem Ipsum is simply dummy text of the printing and typesetting  |
      | Origin   | Contrary to popular belief, Lorem Ipsum is not simply random text |
      | Use      | It is a long established fact that a reader will be distracted    |
  @scenario3-8
  Scenario Outline: Verify tooltip text appears on hover for elements
    When the user selects the "Widgets" card from the main page
    And the user clicks on the element option "Tool Tips"
    When the user hovers over the "<element_name>" element
    Then a tooltip with text "<expected_tooltip>" should be displayed

    Examples:
      | element_name | expected_tooltip                   |
      | Button       | You hovered over the Button        |
      | Field        | You hovered over the text field    |
      | Contrary     | You hovered over the Contrary      |
      | 1.10.32      | You hovered over the 1.10.32       |
  @scenario3-9
  Scenario Outline: Verify sub-menu and sub-sub-menu items appear on hover
    When the user selects the "Widgets" card from the main page
    And the user clicks on the element option "Menu"
    When the user hovers over the main menu item "<main_item>"
    And the user hovers over the sub-menu item "<sub_item>"
    And the user clicks on the sub-sub-menu item "<sub_sub_item>"
    Then the sub-sub-menu item "<sub_sub_item>" action should be executed

    Examples:
      | main_item   | sub_item       | sub_sub_item   |
      | Main Item 2 | SUB SUB LIST » | Sub Sub Item 1 |
      | Main Item 2 | SUB SUB LIST » | Sub Sub Item 2 |
  @scenario3-9
  Scenario Outline: Verify option selection in Select Menu dropdowns
    When the user selects the "Widgets" card from the main page
    And the user clicks on the element option "Select Menu"
    When the user selects "<select_value>" from the "Select Value" dropdown
    And the user selects "<select_one>" from the "Select One" dropdown
    And the user selects "<old_style>" from the "Old Style Select Menu" dropdown
    And the user selects "<multiselect>" from the "Multiselect drop down" dropdown
    And the user selects "<standard_multi>" from the "Standard multi select" dropdown
    Then the selected value "<standard_multi>" should be selected in "Standard multi select"

    Examples:
      | select_value      | select_one | old_style | multiselect | standard_multi |
      | Group 2, option 1 | Ms.        | Blue      | Green       | Volvo          |
      | Group 1, option 2 | Prof.      | Red       | Black       | Saab           |

