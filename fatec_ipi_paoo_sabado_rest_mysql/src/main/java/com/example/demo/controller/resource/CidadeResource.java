package com.example.demo.controller.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.bean.Cidade;	
import com.example.demo.model.repository.CidadeRepository;

@RestController
@RequestMapping ("/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@GetMapping ("/lista")
	public List <Cidade> todosAsCidades () {
		return cidadeRepo.findAll();
	}
	
	@PostMapping ("/salvar")
	public ResponseEntity<Cidade> salvar (@RequestBody Cidade cidade, HttpServletResponse response) {
		Cidade c = cidadeRepo.save(cidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}").buildAndExpand(c.getId()).toUri();
		return ResponseEntity.created(uri).body(c);
	}
	
	@GetMapping ("/primeiraLetra/{nome}")
	public List<Cidade> buscarPeloNome (@PathVariable String nome) {
		return cidadeRepo.findByPrimeiraLetra(nome);
	}	

	@GetMapping ("/Coordenada/{latitude}/{longitude}")
	public List<Cidade> buscarPelaCoordenada (@PathVariable String latitude, @PathVariable String longitude) {
		return cidadeRepo.findByLatitudeLongitude(latitude, longitude);
	}	
	
}
