Scenario:  trader should be not alerted below threshold and should be alerted above threshold 
 
Given a stock of symbol <symbol> and a threshold of <threshold>
When the stock is traded at <price>
Then the alert status should be <status>

Examples:
|symbol|threshold|price|status|
|STK1|5|4|OFF|
|STK1|5|14|ON|
