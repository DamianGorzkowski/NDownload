Feature: User register procedure

  Scenario Outline: Successful user registration procedure
    Given Launch browser
    And Navigate to url '<link>'
    And Download link clicked with file '<name>'


    Examples:
      |link  |name|
      |https://naruto.wbijam.pl/pierwsza_seria-080.html|n_080.mp4|