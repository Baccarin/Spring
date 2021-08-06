package com.baccarin.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.baccarin.cursomc.domain.Cliente;
import com.baccarin.cursomc.dto.ClienteDTO;
import com.baccarin.cursomc.repositories.ClienteRepository;
import com.baccarin.cursomc.services.exceptions.DataIntegrityExcpetion;
import com.baccarin.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepo;

	public Cliente find(Integer id) {
		Optional<Cliente> cliente = clienteRepo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())));

	}

	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return clienteRepo.save(cliente);
	}

	public Cliente update(Cliente cliente) {
		Cliente clienteNew = find(cliente.getId());
		this.updateData(clienteNew, cliente);
		return clienteRepo.save(clienteNew);
	}
	

	public void delete(Integer id) {
		find(id);
		try {
			clienteRepo.deleteById(id);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityExcpetion("Não é possível excluir uma categoria que possua produtos relacionados");
		}
	}
	
	public List<Cliente> findAll(){
		return clienteRepo.findAll();
	}
	
	public Page<Cliente> findPageCliente(Integer page, Integer linesPerPage, String orderBy , String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage ,Direction.valueOf(direction), orderBy);
		return clienteRepo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}	

	private void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());
		newCliente.setCpfOuCnpj(cliente.getCpfOuCnpj());
		newCliente.setTipo(cliente.getTipo() != null ? cliente.getTipo() : null);
	}

}
