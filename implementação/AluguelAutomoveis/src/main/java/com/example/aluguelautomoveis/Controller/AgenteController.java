package com.example.aluguelautomoveis.Controller;

import com.example.aluguelautomoveis.DAO.AgenteDAO;
import com.example.aluguelautomoveis.Model.Agente;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AgenteController {
    AgenteDAO dao = new AgenteDAO();
    Gson gson = new Gson();

    @PostMapping("/agente/add")
    public void add(@RequestBody Agente agente) {
        dao.add(agente);
    }

    @GetMapping("/agente/{id}")
    public String get(@PathVariable("id") int id) {
        var agente = dao.get(id);
        var json = gson.toJson(agente);

        return json;
    }

    @GetMapping ("/agente")
    public String getAll() {
        var agentes = dao.getAll();
        var json = gson.toJson(agentes);

        return json;
    }

    @PutMapping("/agente/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Agente agente) {
        dao.update(id, agente.getCnpj(), agente.getUsuarioID());
    }

    @DeleteMapping ("/agente/{id}")
    public void delete(@PathVariable("id") int id) {
        dao.delete(id);
    }
}
