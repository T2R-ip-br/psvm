package com.sukhoev.psms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PowerSupplyMeteringSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerSupplyMeteringSystemApplication.class, args);
	}

	// TODO: реализовать методы редактирования записей
	// TODO: реализовать методы удаления записей для всех сущностей
	// TODO: реализовать расчёт запаса мощности стойки
	// TODO: реализовать редактирование оборудования
	// TODO: реализовать метод редактирования конфигурации стойки
	// TODO: реализовать функцию проверки соответствия ооборудования по ширине и длинее при его добавлении в стойку
}
