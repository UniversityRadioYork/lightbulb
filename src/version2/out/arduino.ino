/*
Arduino PWM Level Shifter
@author Michael G
*/

void setup() {
    pinMode(2, OUTPUT);
    pinMode(12, INPUT);
}

void loop() {
    analogWrite(2, digitalRead(12));
}