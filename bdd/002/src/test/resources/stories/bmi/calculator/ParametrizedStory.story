Meta:            Story for test
@author
@date:
@event:          Training Program
@topic:          Behaviour Driven Development with Java
@organization:
@licence:

Scenario:  Simple BMI calculator validation used for parametrized story example

Given Weight Classifier is started indeed
When the user pass to the calculator a value for mass <mass> kg
And the user pass to the calculator a value for height <height> m
Then the calculator shows that the value for the users's body mass index is <bmi>
And for the calculated bmi value <bmi>, the weight classifier shows: <weight-category>

Examples:
|mass		|height		|bmi					|weight-category		|
|50			|1.99		|12.625944137573242		|Severely Underweight	|
|50			|1.70		|17.301036834716797		|Underweight			|
|75			|1.75		|24.489795684814453		|Normal					|
|83			|1.77		|26.493024826049805		|Overweight				|
|115		|1.95		|30.24325942993164		|Obese Class I			|
|115		|1.75		|37.551021575927734		|Obese Class II			|
|115		|1.55		|47.86680603027344		|Obese Class III		|