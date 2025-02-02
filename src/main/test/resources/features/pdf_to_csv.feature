Feature: Convert PDF to CSV

  Scenario: Validate scanned PDF data
    Given I extract text from PDF "path"
    Then i write the extracted text to CSV "path"

  Scenario: Validate scanned PDF data
    Given I have a TXT file at "path"
    When I parse the TXT file
    Then I should see the extracted data printed in the logs