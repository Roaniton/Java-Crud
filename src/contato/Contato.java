/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contato;

import banco.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JUASouza-Infi181
 */
public class Contato {

    int id;
    String nome;
    String telefone;

    public Contato() {
        this.id = 0;
        this.nome = null;
        this.telefone = null;
    }
    
    // construtor abaixo é utilizado na exclusão
    public Contato (int id) {
        this.id = id;
    }
    
    // construtor abaixo utilizado na inclusão
    public Contato (String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    // construtor abaixo utilizado ao atualizar dados
    public Contato (int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    public boolean incluir (){
        int r = 0;
        PreparedStatement st;
        String sql = "Insert Into Contato (nome,fone) values (?,?)";
        try {
            st = Banco.getCon().prepareStatement(sql);
            st.setString(1, getNome());
            st.setString(2, getTelefone());
            r = st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro!", JOptionPane.WARNING_MESSAGE);
        }
        return r==1;
    }
    
    public ArrayList<Contato> listaContato() {
        ArrayList<Contato> userList = new ArrayList<>();
        try {
            Connection conexao = banco.Banco.getCon();       
            String query = "SELECT * FROM contato";
            Statement st = conexao.createStatement();
            ResultSet rs2 = st.executeQuery(query);
            Contato contato;
            while (rs2.next()) {
                contato = new Contato(rs2.getInt("ID_CONTATO"), rs2.getString("nome"), rs2.getString("fone"));
                userList.add(contato);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return userList;
    }
    
    public boolean atualizar (){
        int r = 0;
        PreparedStatement st;
        String sql = "UPDATE contato set nome = ?, fone = ? WHERE ID_CONTATO = ?";
        try {
            st = Banco.getCon().prepareStatement(sql);
            st.setString(1, getNome());
            st.setString(2, getTelefone());
            st.setInt(3, getId());
            JOptionPane.showMessageDialog(null, "Contato atualizado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            r = st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return r==1;
    }
    
    public boolean deletar (){
        int r = 0;
        PreparedStatement st;
        String sql = "DELETE FROM contato WHERE ID_CONTATO = ?";
        if (JOptionPane.showConfirmDialog(null, "Você tem certeza que quer excluir?", "CUIDADO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                st = Banco.getCon().prepareStatement(sql);
                st.setInt(1, getId());
                r = st.executeUpdate();
                st.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Exclusão Cancelada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        return r==1;
    }
    
    
}
