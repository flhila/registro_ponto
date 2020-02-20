package br.com.itau.pontoeletronico.presenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.itau.pontoeletronico.models.RegistroPonto;
import br.com.itau.pontoeletronico.utils.RegistroPontoUtil;

public class RegistrosPontoUsuarioPresenter {
	
	private List<RegistroPontoPresenter> registros;
	
	private long totalHorasTrabalhadas;

	public RegistrosPontoUsuarioPresenter(List<RegistroPonto> registrosList) {
		if (!registrosList.isEmpty()) {
			registros = new ArrayList<RegistroPontoPresenter>();
			for (Iterator<RegistroPonto> iterator = registrosList.iterator(); iterator.hasNext();) {
				RegistroPonto registroPonto = (RegistroPonto) iterator.next();
				registros.add(new RegistroPontoPresenter(registroPonto));	
			}
			this.totalHorasTrabalhadas = RegistroPontoUtil.calculaTotalHorasTrabalhadas(registros);
		}
	}

	public List<RegistroPontoPresenter> getRegistros() {
		return registros;
	}

	public void setRegistros(List<RegistroPontoPresenter> registros) {
		this.registros = registros;
	}

	public long getTotalHorasTrabalhadas() {
		return totalHorasTrabalhadas;
	}

	public void setTotalHorasTrabalhadas(long totalHorasTrabalhadas) {
		this.totalHorasTrabalhadas = totalHorasTrabalhadas;
	}

}
