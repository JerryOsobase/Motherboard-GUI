@E2E
Feature: Ecommerce order
  Making a successful order

  Background:
    Given initialize browser and navigate to url

    Scenario Outline:
      Given user login with email <email> and password <password>
      When user add product <productName> to cart
      And Select country and make payment
      Then user can view success message "THANKYOU FOR THE ORDER."
      Examples:
      |email         | password   | productName    |
      |jjdj@dkk.com  | Testing1@  | ZARA COAT 3    |
      |jerry@base.com| Base4jerry@| adidas original|
