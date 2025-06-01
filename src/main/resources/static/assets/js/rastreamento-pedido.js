// 1) Mapear exatamente as chaves do enum do back-end:
const STATUS = {
    PENDENTE: { step: 'stepRecebido', progress: '0%' },
    PROCESSANDO: { step: 'stepEmPreparo', progress: '33%' },
    ENVIADO: { step: 'stepSaiuEntrega', progress: '66%' },
    ENTREGUE: { step: 'stepEntregue', progress: '100%' },
    CANCELADO: { step: 'stepRecebido', progress: '0%' }  // ou tratar à parte
};

// 2) Ordem dos passos que existem no HTML:
const STATUS_ORDER = ['PENDENTE', 'PROCESSANDO', 'ENVIADO', 'ENTREGUE'];

// Função que busca o pedido e chama o update
async function updatePedidoStatus() {
    const id = getPedidoIdFromUrl();
    try {
        const resp = await fetch(`/rastreamento-pedido/${id}/rastreamento`);
        const pedido = await resp.json();
        updateUI(pedido);
        if (pedido.status !== 'ENTREGUE' && pedido.status !== 'CANCELADO') {
            setTimeout(updatePedidoStatus, 5000);
        }
    } catch {
        setTimeout(updatePedidoStatus, 10000);
    }
}

function updateUI(pedido) {
    // Atualiza texto simples
    document.getElementById('pedidoNumber').textContent = `Pedido #${pedido.id}`;
    document.getElementById('pedidoStatus').textContent = pedido.statusDescricao;

    // Previsão
    const previsaoEl = document.getElementById('previsaoEntrega');
    previsaoEl.textContent = pedido.previsaoEntrega || '—';

    // Barra de progresso
    const cfg = STATUS[pedido.status] || STATUS.PENDENTE;
    document.getElementById('progressBarFill').style.width = cfg.progress;

    // Passos
    const currentIndex = STATUS_ORDER.indexOf(pedido.status);
    const steps = ['stepRecebido', 'stepEmPreparo', 'stepSaiuEntrega', 'stepEntregue'];
    steps.forEach((stepId, idx) => {
        const el = document.getElementById(stepId);
        el.classList.toggle('completed', idx < currentIndex);
        el.classList.toggle('active', idx === currentIndex);
    });

    //  momentos específicos em que cada etapa
    document.getElementById('stepRecebidoTime').textContent = pedido.horaRecebido || '-';
    document.getElementById('stepEmPreparoTime').textContent = pedido.horaInicioPreparo || '-';
    document.getElementById('stepSaiuEntregaTime').textContent = pedido.horaSaida || '-';
    document.getElementById('stepEntregueTime').textContent = pedido.horaEntrega || '-';

    // Resumo
    const ul = document.getElementById('resumoPedido');
    ul.innerHTML = '';
    pedido.itens.forEach(item => {
        const li = document.createElement('li');
        li.className = 'list-group-item d-flex justify-content-between';
        li.innerHTML = `
        <span>${item.quantidade}x ${item.nome}</span>
        <span>R$ ${item.preco.toFixed(2).replace('.', ',')}</span>
      `;
        ul.appendChild(li);
    });
    document.getElementById('total').textContent =
        `R$ ${pedido.total.toFixed(2).replace('.', ',')}`;
}

function getPedidoIdFromUrl() {
    const parts = window.location.pathname.split('/');
    return parts[parts.length - 1];
}

document.addEventListener('DOMContentLoaded', updatePedidoStatus);
