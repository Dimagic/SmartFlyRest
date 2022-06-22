package net.ddns.dimag.smartflyrest.models;

import com.google.gson.JsonObject;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SensorMessage {
    private String mac;
    private String ip;
    private String img;
    private String ver;
    private int sensorNum;
    private int analogVal;
    private int voltage;
    private int resist;

    public SensorMessage() {
    }

    public SensorMessage(JsonObject o) {
        this.mac = o.get("mac").getAsString();
        this.ip = o.get("ip").getAsString();
        this.img = o.get("img").getAsString();
        this.ver = o.get("ver").getAsString();
        this.sensorNum = o.get("sensorNum").getAsInt();
        this.analogVal = o.get("analogVal").getAsInt();
        this.voltage = o.get("voltage").getAsInt();
        this.resist = o.get("resist").getAsInt();
    }

    public String getMac() {
        return mac;
    }

    public String getIp() {
        return ip;
    }

    public String getImg() {
        return img;
    }

    public String getVer() {
        return ver;
    }

    public int getSensorNum() {
        return sensorNum;
    }

    public int getAnalogVal() {
        return analogVal;
    }

    public int getVoltage() {
        return voltage;
    }

    public int getResist() {
        return resist;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mac", mac)
                .append("ip", ip)
                .append("img", img)
                .append("ver", ver)
                .append("sensorNum", sensorNum)
                .append("analogVal", analogVal)
                .append("voltage", voltage)
                .append("resist", resist)
                .toString();
    }
}
