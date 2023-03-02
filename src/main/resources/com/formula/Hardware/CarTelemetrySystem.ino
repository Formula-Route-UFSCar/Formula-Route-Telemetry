#include <ArduinoJson.h>
#include <ArduinoJson.hpp>

#include "MPU9250.h"

MPU9250 mpu;

struct __attribute__((packed)) MPU_Data {
  float Yaw;
  float Pitch;
  float Roll;
  float EulerX;
  float EulerY;
  float EulerZ;
  float AccX;
  float AccY;
  float AccZ;
  float LinearAccX;
  float LinearAccY;
  float LinearAccZ;
};

MPU_Data data;

void setup() {
  Serial.begin(9600);
  Wire.begin();
  delay(2000);

  if (!mpu.setup(0x68)) {  // change to your own address
    while (1) {
      Serial.println("MPU connection failed. Please check your connection with `connection_check` example.");
      delay(5000);
    }
  }
}

void loop() {
  if (mpu.update()) {
    static uint32_t prev_ms = millis();
    if (millis() > prev_ms + 25) {

      data = getMPUData();
      Write(data,"LFW:");
      //printMPUData(data);

      prev_ms = millis();
    }
  }
}

MPU_Data getMPUData() {
  MPU_Data data;
  data.Yaw = mpu.getYaw();
  data.Pitch = mpu.getPitch();
  data.Roll = mpu.getRoll();
  data.EulerX = mpu.getEulerX();
  data.EulerY = mpu.getEulerY();
  data.EulerZ = mpu.getEulerZ();
  data.AccX = mpu.getAccX();
  data.AccY = mpu.getAccY();
  data.AccZ = mpu.getAccZ();
  data.LinearAccX = mpu.getLinearAccX();
  data.LinearAccY = mpu.getLinearAccY();
  data.LinearAccZ = mpu.getLinearAccZ();
  return data;
}

void printMPUData(MPU_Data data) {
  Serial.print("Yaw: ");
  Serial.println(data.Yaw);
  Serial.print("Pitch: ");
  Serial.println(data.Pitch);
  Serial.print("Roll: ");
  Serial.println(data.Roll);
  Serial.print("EulerX: ");
  Serial.println(data.EulerX);
  Serial.print("EulerY: ");
  Serial.println(data.EulerY);
  Serial.print("EulerZ: ");
  Serial.println(data.EulerZ);
  Serial.print("AccX: ");
  Serial.println(data.AccX);
  Serial.print("AccY: ");
  Serial.println(data.AccY);
  Serial.print("AccZ: ");
  Serial.println(data.AccZ);
  Serial.print("LinearAccX: ");
  Serial.println(data.LinearAccX);
  Serial.print("LinearAccY: ");
  Serial.println(data.LinearAccY);
  Serial.print("LinearAccZ: ");
  Serial.println(data.LinearAccZ);
}

void Write(MPU_Data receivedData, String wheel) {
Serial.println(wheel);
StaticJsonDocument<96> doc;

doc["Yaw"] = receivedData.Yaw;
doc["Pitch"] = receivedData.Pitch;
doc["Roll"] = receivedData.Roll;
doc["EulerX"] = receivedData.EulerX;
doc["EulerY"] = receivedData.EulerY;
doc["EulerZ"] = receivedData.EulerZ;
doc["AccX"] = receivedData.AccX;
doc["AccY"] = receivedData.AccY;
doc["AccZ"] = receivedData.AccZ;
doc["LinearAccX"] = receivedData.LinearAccX;
doc["LinearAccY"] = receivedData.LinearAccY;
doc["LinearAccZ"] = receivedData.LinearAccZ;
Serial.println();
serializeJson(doc, Serial);
}