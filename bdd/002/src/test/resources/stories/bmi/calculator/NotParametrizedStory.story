Meta:            Story for test
@author
@date:
@event:          Training Program
@topic:          Behaviour Driven Development with Java
@organization:
@licence:

Scenario:  Simple BMI calculator validation used for not parametrized story example (1)

Given a body mass index calculator
And Weight Classifier is started
When a patient is with mass 83 kg and height 1.77 m
Then patient's body mass index is 26.493024826049805
And for the calculated bmi value 26.493024826049805, the weight classifier shows: Overweight

Scenario:  Simple BMI calculator validation used for not parametrized story example (2)

Given a body mass index calculator
And Weight Classifier is started
When a patient is with mass 50 kg and height 1.70 m
Then patient's body mass index is 17.301036834716797
And for the calculated bmi value 17.301036834716797, the weight classifier shows: Underweight

Scenario:  Simple BMI calculator validation used for not parametrized story example (3)

Given a body mass index calculator
And Weight Classifier is started
When a patient is with mass 115 kg and height 1.95 m
Then patient's body mass index is 30.24325942993164
And for the calculated bmi value 30.24325942993164, the weight classifier shows: Obese Class I