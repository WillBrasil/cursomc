package com.willianbrasil.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.willianbrasil.cursomc.domain.Categoria;
import com.willianbrasil.cursomc.domain.Cidade;
import com.willianbrasil.cursomc.domain.Cliente;
import com.willianbrasil.cursomc.domain.Endereco;
import com.willianbrasil.cursomc.domain.Estado;
import com.willianbrasil.cursomc.domain.Produto;
import com.willianbrasil.cursomc.domain.enums.TipoCliente;
import com.willianbrasil.cursomc.repositories.CategoriaRepository;
import com.willianbrasil.cursomc.repositories.CidadeRepository;
import com.willianbrasil.cursomc.repositories.ClienteRepository;
import com.willianbrasil.cursomc.repositories.EnderecoRepository;
import com.willianbrasil.cursomc.repositories.EstadoRepository;
import com.willianbrasil.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// CADASTRO DE CATEGORIAS E SEUS PRODUTOS
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
				
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		
		//CADASTRO DE CIDADES E ESTADOS 
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2 , c3));
		
		//CADASTRO DE CLIENTES/TELEFONES/ENDERECOS
		Cliente cli1 = new Cliente(null, "Maria Silva", "Maria@Silva.com", "000888222", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("33229900", "32321112"));
		
		Endereco e1 = new Endereco(null, "Rua Das Pedras", "101", "Colegio", "Das Britas", "90888999", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "888999888", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		
	}

}
