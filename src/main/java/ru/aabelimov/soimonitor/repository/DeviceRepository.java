package ru.aabelimov.soimonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aabelimov.soimonitor.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
