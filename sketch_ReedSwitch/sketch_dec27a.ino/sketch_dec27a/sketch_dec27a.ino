// Arduino pin numbers
//D4 and A0 used on board and connected to D0 and A0 on the module
//G ground
//+ 5V
//open arduino console - upload the code and watch the result
 
const int digital = 4;
const int analog = A0;

void setup()
{
pinMode(digital, INPUT);
Serial.begin(9600);
}
 
void loop()
{
while(Serial.available() == 0);
int n = Serial.read() - '0';

if(n == 1) { 
  Serial.print(digitalRead(digital));
  Serial.print("-");
  Serial.println(analogRead(A0));
}
else if(n == 0) {
  Serial.print(analogRead(A0));
  Serial.print("-");
  Serial.println(digitalRead(digital));
}
else {
  Serial.println("Invalid");
  
}
}
