package com.example.aluguelautomoveis.Controller;

import com.example.aluguelautomoveis.DAO.AdministradorDAO;
import com.example.aluguelautomoveis.DAO.AgenteDAO;
import com.example.aluguelautomoveis.DAO.ClienteDAO;
import com.example.aluguelautomoveis.DAO.UsuarioDAO;
import com.example.aluguelautomoveis.Model.Usuario;
import com.google.gson.Gson;
import org.hibernate.mapping.Map;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UsuarioController {
    UsuarioDAO dao = new UsuarioDAO();
    Gson gson = new Gson();

    @PostMapping ("/usuario/add")
    public String add(@RequestBody Usuario usuario) {
        var usuarioAdicionado = dao.add(usuario);

        return gson.toJson(usuarioAdicionado);
    }

    @GetMapping ("/usuario/{id}")
    public String get(@PathVariable("id") int id) {
        var usuario = dao.get(id);
        var json = gson.toJson(usuario);

        return json;
    }

    @GetMapping ("/usuario")
    public String getAll() {
        var usuarios = dao.getAll();
        var json = gson.toJson(usuarios);

        return json;
    }

    @PutMapping ("/usuario/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Usuario usuario) {
        dao.update(id, usuario.getEndereco(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }

    @DeleteMapping ("/usuario/{id}")
    public void delete(@PathVariable("id") int id) {
        dao.delete(id);
    }

    @PostMapping ("/usuario/login")
    public String login(@RequestBody String responseBody) {
        HashMap<String, String> login = gson.fromJson(responseBody, HashMap.class);
        var administradorDAO = new AdministradorDAO();
        var agenteDAO = new AgenteDAO();
        var clienteDAO = new ClienteDAO();

        String email = login.get("email");
        String senha = login.get("senha");

        var usuario = dao.getByCredentials(email, senha);
        var response = new HashMap<String, String>();

        if (usuario == null) {
            return gson.toJson(response);
        }

        administradorDAO.getAll().forEach(administrador -> {
            if (administrador.getUsuarioID() == usuario.getId()) {
                response.put("tipo", "administrador");
                response.put("id", "" + administrador.getId());
            }
        });

        agenteDAO.getAll().forEach(agente -> {
            if (agente.getUsuarioID() == usuario.getId()) {
                response.put("tipo", "agente");
                response.put("id", "" + agente.getId());
            }
        });

        clienteDAO.getAll().forEach(cliente -> {
            if (cliente.getUsuarioID() == usuario.getId()) {
                response.put("tipo", "cliente");
                response.put("id", "" + cliente.getId());
            }
        });

        return gson.toJson(response);
    }
}
