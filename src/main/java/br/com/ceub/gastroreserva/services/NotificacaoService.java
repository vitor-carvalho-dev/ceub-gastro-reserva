package br.com.ceub.gastroreserva.services;

import br.com.ceub.gastroreserva.entities.Notificacao;
import br.com.ceub.gastroreserva.entities.Usuario;
import br.com.ceub.gastroreserva.enums.TipoNotificacao;
import br.com.ceub.gastroreserva.repository.NotificacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoService {
    private final NotificacaoRepository notificacaoRepository;
    private final MailSenderService mailSenderService;


    public void enviarNotificacao(Usuario usuario, String mensagem) {
        var notificacao = Notificacao
                .builder()
                .mensagem(mensagem)
                .tipoNotificacao(TipoNotificacao.EMAIL)
                .usuario(usuario)
                .build();
        notificacaoRepository.save(notificacao);
        mailSenderService.sendEmail(usuario.getEmail(), "reserva realizada com sucesso", mensagem);
    }
}
