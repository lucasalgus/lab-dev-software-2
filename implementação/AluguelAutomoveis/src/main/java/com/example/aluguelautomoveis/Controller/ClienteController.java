package com.example.aluguelautomoveis.Controller;

import com.example.aluguelautomoveis.DAO.ClienteDAO;
import com.example.aluguelautomoveis.Model.Cliente;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ClienteController {
    ClienteDAO dao = new ClienteDAO();
    Gson gson = new Gson();

    @PostMapping("/cliente/add")
    public String add(@RequestBody Cliente cliente) {
        var clienteAdicionado = dao.add(cliente);

        return gson.toJson(clienteAdicionado);
    }

    @GetMapping("/cliente/{id}")
    public String get(@PathVariable("id") int id) {
        var cliente = dao.get(id);
        var json = gson.toJson(cliente);

        return json;
    }

    @GetMapping ("/cliente")
    public String getAll() {
        var clientes = dao.getAll();
        var json = gson.toJson(clientes);

        return json;
    }

    @PutMapping("/cliente/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Cliente cliente) {
        dao.update(id, cliente.getRg(), cliente.getCpf(), cliente.getProfissao(), cliente.getUsuarioID());
    }

    @DeleteMapping ("/cliente/{id}")
    public void delete(@PathVariable("id") int id) {
        dao.delete(id);
    }
}
