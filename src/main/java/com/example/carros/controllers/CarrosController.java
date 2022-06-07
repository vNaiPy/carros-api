package com.example.carros.controllers;

import com.example.carros.controllers.dto.CarroDTO;
import com.example.carros.domain.models.Carro;
import com.example.carros.domain.services.CarroService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    private CarroService _carroService;

    public CarrosController(CarroService carroService) {
        this._carroService = carroService;
    }

    @GetMapping("/list-carro")
    public ResponseEntity<List<CarroDTO>> listCarro(){
        return ResponseEntity.ok(_carroService.listCarro());
        //return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
    }

    @GetMapping("/get-carro-by-id")
    public ResponseEntity getCarroById(@RequestParam("id") Long id){
        Optional<CarroDTO> carro = _carroService.getCarroById(id);

        return carro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

//        return carro.isPresent()
//                ? ResponseEntity.ok(carro.get())
//                : ResponseEntity.notFound().build();
    }

    @GetMapping("/list-carro-by-tipo")
    public ResponseEntity<List<CarroDTO>> listCarroByTipo(@RequestParam("tipo") String tipo){
        List<CarroDTO> carros = _carroService.listCarroByTipo(tipo);

        return carros.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(carros);
    }

    @GetMapping("/list-carro-by-current-user")
    public ResponseEntity<List<CarroDTO>> listCarroByCurrentUser(){
        List<CarroDTO> carros = _carroService.listCarroByCurrentUser();

        return carros.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(carros);
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity addCarro(@RequestBody Carro carro){
        try{
            CarroDTO carroDTO = _carroService.addCarro(carro);

            URI location = getUri(carroDTO.getId());
            return ResponseEntity.created(location).build();
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/update-carro")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity updateCarro(@RequestParam("id") Long id, @RequestBody Carro carro){

        carro.setId(id);

        CarroDTO carroDTO = _carroService.updateCarro(id, carro);
        return carroDTO != null
                ? ResponseEntity.ok(carroDTO)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/remove-carro")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity removeCarro(@RequestParam("id") Long id){
        boolean carroRemove = _carroService.removeCarro(id);

        return carroRemove ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }
}
