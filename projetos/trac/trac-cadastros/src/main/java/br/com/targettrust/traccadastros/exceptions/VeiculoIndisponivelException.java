package br.com.targettrust.traccadastros.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NO_CONTENT, 
reason = "O veículo solicitado não está disponível nas datas")
public class VeiculoIndisponivelException extends RuntimeException {

}
