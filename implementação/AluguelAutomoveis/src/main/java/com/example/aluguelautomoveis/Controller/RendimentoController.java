package com.example.aluguelautomoveis.Controller;

import com.example.aluguelautomoveis.DAO.RendimentoDAO;
import com.example.aluguelautomoveis.Model.Rendimento;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RendimentoController {
    RendimentoDAO dao = new RendimentoDAO();
    Gson gson = new Gson();

    @PostMapping("/rendimento/add")
    public void add(@RequestBody Rendimento rendimento) {
        dao.add(rendimento);
    }

    @GetMapping("/rendimento/{id}")
    public String get(@PathVariable("id") int id) {
        var rendimento = dao.get(id);
        var json = gson.toJson(rendimento);

        return json;
    }

    @GetMapping ("/rendimento")
    public String getAll() {
        var rendimentos = dao.getAll();
        var json = gson.toJson(rendimentos);

        return json;
    }

    @PutMapping("/rendimento/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Rendimento rendimento) {
        dao.update(id, rendimento.getValor(), rendimento.getClienteID());
    }

    @DeleteMapping ("/rendimento/{id}")
    public void delete(@PathVariable("id") int id) {
        dao.delete(id);
    }
}
