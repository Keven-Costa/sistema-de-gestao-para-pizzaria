package com.company.pizzaria.controller.admin;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.pizzaria.dto.PedidoDTO;
import com.company.pizzaria.model.entity.ItemPedido;
import com.company.pizzaria.model.entity.Pedido;
import com.company.pizzaria.model.enums.StatusPedidos;
import com.company.pizzaria.service.PedidoService;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    private final PedidoService pedidoService;

    // Injeção via construtor
    public DashboardController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/dashboard")
    public String exibirPaginaDashboard(Model model) {
        int pedidosHoje = pedidoService.contarPedidosHoje();
        BigDecimal valorTotal = pedidoService.valorTotalPedidosHoje();
        List<Pedido> ultimosPedidos = pedidoService.ultimosPedidos();

        // Converter para DTO se necessário
        List<PedidoDTO> ultimosPedidosDTO = ultimosPedidos.stream().map(p -> {
            PedidoDTO dto = new PedidoDTO();
            dto.setCodigo("#" + p.getId());
            dto.setSabores(
                p.getItens().stream()
                  .map(ItemPedido::getNome)
                  .collect(Collectors.joining(", "))
            );
            dto.setStatusCss(obterStatusCss(p.getStatus()));
            dto.setStatusTexto(p.getStatus().getDescricao());
            return dto;
        }).toList();

        model.addAttribute("pedidosHoje", pedidosHoje);
        model.addAttribute("valorTotal", valorTotal);
        model.addAttribute("ultimosPedidos", ultimosPedidosDTO);

        // TODO: adicionar dados do gráfico de sabores mais vendidos

        return "admin/dashboard";
    }

    private String obterStatusCss(StatusPedidos status) {
        return switch (status) {
            case PROCESSANDO -> "status-preparo";
            case ENTREGUE -> "status-entregue";
            case ENVIADO -> "status-enviado";
            case CANCELADO -> "status-cancelado";
            default -> "status-pendente";
        };
    }
}