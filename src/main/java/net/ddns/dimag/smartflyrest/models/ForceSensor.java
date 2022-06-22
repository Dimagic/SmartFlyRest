package net.ddns.dimag.smartflyrest.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "SENSORS")
public class ForceSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Column(name = "img")
    private String img;

    @Column(name = "version")
    private String version;

    @Column(name = "mac")
    private String mac;

    @Column(name = "ip")
    private String ip;

    @Column(name = "calibration_value")
    private int calibrationValue;

    @Column(name = "date_connection")
    private Long dateConnection;


    public ForceSensor() {
    }

    public ForceSensor(String mac, String img, String version, String ip) {
        this.mac = mac;
        this.img = img;
        this.version = version;
        this.ip = ip;
        this.dateConnection = new Date().getTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getCalibrationValue() {
        return calibrationValue;
    }

    public void setCalibrationValue(int calibrationValue) {
        this.calibrationValue = calibrationValue;
    }

    public Long getDateConnection() {
        return dateConnection;
    }

    public void setDateConnection(Date val) {
        this.dateConnection = val.getTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForceSensor that = (ForceSensor) o;
        return calibrationValue == that.calibrationValue
                && img.equals(that.img)
                && version.equals(that.version)
                && mac.equals(that.mac)
                && ip.equals(that.ip)
                && dateConnection.equals(that.dateConnection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(img, version, mac, ip, calibrationValue);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }


}
