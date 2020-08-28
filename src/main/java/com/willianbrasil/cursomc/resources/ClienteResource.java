package com.willianbrasil.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.willianbrasil.cursomc.domain.Cliente;
import com.willianbrasil.cursomc.dto.ClienteDTO;
import com.willianbrasil.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	
	//METODO PUT
		@RequestMapping(value="/{id}", method=RequestMethod.PUT)
		public ResponseEntity<Void> update(@RequestBody @Valid ClienteDTO objDto, @PathVariable Integer id){
			Cliente obj = service.fromDTO(objDto);
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}
		
		//METODO DELETE
		@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<Cliente> delete(@PathVariable Integer id){
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
		 //METODO PARA LISTAR AS CATEGORIAS
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<ClienteDTO>> findAll() {
			List<Cliente> list = service.findAll();
			List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);
			
		}
		
		//METODO PARA PAGINACAO OPCIONAL DE REQUISICAO
		@RequestMapping(value ="/page", method=RequestMethod.GET)
		public ResponseEntity<Page<ClienteDTO>> findPage(
			  @RequestParam(value="page", defaultValue="0")	Integer page,
			  @RequestParam(value="linesPerPage", defaultValue="24")	Integer linesPerPage,
			  @RequestParam(value="orderBy", defaultValue="nome")String orderBy,
			  @RequestParam(value="direction", defaultValue="ASC")String direction) {
			Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
			Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
			return ResponseEntity.ok().body(listDto);
		
		}

}
