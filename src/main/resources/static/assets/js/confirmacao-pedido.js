document.addEventListener("DOMContentLoaded", () => {

    // Pega o caminho da URL, por exemplo: /confirmacao-pedido/32
    const path = window.location.pathname;

    // Divide a URL pelo "/" e pega a última parte (o ID)
    const parts = path.split("/");
    const pedidoId = parts[parts.length - 1]; 

    if (pedidoId) {
        const btnTrack = document.getElementById("btnTrack");
        if (btnTrack) {
            btnTrack.href = `/rastreamento-pedido/${pedidoId}`;
            console.log(`Botão de rastreamento atualizado para: ${btnTrack.href}`);
        }
    }

    localStorage.removeItem('ultimoPedido');

    try {
        const resumoPedido = carregarResumoPedido();
        exibirResumoPedido(resumoPedido);

        // Salva o pedido para a página de rastreamento
        const pedidoParaRastreamento = {
            ...resumoPedido,
            status: 0, // RECEBIDO
            numero: '#' + Math.floor(Math.random() * 9000 + 1000), // Número aleatório
            previsaoEntrega: calcularPrevisaoEntrega(),
            entregador: {
                nome: "João Silva",
                foto: "https://randomuser.me/api/portraits/men/32.jpg",
                placa: "XYZ-1234"
            }
        };

        localStorage.setItem('ultimoPedido', JSON.stringify(pedidoParaRastreamento));

        // Remove o resumo temporário
        localStorage.removeItem("resumoPedido");
    } catch (error) {
        console.error("Erro ao exibir resumo do pedido:", error);
        alert(error.message || "Ocorreu um erro ao carregar o pedido");
        window.location.href = "/";
    }
});

function calcularPrevisaoEntrega() {
    const now = new Date();
    const minTime = new Date(now.getTime() + 40 * 60000); // 40 minutos
    const maxTime = new Date(now.getTime() + 50 * 60000); // 50 minutos

    return `${minTime.getHours()}:${minTime.getMinutes().toString().padStart(2, '0')} - ${maxTime.getHours()}:${maxTime.getMinutes().toString().padStart(2, '0')}`;
}

function carregarResumoPedido() {
    const resumoJSON = localStorage.getItem("resumoPedido");

    if (!resumoJSON) {
        throw new Error("Nenhum pedido encontrado. Você será redirecionado para a página inicial.");
    }

    try {
        return JSON.parse(resumoJSON);
    } catch (parseError) {
        throw new Error("Formato de pedido inválido. Por favor, refaça seu pedido.");
    }
}

function exibirResumoPedido(resumo) {

    exibirCabecalhoPedido(resumo);

    exibirItensPedido(resumo);

    exibirInformacoesExtras(resumo);
}

function exibirCabecalhoPedido(resumo) {
    const numeroPedido = document.querySelector(".order-number");
    const tempoEntrega = document.querySelector(".delivery-time");

    numeroPedido.textContent = `Cliente: ${resumo.cliente}`;

    const dataPedido = new Date(resumo.dataPedido);
    const dataFormatada = dataPedido.toLocaleString("pt-BR", {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    });

    tempoEntrega.innerHTML = `
        <i class="fas fa-clock me-2"></i> 
        Pedido feito em <strong>${dataFormatada}</strong>
    `;
}

function exibirItensPedido(resumo) {
    const lista = document.querySelector(".order-details ul");
    lista.innerHTML = "";

    // Adicionar itens do pedido
    resumo.itens.forEach(item => {
        lista.appendChild(criarItemLista(item));
    });

    // Adicionar total
    lista.appendChild(criarTotalPedido(resumo.precoTotal));
}

function criarItemLista(item) {
    const li = document.createElement("li");
    li.classList.add("mb-2");
    li.innerHTML = `
        <strong>${item.quantidade}x ${item.nome}</strong> 
        - R$ ${item.preco.toFixed(2).replace('.', ',')}
    `;
    return li;
}

function criarTotalPedido(total) {
    const li = document.createElement("li");
    li.classList.add("pt-2", "mt-2", "border-top", "fw-bold");
    li.innerHTML = `Total: R$ ${total.toFixed(2).replace('.', ',')}`;
    return li;
}

function exibirInformacoesExtras(resumo) {
    const container = document.querySelector(".order-details");
    const divExtras = document.createElement("div");

    divExtras.classList.add("mt-4", "p-3", "bg-light", "rounded");
    divExtras.innerHTML = `
        <div class="mb-2">
            <i class="fas fa-map-marker-alt me-2 text-primary"></i>
            <strong>Endereço:</strong> ${resumo.endereco}
        </div>
        <div class="mb-2">
            <i class="fas fa-phone me-2 text-primary"></i>
            <strong>Telefone:</strong> ${resumo.telefone}
        </div>
        <div>
            <i class="fas fa-sticky-note me-2 text-primary"></i>
            <strong>Observações:</strong> ${resumo.observacoes || "Nenhuma"}
        </div>
    `;

    container.appendChild(divExtras);
}
