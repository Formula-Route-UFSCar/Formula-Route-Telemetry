package com.formula.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
public class WheelSensor {
   @JsonProperty("Yaw")
   public double yaw;
   @JsonProperty("Pitch")
   public double pitch;
   @JsonProperty("Roll")
   public double roll;
   @JsonProperty("EulerX")
   public double eulerX;
   @JsonProperty("EulerY")
   public double eulerY;
   @JsonProperty("EulerZ")
   public double eulerZ;
   @JsonProperty("AccX")
   public double accX;
   @JsonProperty("AccY")
   public double accY;
   @JsonProperty("AccZ")
   public double accZ;
   @JsonProperty("LinearAccX")
   public double linearAccX;
   @JsonProperty("LinearAccY")
   public double linearAccY;
   @JsonProperty("LinearAccZ")
   public double linearAccZ;

   @Override
   public String toString() {
      return "WheelSensor{" +
              "yaw=" + yaw +
              ", pitch=" + pitch +
              ", roll=" + roll +
              ", eulerX=" + eulerX +
              ", eulerY=" + eulerY +
              ", eulerZ=" + eulerZ +
              ", accX=" + accX +
              ", accY=" + accY +
              ", accZ=" + accZ +
              ", linearAccX=" + linearAccX +
              ", linearAccY=" + linearAccY +
              ", linearAccZ=" + linearAccZ +
              '}';
   }
}
