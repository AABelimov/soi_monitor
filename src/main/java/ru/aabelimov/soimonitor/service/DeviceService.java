package ru.aabelimov.soimonitor.service;

import ru.aabelimov.soimonitor.entity.Device;

import java.util.List;

public interface DeviceService {

    List<Device> getAll();

    void reverseIsOnline(Device device);
}
