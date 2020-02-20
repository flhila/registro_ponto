package br.com.itau.pontoeletronico.utils;

import java.time.temporal.ChronoUnit;
import java.util.List;

import br.com.itau.pontoeletronico.models.TipoRegistro;
import br.com.itau.pontoeletronico.presenter.RegistroPontoPresenter;

public class RegistroPontoUtil {
	
	public static long calculaTotalHorasTrabalhadas(List<RegistroPontoPresenter> registros) {
		long total = 0;
		
		for (RegistroPontoPresenter registroPontoPresenter : registros) {
			if (registroPontoPresenter.getTipoRegistro().equals(TipoRegistro.ENTRADA)) {
				
				boolean entradaEncontrada = false;
				for (RegistroPontoPresenter registroPontoPresenterSaida : registros) {
					if (entradaEncontrada) {
						if (registroPontoPresenterSaida.getTipoRegistro().equals(TipoRegistro.SAIDA)) {
							total += ChronoUnit.HOURS.between(registroPontoPresenter.getDataHora(), registroPontoPresenterSaida.getDataHora());
							break;
						}
					}
					if (registroPontoPresenterSaida.getId() == registroPontoPresenter.getId()) {
						entradaEncontrada = true;
					}
				}
			}
		}
		return total;
	}

}
