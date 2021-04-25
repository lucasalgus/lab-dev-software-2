package com.example.aluguelautomoveis.Controller;

import com.example.aluguelautomoveis.DAO.AdministradorDAO;
import com.example.aluguelautomoveis.Model.Administrador;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AdministradorController {
    AdministradorDAO dao = new AdministradorDAO();
    Gson gson = new Gson();

    @PostMapping("/administrador/add")
    public void add(@RequestBody Administrador administrador) {
        dao.add(administrador);
    }

    @GetMapping("/administrador/{id}")
    public String get(@PathVariable("id") int id) {
        var administrador = dao.get(id);
        var json = gson.toJson(administrador);

        return json;
    }

    @GetMapping ("/administrador")
    public String getAll() {
        var administradores = dao.getAll();
        var json = gson.toJson(administradores);

        return json;
    }

    @PutMapping("/administrador/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Administrador administrador) {
        dao.update(id, administrador.getUsuarioID());
    }

    @DeleteMapping ("/administrador/{id}")
    public void delete(@PathVariable("id") int id) {
        dao.delete(id);
    }
}
