package com.brasens.utilities.analyses;

import com.brasens.http.objects.MachineIntervals;
import com.brasens.utilities.common.enums.AssetState;
import com.brasens.utilities.common.enums.DataPeriod;
import lombok.*;

import java.time.*;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.*;

public class SensorStatusAnalyzer {

    @Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
    public static class AnalyzedData {
        public String[] timeTags;
        public double[] offlineTime;
        public double[] onlineTime;
    }

    public static AnalyzedData processData(ArrayList<MachineIntervals> machineIntervals, DataPeriod dataPeriod) {
        List<String> timeTags = new ArrayList<>();
        List<Double> offlineTime = new ArrayList<>();
        List<Double> onlineTime = new ArrayList<>();
        ZonedDateTime now = ZonedDateTime.now();
        int periodCount = 0;
        switch (dataPeriod) {
            case DAILY:
                periodCount = now.getHour();
                for (int i = 0; i <= periodCount; i++) {
                    timeTags.add(String.format("%02dh", i));
                    offlineTime.add(0.0);
                    onlineTime.add(0.0);
                }
                break;
            case WEEKLY:
                periodCount = 7;
                for (int i = 0; i < periodCount; i++) {
                    timeTags.add(DayOfWeek.of((now.minusDays(i).getDayOfWeek().getValue() + 5) % 7 + 1).getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
                    offlineTime.add(0.0);
                    onlineTime.add(0.0);
                }
                Collections.reverse(timeTags);
                break;
            case MONTHLY:
                LocalDate firstDayOfMonth = now.toLocalDate().withDayOfMonth(1);
                int firstWeekOfMonth = firstDayOfMonth.get(WeekFields.of(Locale.getDefault()).weekOfYear());
                int currentWeekOfYear = now.get(WeekFields.of(Locale.getDefault()).weekOfYear());

                periodCount = currentWeekOfYear - firstWeekOfMonth + 1;
                for (int i = 0; i < periodCount; i++) {
                    timeTags.add("Week " + (i + 1));
                    offlineTime.add(0.0);
                    onlineTime.add(0.0);
                }
                break;
            default:
                return processData(machineIntervals, DataPeriod.WEEKLY);
        }

        for (int i = 0; i < machineIntervals.size() - 1; i++) {
            MachineIntervals current = machineIntervals.get(i);
            MachineIntervals next = machineIntervals.get(i + 1);
            long deltaTime = ChronoUnit.SECONDS.between(current.getAdded(), next.getAdded());

            int index = -1;
            switch (dataPeriod) {
                case DAILY:
                    index = current.getAdded().getHour();
                    break;
                case WEEKLY:
                    index = (int) ChronoUnit.DAYS.between(now, current.getAdded());
                    break;
                case MONTHLY:
                    index = current.getAdded().get(WeekFields.of(Locale.getDefault()).weekOfMonth()) - 1;
                    break;
            }

            if (index >= 0 && index < periodCount) {
                double deltaHours = deltaTime / 3600.0;
                if (current.getAssetState() == AssetState.WORKING) {
                    onlineTime.set(index, (double)Math.round((onlineTime.get(index) + deltaHours)*100)/100);
                } else {
                    offlineTime.set(index, (double)Math.round((offlineTime.get(index) + deltaHours)*100)/100);
                }
            }
        }

        AnalyzedData sensorData = new AnalyzedData();
        sensorData.setTimeTags(timeTags.toArray(new String[0]));
        sensorData.setOfflineTime(offlineTime.stream().mapToDouble(Double::doubleValue).toArray());
        sensorData.setOnlineTime(onlineTime.stream().mapToDouble(Double::doubleValue).toArray());

        return sensorData;
    }
}
