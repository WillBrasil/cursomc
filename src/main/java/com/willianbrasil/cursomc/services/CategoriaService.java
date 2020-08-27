package com.willianbrasil.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.willianbrasil.cursomc.domain.Categoria;
import com.willianbrasil.cursomc.repositories.CategoriaRepository;
import com.willianbrasil.cursomc.services.exceptions.DataIntegrityException;
import com.willianbrasil.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	//GET
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
			return	obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo " + Categoria.class.getName()));
	}
	
	//POST
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
		
	}
	
	//PUT
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	//DELETE
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não e possivel excluir uma categoria que possui produtos");
		}
	}

}
