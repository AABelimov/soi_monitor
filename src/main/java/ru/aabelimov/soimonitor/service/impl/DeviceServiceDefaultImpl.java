package ru.aabelimov.soimonitor.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aabelimov.soimonitor.entity.Device;
import ru.aabelimov.soimonitor.repository.DeviceRepository;
import ru.aabelimov.soimonitor.service.DeviceService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceServiceDefaultImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    @Override
    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    @Override
    public void reverseIsOnline(Device device) {
        device.setIsOnline(!device.getIsOnline());
        deviceRepository.save(device);
    }
}
