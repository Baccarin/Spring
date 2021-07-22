package com.baccarin.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.baccarin.cursomc.domain.Categoria;
import com.baccarin.cursomc.domain.Produto;
import com.baccarin.cursomc.repositories.CategoriaRepository;
import com.baccarin.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository catRepo;
	@Autowired
	private ProdutoRepository produtoRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
		//teste
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria("Informática");
		Categoria cat2 = new Categoria("RH");
		
		Produto p1 = new Produto("Computador", 1000.0);
		Produto p2 = new Produto("Televisão", 2300.0);
		Produto p3 = new Produto("Cadeira", 130.0);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2));
		cat2.getProdutos().addAll(Arrays.asList(p3));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		
		catRepo.saveAll(Arrays.asList(cat1,cat2));
		produtoRepo.saveAll(Arrays.asList(p1,p2,p3));
		
	} 
	

}
