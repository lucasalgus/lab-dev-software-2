package com.example.aluguelautomoveis.Controller;

import com.example.aluguelautomoveis.DAO.EmpregadorDAO;
import com.example.aluguelautomoveis.Model.Empregador;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EmpregadorController {
    EmpregadorDAO dao = new EmpregadorDAO();
    Gson gson = new Gson();

    @PostMapping("/empregador/add")
    public void add(@RequestBody Empregador empregador) {
        dao.add(empregador);
    }

    @GetMapping("/empregador/{id}")
    public String get(@PathVariable("id") int id) {
        var empregador = dao.get(id);
        var json = gson.toJson(empregador);

        return json;
    }

    @GetMapping ("/empregador")
    public String getAll() {
        var empregadores = dao.getAll();
        var json = gson.toJson(empregadores);

        return json;
    }

    @PutMapping("/empregador/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Empregador empregador) {
        dao.update(id, empregador.getNome(), empregador.getClienteID());
    }

    @DeleteMapping ("/empregador/{id}")
    public void delete(@PathVariable("id") int id) {
        dao.delete(id);
    }
}
