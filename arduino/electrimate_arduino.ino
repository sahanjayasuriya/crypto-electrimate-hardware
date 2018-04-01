#include "EmonLib.h"             // Include Emon Library


#define VOLT_CAL 152.7
#define CURRENT_CAL 37.5
#define CURRENT_CAL2 28.5

#define VOLTAGE_PIN 1
#define CT_1_PIN 2
#define CT_2_PIN 4
#define DEBUG_PIN 5

EnergyMonitor emon1;             // Create an instance
EnergyMonitor emon2;

long lastMillis = 0;
bool debug = false;

void setup()
{
  Serial.begin(9600);

  emon1.voltage(VOLTAGE_PIN, VOLT_CAL, 1.7);  // Voltage: input pin, calibration, phase_shift
  emon1.current(CT_1_PIN, CURRENT_CAL);       // Current: input pin, calibration.

  emon2.voltage(VOLTAGE_PIN, VOLT_CAL, 1.7);  // Voltage: input pin, calibration, phase_shift
  emon2.current(CT_2_PIN, CURRENT_CAL2);       // Current: input pin, calibration.

  pinMode(5, INPUT);

}

void printData(EnergyMonitor emon, int pin) {

  float supplyVoltage   = emon.Vrms;             //extract Vrms into Variable
  float Irms            = emon.Irms;             //extract Irms into Variable

  if (supplyVoltage < 215) {
    supplyVoltage = 0;
    Irms = 0;
  }
  if (Irms <= 0.15) {
    Irms = 0;
  }

  Serial.print("*");
  Serial.print(supplyVoltage);
  Serial.print("|");
  Serial.print(Irms);
  Serial.print("|");
  Serial.print(pin);
  Serial.print("|");
  long m = millis();
  Serial.print(m - lastMillis);
  Serial.println("+");
  lastMillis = m;

}

void debugPrint(float f) {
  if (debug)
    Serial.println(f);
}


void loop()
{
  Serial.println(digitalRead(DEBUG_PIN));
  if ( digitalRead(DEBUG_PIN) == 1 ) {
    debug = true;
  } else {
    false;
  }
  float r = analogRead(CT_1_PIN);
  debugPrint(r);
  if (r != 0.0) {
    emon1.calcVI(20, 2000);        // Calculate all. No.of half wavelengths (crossings), time-out
    printData(emon1, CT_1_PIN);
  }

  r = digitalRead(CT_2_PIN);
  debugPrint(r);
  if (r != 0.0) {
    emon2.calcVI(20, 2000);        // Calculate all. No.of half wavelengths (crossings), time-out
    printData(emon2, CT_2_PIN);
  }
}
