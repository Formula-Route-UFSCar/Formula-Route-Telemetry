package com.formula;

import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.TimeZone;

public class Config {
    public static final String APP_VERSION = "SNAPSHOT 1.1";

    public static final String BACKEND_HOST = getBackendHost();

    public static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("America/Sao_Paulo");

    public static final String CHART_DATA_PATTERN = "dd/MM HH:mm:ss";
    public static Image getIcon(String icon){
        //System.out.println(FormulaRouteTelemetryMain.class.getResource(icon));
        //System.out.println(icon);
        return new Image(String.valueOf(Config.class.getResource(icon)));
    }

    public static String getFormattedDate(ZonedDateTime zonedDateTime){
        ZonedDateTime destinationDateTime = zonedDateTime.withZoneSameInstant(DEFAULT_TIMEZONE.toZoneId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CHART_DATA_PATTERN);
        return destinationDateTime.format(formatter);
    }

    public static ZonedDateTime getZonedDateTime(ZonedDateTime zonedDateTime){
        ZonedDateTime destinationDateTime = zonedDateTime.withZoneSameInstant(DEFAULT_TIMEZONE.toZoneId());
        return destinationDateTime;
    }

    public static String getColorPalleteProperties(String key) {
        Properties properties = new Properties();
        try (
                InputStream inputStream = FormulaRouteTelemetryMain.class.getClassLoader().getResourceAsStream("formula/color_pallate.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                System.err.println("File not found: color_pallate.properties");
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(key);
    }

    public static String getTooltipTextProperties(String key) {
        Properties properties = new Properties();
        try (
                InputStream inputStream = FormulaRouteTelemetryMain.class.getClassLoader().getResourceAsStream("formula/tooltips_texts.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                System.err.println("File not found: tooltips_texts.properties");
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    public static String getBackendHost() {
        Properties properties = new Properties();
        try (
                InputStream inputStream = FormulaRouteTelemetryMain.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                System.err.println("File not found: tooltips_texts.properties");
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        if(properties.getProperty("profiles.active").equals("local") || properties.getProperty("profiles.active").equals("dev"))
        {
            System.out.println("Starting in local mode...");
            return "https://localhost:8080";
        }
        return "";
    }

}
