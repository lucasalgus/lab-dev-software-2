package com.example.aluguelautomoveis.Controller;

import com.example.aluguelautomoveis.DAO.AluguelDAO;
import com.example.aluguelautomoveis.Model.Aluguel;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AluguelController {
    AluguelDAO dao = new AluguelDAO();
    Gson gson = new Gson();

    @PostMapping("/aluguel/add")
    public void add(@RequestBody Aluguel aluguel) {
        dao.add(aluguel);
    }

    @GetMapping("/aluguel/{id}")
    public String get(@PathVariable("id") int id) {
        var aluguel = dao.get(id);
        var json = gson.toJson(aluguel);

        return json;
    }

    @GetMapping ("/aluguel")
    public String getAll() {
        var alugueis = dao.getAll();
        var json = gson.toJson(alugueis);

        return json;
    }

    @PutMapping("/aluguel/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Aluguel aluguel) {

        dao.update(id, aluguel.getContrato(), aluguel.isAtivo(), aluguel.getDataInicio(), aluguel.getDataFim(), aluguel.getAutomovelID(), aluguel.getClienteID(), aluguel.getAgenteID(), aluguel.getProprietarioID());
    }

    @DeleteMapping ("/aluguel/{id}")
    public void delete(@PathVariable("id") int id) {
        dao.delete(id);
    }
}
