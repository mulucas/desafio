package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dao.DaoGeneric;
import br.com.entidades.Registro;

@ViewScoped
@ManagedBean(name = "registroBean")
public class RegistroBean {

	private Registro registro = new Registro();
	private DaoGeneric<Registro> daoGeneric = new DaoGeneric<Registro>();
	private List<Registro> registros = new ArrayList<Registro>();

	public String salvar() {
		registro = daoGeneric.merge(registro);
		registro = new Registro();
		carregarRegistros();
		return "";
	}

	public String novo() {
		registro = new Registro();
		return "";
	}

	public String remover() {
		daoGeneric.deletarPorId(registro);
		registro = new Registro();
		carregarRegistros();
		return "";
	}

	@PostConstruct
	public void carregarRegistros() {
		registros = daoGeneric.getListEntity(Registro.class);
	}

	public Registro getRegistro() {
		return registro;
	}
	
	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public DaoGeneric<Registro> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Registro> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public List<Registro> getRegistros() {
		return registros;
	}
}
