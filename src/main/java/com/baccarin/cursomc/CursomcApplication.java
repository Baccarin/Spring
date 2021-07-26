package com.baccarin.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.baccarin.cursomc.domain.Categoria;
import com.baccarin.cursomc.domain.Cidade;
import com.baccarin.cursomc.domain.Cliente;
import com.baccarin.cursomc.domain.Endereco;
import com.baccarin.cursomc.domain.Estado;
import com.baccarin.cursomc.domain.Pagamento;
import com.baccarin.cursomc.domain.PagamentoComBoleto;
import com.baccarin.cursomc.domain.PagamentoComCartao;
import com.baccarin.cursomc.domain.Pedido;
import com.baccarin.cursomc.domain.Produto;
import com.baccarin.cursomc.domain.enums.EstadoPagamento;
import com.baccarin.cursomc.domain.enums.TipoCliente;
import com.baccarin.cursomc.repositories.CategoriaRepository;
import com.baccarin.cursomc.repositories.CidadeRepository;
import com.baccarin.cursomc.repositories.ClienteRepository;
import com.baccarin.cursomc.repositories.EnderecoRepository;
import com.baccarin.cursomc.repositories.EstadoRepository;
import com.baccarin.cursomc.repositories.PagamentoRepository;
import com.baccarin.cursomc.repositories.PedidoRepository;
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
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired
	private PagamentoRepository pagamentoRepo;
	
	

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
		// teste
	}

	@Override
	public void run(String... args) throws Exception {

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

		Estado estado1 = new Estado("Rio Grande do Sul");
		Estado estado2 = new Estado("São Paulo");

		Cidade cidade1 = new Cidade("Pelotas", estado1);
		Cidade cidade2 = new Cidade("Rio Grande", estado1);
		Cidade cidade3 = new Cidade("Mogi", estado2);

		estado1.getCidades().addAll(Arrays.asList(cidade1, cidade2));
		estado2.getCidades().addAll(Arrays.asList(cidade3));

		estadoRepo.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepo.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		Cliente cliente1 = new Cliente("Guilherme", "teste@email.com", "000.000.000-00", TipoCliente.PESSOA_FISICA);
		cliente1.getTelefones().addAll(Arrays.asList("99999999", "888884484"));

		Cliente cliente2 = new Cliente("Guilherme", "teste2@email.com", "000.000.000-11", TipoCliente.PESSOA_FISICA);
		cliente2.getTelefones().addAll(Arrays.asList("9015213454", "9546545612"));

		Endereco endereco1 = new Endereco("Centro", "565", "A", "CentroDnvo", "96000-500", cliente1, cidade1);
		Endereco endereco2 = new Endereco("Praia", "5330", "B", "CentroDnvo", "96000-000", cliente2, cidade1);

		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		cliente2.getEnderecos().addAll(Arrays.asList(endereco2));

		clienteRepo.saveAll(Arrays.asList(cliente1, cliente2));
		enderecoRepo.saveAll(Arrays.asList(endereco1, endereco2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido pedido1 = new Pedido(sdf.parse("30/09/2017 10:50"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(sdf.parse("10/09/2020 10:50"), cliente2, endereco2);
		
		Pagamento pagamento1 = new PagamentoComCartao(EstadoPagamento.QUITADO, pedido1, 2);
		pedido1.setPagamento(pagamento1);
		Pagamento pagamento2 = new PagamentoComBoleto(EstadoPagamento.PENDENTE, pedido2, sdf.parse("01/01/2022 00:00"),new Date());
		pedido2.setPagamento(pagamento2);
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1));
		cliente2.getPedidos().addAll(Arrays.asList(pedido2));
		
		pedidoRepo.saveAll(Arrays.asList(pedido1,pedido2));
		pagamentoRepo.saveAll(Arrays.asList(pagamento1,pagamento2));
		
	}
}
