package com.aplicacao.cursomc.services;

import com.aplicacao.cursomc.domain.PagamentoComBoleto;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class BoletoService {

  public static void preencherPagamentoComBoleto(PagamentoComBoleto pagamentoComBoleto, Date instanteDoPedido) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(instanteDoPedido);
    calendar.add(Calendar.DAY_OF_MONTH,7);
    pagamentoComBoleto.setDatavencimento(calendar.getTime());
  }
}
