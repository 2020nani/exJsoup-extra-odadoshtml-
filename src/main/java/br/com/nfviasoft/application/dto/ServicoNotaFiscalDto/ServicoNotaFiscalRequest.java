package br.com.nfviasoft.application.dto.ServicoNotaFiscalDto;

import br.com.nfviasoft.servicoNotaFiscal.ServicoNotaFiscal;
import lombok.Getter;
import lombok.ToString;
import org.jsoup.nodes.Element;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@Getter
@ToString
public class ServicoNotaFiscalRequest {

    private String estado;
    private String autorizacao4;
    private String retornoAutorizacao4;
    private String inutilizacao4;
    private String consultaProtocolo4;
    private String statusServico4;
    private String tempoMedio;
    private String consultaCadastro4;
    private String recepcaoEvento4;
    private Integer statusIndisponivel = 0;


    public ServicoNotaFiscalRequest(Element element) {
        this.estado = element.getElementsByTag("td").first().text();
        this.autorizacao4 = statusService(element.getElementsByTag("td").get(1));
        this.retornoAutorizacao4 = statusService(element.getElementsByTag("td").get(2));
        this.inutilizacao4 = statusService(element.getElementsByTag("td").get(3));
        this.consultaProtocolo4 = statusService(element.getElementsByTag("td").get(4));
        this.statusServico4 = statusService(element.getElementsByTag("td").get(5));
        this.tempoMedio = statusService(element.getElementsByTag("td").get(6));
        this.consultaCadastro4 = statusService(element.getElementsByTag("td").get(7));
        this.recepcaoEvento4 = statusService(element.getElementsByTag("td").get(8));
    }

    private String statusService(Element element) {

        if(!element.getElementsByTag("img").hasAttr("src")) {
            if(element.getElementsByTag("span").text() != "") {
                this.statusIndisponivel = this.getStatusIndisponivel() + 1;
                return "-";
            }
        }
        if(element.getElementsByTag("img").hasAttr("src")){
            if (String.valueOf(element.getElementsByTag("img").first().getElementsByAttribute("src")).contains("bola_amarela_P.png"))  return "amarela";
            if (String.valueOf(element.getElementsByTag("img").first().getElementsByAttribute("src")).contains("bola_vermelho_P.png"))  return "vermelho";
        }
        return "verde";
    }

    public ServicoNotaFiscal toServiceNotaFical() {

        return new ServicoNotaFiscal(
                estado,
                autorizacao4,
                retornoAutorizacao4,
                inutilizacao4,
                consultaProtocolo4,
                statusServico4,
                tempoMedio,
                consultaCadastro4,
                recepcaoEvento4,
                statusIndisponivel);
    }
}
