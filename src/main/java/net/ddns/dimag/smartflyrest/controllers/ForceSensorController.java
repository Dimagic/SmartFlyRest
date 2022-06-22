package net.ddns.dimag.smartflyrest.controllers;

import com.google.gson.JsonObject;
import net.ddns.dimag.smartflyrest.models.Competitor;
import net.ddns.dimag.smartflyrest.models.ForceSensor;
import net.ddns.dimag.smartflyrest.models.SensorMessage;
import net.ddns.dimag.smartflyrest.repo.ForceSensorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping("/api")
public class ForceSensorController {
    final ForceSensorRepo repo;

    @Autowired
    public ForceSensorController(ForceSensorRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/sensors")
    public ResponseEntity<List<ForceSensor>> getAllSensors() {
        try {
            List<ForceSensor> sensors = new ArrayList<>();
            repo.findAll().forEach(sensors::add);
            if (sensors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(sensors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sensors/{id}")
    public ResponseEntity<ForceSensor> getSensorById(@PathVariable("id") long id) {
        Optional<ForceSensor> sensorData = repo.findById(id);

        if (sensorData.isPresent()) {
            return new ResponseEntity<>(sensorData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/sensors")
    public ResponseEntity<ForceSensor> saveSensor(@RequestBody ForceSensor forceSensor) {
        try {
            Optional<ForceSensor> _sensor = repo.findSensorByMac(forceSensor.getMac());
            if (_sensor.isPresent()) {
                return updateSensor(_sensor.get().getId(), forceSensor);
            }
            forceSensor.setDateConnection(new Date());
            ForceSensor _forceSensor = repo
                    .save(forceSensor);
            return new ResponseEntity<>(_forceSensor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/sensors/{id}")
    public ResponseEntity<ForceSensor> updateSensor(@PathVariable("id") long id, @RequestBody ForceSensor forceSensor) {
        Optional<ForceSensor> sensorData = repo.findById(id);

        if (sensorData.isPresent()) {
            forceSensor.setId(id);
            forceSensor.setDateConnection(new Date());
            return new ResponseEntity<>(repo.save(forceSensor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/sensor_msg")
    public void msgSensor(@RequestBody SensorMessage msg) {
        System.out.println(msg);
//        try {
//            Optional<ForceSensor> _sensor = repo.findSensorByMac(forceSensor.getMac());
//            if (_sensor.isPresent()) {
//                return updateSensor(_sensor.get().getId(), forceSensor);
//            }
//            forceSensor.setDateConnection(new Date());
//            ForceSensor _forceSensor = repo
//                    .save(forceSensor);
//            return new ResponseEntity<>(_forceSensor, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }
}
