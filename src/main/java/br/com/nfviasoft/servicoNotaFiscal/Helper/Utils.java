package br.com.nfviasoft.servicoNotaFiscal.Helper;

import br.com.nfviasoft.servicoNotaFiscal.ServicoNotaFiscal;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class Utils {

    public boolean filtroStatusAtual(ServicoNotaFiscal servicoNotaFiscal) {

        var dataSalva = servicoNotaFiscal.getMomentoCriacao().getHour()+servicoNotaFiscal.getMomentoCriacao().getMinute();
        var  dataAtual = LocalDateTime.now().getHour() + LocalDateTime.now().getMinute() - 5;
        Boolean confirmaStatusAtual = dataSalva >= dataAtual ? true : false ;

        return confirmaStatusAtual;
    }

    public boolean filtroPorData(String data, ServicoNotaFiscal servicoNotaFiscal){
        var dia = servicoNotaFiscal.getMomentoCriacao().getDayOfMonth();
        var mes = servicoNotaFiscal.getMomentoCriacao().getMonthValue();
        var ano = servicoNotaFiscal.getMomentoCriacao().getYear();
        if(dia == Integer.parseInt(data.substring(0,2)) &&
           mes == Integer.parseInt(data.substring(2,4)) &&
           ano == Integer.parseInt(data.substring(4,8))) {
            return true;
        }

        return false;
    }


}
