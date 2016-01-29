Meta:            Story for test
@author
@date:
@event:          Training Program
@topic:          Behaviour Driven Development with Java
@organization:
@licence:

Narrative : As a Trader I want to be informed for price changes

Scenario: A trader is not alerted because price is under threshold (1)

Given a stock and a threshold of 15.0
When stock is traded at 5.0
Then the alert status should be OFF


