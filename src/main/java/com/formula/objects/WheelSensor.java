/*
 * MIT License
 *
 * Copyright (c)2023 Matheus Markies
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.formula.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.formula.serialport.SerialRunnable;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
public class WheelSensor {
   private SerialRunnable.Wheel wheel;
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
