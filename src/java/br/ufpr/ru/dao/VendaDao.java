/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.ru.dao;

import br.ufpr.ru.jdbc.ConnectionFactory;
import br.ufpr.ru.modelo.Produto;
import br.ufpr.ru.modelo.Venda;
import br.ufpr.ru.modelo.VendaItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author fabio
 */
public class VendaDao implements IDao<Venda> {

    private Connection connection;

    public VendaDao() {
        this.connection = new ConnectionFactory().getConnection();
    }

    @Override
    public void inserir(Venda obj) {
        String sql = "insert into Venda (Checkin_id, TipoDeReceita_id, Caixa_id) values(?,?,?)";
        VendaItemDao vi = new VendaItemDao();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getCheckin().getId());
            stmt.setInt(2, obj.getTipoDeReceita().getId());
            stmt.setInt(3, obj.getCaixa().getId());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            int idVenda = 0;
            if (rs.next()) {
                idVenda = rs.getInt(1);

            }
            obj.setId(idVenda);
            vi.inserir(obj.getListaDeProdutos(), obj);

            stmt.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("erro no adiciona venda");
            throw new RuntimeException(e);
        }

    }

    @Override
    public void alterar(Venda obj) {
        String sql = "update Venda set Checkin_id=?, TipoDeReceita_id=?, Caixa_id=? where id=" + obj.getId();
        VendaItemDao vi = new VendaItemDao();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getCheckin().getId());
            stmt.setInt(2, obj.getTipoDeReceita().getId());
            stmt.setInt(3, obj.getCaixa().getId());
            stmt.execute();
            vi.alterar(obj.getListaDeProdutos(), obj);

            stmt.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("erro no alterar venda");
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deletar(Venda obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Venda buscar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venda> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venda> listarAtivos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venda> listar(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}