package com.baccarin.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.baccarin.cursomc.domain.Categoria;
import com.baccarin.cursomc.domain.Cidade;
import com.baccarin.cursomc.domain.Cliente;
import com.baccarin.cursomc.domain.Endereco;
import com.baccarin.cursomc.domain.Estado;
import com.baccarin.cursomc.domain.Produto;
import com.baccarin.cursomc.domain.enums.TipoCliente;
import com.baccarin.cursomc.repositories.CategoriaRepository;
import com.baccarin.cursomc.repositories.CidadeRepository;
import com.baccarin.cursomc.repositories.ClienteRepository;
import com.baccarin.cursomc.repositories.EnderecoRepository;
import com.baccarin.cursomc.repositories.EstadoRepository;
import com.baccarin.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository catRepo;
	@Autowired
	private ProdutoRepository produtoRepo;
	@Autowired
	private CidadeRepository cidadeRepo;
	@Autowired
	private EstadoRepository estadoRepo;
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private EnderecoRepository enderecoRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
		// teste
	}

	@Override
	public void run(String... args) throws Exception {
		this.startProdutoAndCategoria();
		this.startEstadoAndCidade();
		this.startCliente();
	}

	public void startProdutoAndCategoria() {
		Categoria cat1 = new Categoria("Informática");
		Categoria cat2 = new Categoria("RH");

		Produto p1 = new Produto("Computador", 1000.0);
		Produto p2 = new Produto("Televisão", 2300.0);
		Produto p3 = new Produto("Cadeira", 130.0);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2));
		cat2.getProdutos().addAll(Arrays.asList(p3));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat2));

		catRepo.saveAll(Arrays.asList(cat1, cat2));
		produtoRepo.saveAll(Arrays.asList(p1, p2, p3));
	}

	public void startEstadoAndCidade() {
		Estado e1 = new Estado("Rio Grande do Sul");
		Estado e2 = new Estado("São Paulo");

		Cidade c1 = new Cidade("Pelotas", e1);
		Cidade c2 = new Cidade("Rio Grande", e1);
		Cidade c3 = new Cidade("Mogi", e2);

		e1.getCidades().addAll(Arrays.asList(c1, c2));
		e2.getCidades().addAll(Arrays.asList(c3));

		estadoRepo.saveAll(Arrays.asList(e1, e2));
		cidadeRepo.saveAll(Arrays.asList(c1, c2, c3));

	}

	public void startCliente() {
		Estado e1 = new Estado("Rio Grande do Norte");
		Cidade c1 = new Cidade("Não sei", e1);
		e1.getCidades().addAll(Arrays.asList(c1));
		estadoRepo.saveAll(Arrays.asList(e1));
		cidadeRepo.saveAll(Arrays.asList(c1));

		
		Cliente cliente = new Cliente("Guilherme", "teste@email.com", "000.000.000-00", TipoCliente.PESSOA_FISICA);
		cliente.getTelefones().addAll(Arrays.asList("99999999", "888884484"));

		Cliente cliente2 = new Cliente("Guilherme", "teste2@email.com", "000.000.000-11", TipoCliente.PESSOA_FISICA);
		cliente2.getTelefones().addAll(Arrays.asList("9015213454","9546545612"));

		Endereco end1 = new Endereco("Centro", "565", "A", "CentroDnvo", "96000-500", cliente, c1);
		Endereco end2 = new Endereco("Praia", "5330", "B", "CentroDnvo", "96000-000", cliente2, c1);
		
		cliente.getEnderecos().addAll(Arrays.asList(end1,end2));
		cliente2.getEnderecos().addAll(Arrays.asList(end2));

		clienteRepo.saveAll(Arrays.asList(cliente,cliente2));
		enderecoRepo.saveAll(Arrays.asList(end1,end2));

	}

}
