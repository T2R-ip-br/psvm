package com.sukhoev.psms.hardware.service;

import com.sukhoev.psms.hardware.entity.TypeHardware;
import com.sukhoev.psms.hardware.repository.TypeHardwareRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TypeHardwareService {

    private final TypeHardwareRepository typeHardwareRepository;

    public List<TypeHardware> findAll() {
        return typeHardwareRepository.findAll();
    }
}
