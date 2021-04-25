package com.example.aluguelautomoveis.Controller;

import com.example.aluguelautomoveis.DAO.AutomovelDAO;
import com.example.aluguelautomoveis.Model.Automovel;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AutomovelController {
    AutomovelDAO dao = new AutomovelDAO();
    Gson gson = new Gson();

    @PostMapping("/automovel/add")
    public void add(@RequestBody Automovel automovel) {
        dao.add(automovel);
    }

    @GetMapping("/automovel/{id}")
    public String get(@PathVariable("id") int id) {
        var automovel = dao.get(id);
        var json = gson.toJson(automovel);

        return json;
    }

    @GetMapping ("/automovel")
    public String getAll() {
        var automoveis = dao.getAll();
        var json = gson.toJson(automoveis);

        return json;
    }

    @PutMapping("/automovel/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Automovel automovel) {
        dao.update(id, automovel.getAno(), automovel.getMarca(), automovel.getModelo(), automovel.getPlaca());
    }

    @DeleteMapping ("/automovel/{id}")
    public void delete(@PathVariable("id") int id) {
        dao.delete(id);
    }
}
