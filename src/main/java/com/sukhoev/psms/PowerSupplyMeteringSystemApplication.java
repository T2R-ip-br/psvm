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

	// TODO: реализовать вывод блоков питания/точек подключения соответствующих стойке
	// TODO: реализовать функционал недопускающий добавление оборудования в стойку когда у стойки нет хотябы одного блока питания
	
	// TODO: реализовать расчёт запаса мощности стойки
	// TODO: реализовать проверку свободности юнита при  добавлении оборудования
	// TODO: реализовать объединение ячеек таблицы, отображающей комплектацию стойки, в случае когда оборудование занимает больше одного юнита

}
