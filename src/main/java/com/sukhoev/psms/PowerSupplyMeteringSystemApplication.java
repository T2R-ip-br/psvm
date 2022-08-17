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
		// помещения
		// точки подключения
		// Модели стоек
		// Стойки
		// Конфигурацию стоек
		// пользователей
	// TODO: реализовать методы удаления записей для всех сущностей
		// помещения
		// точки подключения
		// Модели стоек
		// пользователей
	// TODO: реализовать расчёт запаса мощности стойки
	// TODO: реализовать функцию поиска подходящих стоек для оборудования
	// TODO: Дописать контроллеры, чтобы были все методы по best practices
}
