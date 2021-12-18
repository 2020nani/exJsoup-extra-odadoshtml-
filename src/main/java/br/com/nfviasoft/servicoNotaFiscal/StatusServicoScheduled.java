package br.com.nfviasoft.servicoNotaFiscal;

import br.com.nfviasoft.application.dto.ServicoNotaFiscalDto.ServicoNotaFiscalRequest;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@EnableScheduling
@Slf4j
public class StatusServicoScheduled {

    @Autowired
    private ServicoNotaFiscalService servicoNotaFiscalService;

    public StatusServicoScheduled(ServicoNotaFiscalService servicoNotaFiscalService) {
        this.servicoNotaFiscalService = servicoNotaFiscalService;
    }

    @Scheduled(fixedDelayString = "${periodicidade.salvar.historico.status}",
               initialDelayString = "${periodicidade.delay.historico.status}")
    public void guardarHistorico() throws IOException {

        log.info("O armazenamento de historico foi iniciado");
        List<ServicoNotaFiscal> historicoStatus = new ArrayList<>();
        Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
        Element tbody = doc.getElementsByTag("tbody").get(1);
        List<Element> dados = tbody.getElementsByTag("tr");

        dados.forEach(element -> {
            if(!element.getElementsByTag("th").hasAttr("scope")){
                ServicoNotaFiscalRequest servico = new ServicoNotaFiscalRequest(element);

                ServicoNotaFiscal statusServico = servicoNotaFiscalService.save(servico);

                log.info("Status salvo com sucesso");
            }

        });
    }

}
