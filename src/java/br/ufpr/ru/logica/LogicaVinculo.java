/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.ru.logica;

import java.util.List;
import br.ufpr.ru.dao.VinculoDao;
import br.ufpr.ru.modelo.Modalidade;
import br.ufpr.ru.modelo.Vinculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fabio
 */
@Service
public class LogicaVinculo implements CrudVinculo {

    private VinculoDao dao;

    @Autowired
    public LogicaVinculo(VinculoDao dao) {

        this.dao = dao;
    }

    @Override
    public void cadastraVinculo(Vinculo vinculo) {
        vinculo.setAtivo(true);
        dao.inserir(vinculo);
    }

    @Override
    public void alteraVinculo(Vinculo vinculo) {
        dao.alterar(vinculo);
    }

    @Override
    public void ativaVinculo(int id) {
        Vinculo v = dao.buscar(id);
        v.setAtivo(true);
        dao.alterar(v);
    }

    @Override
    public void desativaVinculo(int id) {
        Vinculo v = dao.buscar(id);
        v.setAtivo(false);
        dao.alterar(v);
    }

    @Override
    public Vinculo buscaVinculo(int id) {
        return dao.buscar(id);
    }

    @Override
    public List<Vinculo> lista(Modalidade modalidade) {
        return dao.listar(modalidade);
    }

    public List<Vinculo> lista() {
        return dao.listar();

    }

    @Override
    public List<Vinculo> listaAtivos() {
        return dao.listarAtivos();
    }

    @Override
    public List<Vinculo> listaAtivos(Modalidade modalidade) {
        return dao.listarAtivos(modalidade);
    }

}
