Scenario: trader should insert stock data in a web page interface. trader should be not alerted below threshold and should be alerted above threshold after it has been inserted.

Given trader starts web application
When trader inserts stock <symbol> with threshold <threshold> and trade at <tradeAt>
Then web page should show an <status> message

Examples:
|symbol|threshold|tradeAt|status|
|STK1|5|4|OFF|
|STK1|5|14|ON|