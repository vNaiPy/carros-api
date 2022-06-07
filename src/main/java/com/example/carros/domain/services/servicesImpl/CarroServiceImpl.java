package com.example.carros.domain.services.servicesImpl;

import com.example.carros.data.repository.CarroRepository;
import com.example.carros.controllers.dto.CarroDTO;
import com.example.carros.domain.models.Carro;
import com.example.carros.domain.models.User;
import com.example.carros.domain.services.CarroService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroServiceImpl implements CarroService {

    private CarroRepository carroRepository;

    public CarroServiceImpl(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public List<CarroDTO> listCarro(){
        return carroRepository.findAll()
                .stream().map(CarroDTO::createCarroDTO)
                .collect(Collectors.toList());
    }

    public Optional<CarroDTO> getCarroById(Long id){
        return carroRepository.findById(id).map(CarroDTO::createCarroDTO);
    }

    public List<CarroDTO> listCarroByTipo(String tipo) {
        return carroRepository.findByTipo(tipo)
                .stream().map(CarroDTO::createCarroDTO)
                .collect(Collectors.toList());
    }

    public List<CarroDTO> listCarroByCurrentUser() {
        User currentUser = getIdCurrentUser();

        return carroRepository.findByUserId(currentUser.getId())
                .stream().map(CarroDTO::createCarroDTO)
                .collect(Collectors.toList());
    }

    public CarroDTO addCarro(Carro carro) {
        Assert.isNull(carro.getId(),"Não foi possível inserir o registro");
        User currentUser = getIdCurrentUser();

        Carro novoCarro = new Carro();
        novoCarro.setNome(carro.getNome());
        novoCarro.setTipo(carro.getTipo());
        novoCarro.setUser(currentUser);

        return CarroDTO.createCarroDTO(carroRepository.save(novoCarro));
    }

    public CarroDTO updateCarro(Long id, Carro carro) {
        Assert.notNull(id, "Não foi possível atualizar o registro");

        Optional<Carro> optional = carroRepository.findById(id);
        if(optional.isPresent()){
            Carro carroDB = optional.get();
            carroDB.setNome(carro.getNome());
            carroDB.setTipo(carro.getTipo());

            carroRepository.save(carroDB);
            return CarroDTO.createCarroDTO(carroDB);
        }
        else return null;
    }

    public boolean removeCarro(Long id) {
        if(getCarroById(id).isPresent()){
            carroRepository.deleteById(id);
            return true;
        }
        else return false;
    }

    private User getIdCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
