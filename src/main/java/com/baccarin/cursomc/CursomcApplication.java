package com.baccarin.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.baccarin.cursomc.domain.Categoria;
import com.baccarin.cursomc.repositories.CategoriaRepository;
import com.baccarin.cursomc.services.CategoriaService;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository catRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
		//teste
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria("Informática");
		Categoria cat2 = new Categoria("RH");
		
		catRepo.saveAll(Arrays.asList(cat1,cat2));
	} 

}
