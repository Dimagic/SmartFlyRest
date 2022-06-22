package net.ddns.dimag.smartflyrest.utils;


import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {
    static Logger LOGGER = LogManager.getLogger(Utils.class.getName());

    final static long ONE_MILLISECOND = 1;
    final static long MILLISECONDS_IN_A_SECOND = 1000;

    final static long ONE_SECOND = 1000;
    final static long SECONDS_IN_A_MINUTE = 60;

    final static long ONE_MINUTE = ONE_SECOND * 60;
    final static long MINUTES_IN_AN_HOUR = 60;

    final static long ONE_HOUR = ONE_MINUTE * 60;
    final static long HOURS_IN_A_DAY = 24;
    final static long ONE_DAY = ONE_HOUR * 24;
    final static long DAYS_IN_A_YEAR = 365;

    public static String getFormattedDate(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dt.format(date);
    }

    public static String getFormattedDate(Long date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dt.format(date);
    }

    public static String getFormattedOnlyDate(Long date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        return dt.format(date);
    }

    public static String getFormattedTime(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dt = new SimpleDateFormat("HH:mm:ss");
        return dt.format(date);
    }

    public static String formatMS(Number n) {
        String res = "";
        if (n != null) {
            long duration = n.longValue();

            duration /= ONE_MILLISECOND;
            int milliseconds = (int) (duration % MILLISECONDS_IN_A_SECOND);
            duration /= ONE_SECOND;
            int seconds = (int) (duration % SECONDS_IN_A_MINUTE);
            duration /= SECONDS_IN_A_MINUTE;
            int minutes = (int) (duration % MINUTES_IN_AN_HOUR);
            duration /= MINUTES_IN_AN_HOUR;
            int hours = (int) (duration % HOURS_IN_A_DAY);
            duration /= HOURS_IN_A_DAY;
            int days = (int) (duration % DAYS_IN_A_YEAR);
            duration /= DAYS_IN_A_YEAR;
            int years = (int) (duration);

//            if (days == 0) {
//                res = String.format("%02d:%02d:%02d", hours, minutes, seconds);
//            } else {
//                res = String.format("%d days %02d:%02d:%02d", days, hours, minutes, seconds);
//            }

            res = String.format("%02d:%02d", minutes, seconds);
        }
        return res;
    }

    public static String formatHMS(Number n) {
        String res = "";
        if (n != null) {
            long duration = n.longValue();

            duration /= ONE_MILLISECOND;
            int milliseconds = (int) (duration % MILLISECONDS_IN_A_SECOND);
            duration /= ONE_SECOND;
            int seconds = (int) (duration % SECONDS_IN_A_MINUTE);
            duration /= SECONDS_IN_A_MINUTE;
            int minutes = (int) (duration % MINUTES_IN_AN_HOUR);
            duration /= MINUTES_IN_AN_HOUR;
            int hours = (int) (duration % HOURS_IN_A_DAY);
            duration /= HOURS_IN_A_DAY;
            int days = (int) (duration % DAYS_IN_A_YEAR);
            duration /= DAYS_IN_A_YEAR;
            int years = (int) (duration);

            if (days == 0) {
                res = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            } else {
                res = String.format("%d days %02d:%02d:%02d", days, hours, minutes, seconds);
            }
        }
        return res;
    }

    public static LocalDate dateToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date localDateToDate(LocalDate dateToConvert) {
        return Date.from(Instant.from(dateToConvert.atStartOfDay(ZoneId.systemDefault())));
    }

    public static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = dateToLocalDate(new Date());
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public static String getComputerName() {
        Map<String, String> env = System.getenv();
        if (env.containsKey("COMPUTERNAME"))
            return env.get("COMPUTERNAME");
        else return env.getOrDefault("HOSTNAME", "Unknown Computer");
    }

    //    return
    //    1 if online
    //    0 if host is not reachable
    //    -1 if UnknownHost
    //    -2 if no lan connection
    public static int isSystemOnline(String ipName) {
        try {
            InetAddress inet = InetAddress.getByName(ipName);
            if (inet.isReachable(3000)) {
                return 1;
            }
        } catch (SocketException e) {
            return -2;
        } catch (UnknownHostException e) {
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Double> getDoubleList(double start, double end, double step) {
        List<Double> doubleList = new ArrayList<>();
        for (double d = start; d < end; d += step) {
            doubleList.add(d);
        }
        return doubleList;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String sendCmd(String cmd) {
        return sendCmd(cmd.trim().split("\\s+"));
    }

    public static String sendCmd(String[] args) {
        StringBuilder sb = new StringBuilder();
        try {
            ProcessBuilder pb = new ProcessBuilder(args);
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "866"));
            String line;
            while ((line = reader.readLine()) != null) {
                byte[] bytes = line.getBytes(StandardCharsets.UTF_8);
                sb.append(new String(bytes, StandardCharsets.UTF_8));
                System.out.println(new String(bytes, StandardCharsets.UTF_8));
            }
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void sendPowerShellCommand (String cmd, boolean asAdmin) throws IOException {
        String command;
        if (asAdmin) {
//            command = String.format("Powershell.exe -Command \"& {Start-Process Powershell.exe -Verb RunAs -Wait -ArgumentList %s}\"", cmd);
            command = "powershell -Verb runAs -ArgumentList ipconfig";
        } else {
            command = String.format("powershell.exe  %s -verb runas", cmd);
        }

        Process powerShellProcess = Runtime.getRuntime().exec(command);
        // Getting the results
        powerShellProcess.getOutputStream().close();
        String line;
        System.out.println("Standard Output:");
        BufferedReader stdout = new BufferedReader(new InputStreamReader(
                powerShellProcess.getInputStream()));
        while ((line = stdout.readLine()) != null) {
            System.out.println(line);
        }
        stdout.close();
        System.out.println("Standard Error:");
        BufferedReader stderr = new BufferedReader(new InputStreamReader(
                powerShellProcess.getErrorStream()));
        while ((line = stderr.readLine()) != null) {
            System.out.println(line);
        }
        stderr.close();
        System.out.println("Done");
    }
}
