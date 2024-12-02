package ru.aabelimov.soimonitor.thread;

import ru.aabelimov.soimonitor.entity.Device;
import ru.aabelimov.soimonitor.service.DeviceService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class PingRunnable implements Runnable {

    private final Device device;
    private final DeviceService deviceService;

    private static final boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

    public PingRunnable(Device device, DeviceService deviceService) {
        this.device = device;
        this.deviceService = deviceService;
    }

    @Override
    public void run() {
        ProcessBuilder pb = new ProcessBuilder("ping", device.getIp(), isWindows ? "-t" : "");
        try {
            Process process = pb.start();
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("CP866")));
            String response;

            while ((response = inputStream.readLine()) != null) {
                response = response.toLowerCase();

                if (response.contains("ttl") && !device.getIsOnline()) {
                    deviceService.reverseIsOnline(device);
                } else if (!response.contains("ttl") && device.getIsOnline()) {
                    deviceService.reverseIsOnline(device);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
