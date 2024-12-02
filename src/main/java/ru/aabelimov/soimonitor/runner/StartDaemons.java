package ru.aabelimov.soimonitor.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.aabelimov.soimonitor.entity.Device;
import ru.aabelimov.soimonitor.service.DeviceService;
import ru.aabelimov.soimonitor.thread.PingRunnable;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StartDaemons implements ApplicationRunner {

    private final DeviceService deviceService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Device> devices = deviceService.getAll();
        devices.forEach(equipment -> new Thread(new PingRunnable(equipment, deviceService)).start());
    }
}
