package br.com.targettrust.locadora.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import br.com.targettrust.locadora.entidades.Equipamento;
import br.com.targettrust.locadora.exception.EquipamentoJaCadastradoException;
import br.com.targettrust.locadora.util.DbUtil;

public class EquipamentoRepositoryImpl implements EquipamentoRepository{

	@Override
	public void insert(Equipamento equipamento) {
		if(equipamento.getId() != null) {
			throw new IllegalArgumentException("Para inclusão de veículo o id "
					+ " não deve ser informado");
		}
		if(equipamento.getDescricao() == null ||
				equipamento.getDescricao().trim().equals("")) {
			throw new IllegalArgumentException("O campo descrição é obrigatório");
		}
		
		String sql = "insert into EQUIPAMENTO (DESCRICAO) values ( ? )";
		try {
			Connection connection = DbUtil.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, equipamento.getDescricao());
			ps.executeUpdate();
		}
		catch (PSQLException e) {
			throw new EquipamentoJaCadastradoException();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Equipamento equipamento) {
		// TODO Auto-generated method stub
		String sql = "update EQUIPAMENTO set descricao = ? "
				+ " where id = ?";
		try {
			Connection connection = DbUtil.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, equipamento.getDescricao());
			ps.setInt(2, equipamento.getId());
			ps.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Equipamento equipamento) {
		// TODO Auto-generated method stub
		String sql = "delete from EQUIPAMENTO where id = ?";
		try {
			Connection connection = DbUtil.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, equipamento.getId());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Equipamento> list() {
		// TODO Auto-generated method stub
		String sql = "select * from EQUIPAMENTO";
		try {
			Connection connection = DbUtil.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			List<Equipamento> equipamentos = new ArrayList<>();
			while(rs.next()) {
				Equipamento equipamento = new Equipamento();
				equipamento.setId(rs.getInt("id"));
				equipamento.setDescricao(rs.getString("descricao"));
				equipamentos.add(equipamento);
			}
			return equipamentos;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Equipamento findById(Integer id) {
		String sql = "select * from EQUIPAMENTO where id = ?";
		try {
			Connection connection = DbUtil.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Equipamento equipamento = new Equipamento();
				equipamento.setId(rs.getInt("id"));
				equipamento.setDescricao(rs.getString("descricao"));
				return equipamento;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Equipamento findByDescricao(String descricao) {
		String sql = " select * from EQUIPAMENTO where DESCRICAO = ? "; 
		try {
			Connection connection = DbUtil.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, descricao);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Equipamento equipamento = new Equipamento();
				equipamento.setId(rs.getInt("id"));
				equipamento.setDescricao(rs.getString("descricao"));
				return equipamento;				
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
