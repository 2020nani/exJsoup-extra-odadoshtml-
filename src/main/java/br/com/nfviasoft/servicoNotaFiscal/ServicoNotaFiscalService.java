package br.com.nfviasoft.servicoNotaFiscal;

import br.com.nfviasoft.application.dto.ServicoNotaFiscalDto.ServicoNotaFiscalRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicoNotaFiscalService {

    ServicoNotaFiscal save(ServicoNotaFiscalRequest request);

    ServicoNotaFiscal filtraPorEstado(String estado);

    List<ServicoNotaFiscal> filtraPorData(String data);

    List<ServicoNotaFiscal> findAll();

    List<ServicoNotaFiscal> filtraPorMaiorIndisponibilidade();
}
