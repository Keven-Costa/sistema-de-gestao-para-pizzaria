
document.addEventListener('DOMContentLoaded', function () {
    // Mapeamento de status para progresso 
    const statusProgress = {
        'PENDENTE': 0,
        'PROCESSANDO': 33,  
        'ENVIADO': 66,
        'ENTREGUE': 100
    };

    // Configuração dos botões por status - ATUALIZADO
    const statusActions = {
        'PENDENTE': [
            { text: 'Iniciar Preparo', icon: 'fa-utensils', color: 'primary', action: 'PROCESSANDO' }, // Alterado para PROCESSANDO
            { text: 'Cancelar Pedido', icon: 'fa-times', color: 'danger', action: 'CANCELADO' }
        ],
        'PROCESSANDO': [  
            { text: 'Enviar para Entrega', icon: 'fa-truck', color: 'warning', action: 'ENVIADO' },
            { text: 'Voltar para Pendente', icon: 'fa-undo', color: 'secondary', action: 'PENDENTE' }
        ],
        'ENVIADO': [
            { text: 'Marcar como Entregue', icon: 'fa-check', color: 'success', action: 'ENTREGUE' },
            { text: 'Relatar Problema', icon: 'fa-exclamation-triangle', color: 'danger', action: 'PROBLEMA' }
        ],
        'ENTREGUE': [
            { text: 'Reabrir Pedido', icon: 'fa-redo', color: 'secondary', action: 'ENVIADO' }
        ],
        'CANCELADO': [
            { text: 'Reativar Pedido', icon: 'fa-redo', color: 'success', action: 'PENDENTE' }
        ]
    };

    // Event listener para os botões de ação
    document.querySelectorAll('.btn-action').forEach(button => {
        button.addEventListener('click', function () {
            const pedidoId = this.getAttribute('data-id');

            // Buscar detalhes do pedido do backend
            fetch(`/admin/gestao-pedidos/${pedidoId}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Erro ao buscar pedido');
                    }
                    return response.json();
                })
                .then(pedido => {
                    console.log("Dados recebidos:", pedido);

                    // Preencher informações do modal
                    document.getElementById('modalPedidoId').textContent = pedidoId;
                    document.getElementById('modalClienteNome').textContent = pedido.nome || 'Não informado';
                    document.getElementById('modalClienteTelefone').textContent = pedido.telefone || 'Não informado';
                    document.getElementById('modalClienteEndereco').textContent = pedido.endereco || 'Não informado';
                    document.getElementById('modalClienteReferencia').textContent = pedido.referencia || 'Nenhuma';

                    // Informações de pagamento
                    document.getElementById('modalPagamentoMetodo').textContent =
                        pedido.metodoPagamento?.tipo || 'Não especificado';
                    document.getElementById('modalPagamentoTotal').textContent =
                        `R$ ${pedido.valorTotal?.toFixed(2) || '0.00'}`;
                    document.getElementById('modalPagamentoTroco').textContent =
                        pedido.trocoPara ? `R$ ${pedido.trocoPara.toFixed(2)}` : '-';

                    // Preencher itens do pedido
                    const itensContainer = document.getElementById('modalItensPedido');
                    itensContainer.innerHTML = '';

                    if (pedido.itens && pedido.itens.length > 0) {
                        pedido.itens.forEach(item => {
                            const p = document.createElement('p');
                            p.className = 'mb-1';
                            p.textContent = `${item.quantidade}x ${item.nome} - R$ ${item.preco.toFixed(2)}`;
                            itensContainer.appendChild(p);
                        });
                    } else {
                        itensContainer.innerHTML = '<p class="text-muted">Nenhum item encontrado</p>';
                    }

                    // Barra de progresso
                    const progress = statusProgress[pedido.status] || 0;
                    document.getElementById('statusProgressBar').style.width = `${progress}%`;

                    // Gerar botões de ação
                    generateActionButtons(pedido.status, pedidoId);
                })
                .catch(error => {
                    console.error('Erro:', error);
                    alert('Erro ao carregar detalhes do pedido');
                });
        });
    });

    // Função para gerar botões de ação
    function generateActionButtons(status, pedidoId) {
        const container = document.getElementById('actionButtons');
        container.innerHTML = '';

        const actions = statusActions[status] || [];

        actions.forEach(action => {
            const button = document.createElement('button');
            button.className = `btn btn-${action.color} action-btn`;
            button.innerHTML = `<i class="fas ${action.icon} me-1"></i> ${action.text}`;
            button.onclick = () => handleStatusChange(action.action, pedidoId);
            container.appendChild(button);
        });
    }

    // Função para lidar com mudança de status
    function handleStatusChange(action, pedidoId) {
        let newStatus = '';
        let message = '';

        switch (action) {
            case 'PROCESSANDO':  
                newStatus = 'PROCESSANDO';
                message = 'Pedido em preparo!';
                break;
            case 'ENVIADO':
                newStatus = 'ENVIADO';
                message = 'Pedido enviado para entrega!';
                break;
            case 'ENTREGUE':
                newStatus = 'ENTREGUE';
                message = 'Pedido marcado como entregue!';
                break;
            case 'CANCELADO':
                newStatus = 'CANCELADO';
                message = 'Pedido cancelado com sucesso!';
                break;
            case 'PENDENTE':
                newStatus = 'PENDENTE';
                message = 'Pedido retornado para pendente!';
                break;
            case 'PROBLEMA':
                message = 'Problema relatado! Verifique as observações.';
                alert('Descreva o problema no campo de observações antes de concluir.');
                return;
        }

        if (newStatus) {
            fetch(`/admin/gestao-pedidos/${pedidoId}/status`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ status: newStatus })
            })
                .then(response => {
                    if (!response.ok) throw new Error('Erro ao atualizar status');
                    alert(message);
                    window.location.reload(); 
                })
                .catch(error => {
                    console.error('Erro:', error);
                    alert('Erro ao atualizar status do pedido');
                });
        }
    }

    // // Função para obter texto do status
    // function getStatusText(status) {
    //     const statusMap = {
    //         'PENDENTE': 'Pendente',
    //         'PROCESSANDO': 'Em preparo',  
    //         'ENVIADO': 'Enviado',
    //         'ENTREGUE': 'Entregue',
    //         'CANCELADO': 'Cancelado'
    //     };
    //     return statusMap[status] || status;
    // }

    // FILTROS E BUSCA 
    const rows = Array.from(document.querySelectorAll('.order-row'));
    const radios = document.querySelectorAll('input[name="statusFilter"]');
    const searchInput = document.getElementById('searchInput');
    const searchButton = document.getElementById('searchButton');

    function applyFilters() {
        const selectedFilter = document.querySelector('input[name="statusFilter"]:checked').id;
        const term = searchInput.value.trim().toLowerCase();

        rows.forEach(row => {
            const status = row.getAttribute('data-status') || '';
            const idText = row.children[0].textContent.trim().toLowerCase();
            const clientText = row.children[1].textContent.trim().toLowerCase();

            // filtro de status
            let showByStatus = false;
            if (selectedFilter === 'filter-all') {
                showByStatus = true;
            } else if (selectedFilter === 'filter-new' && status === 'NOVO') {
                showByStatus = true;
            } else if (selectedFilter === 'filter-preparing' && status === 'PROCESSANDO') {
                showByStatus = true;
            } else if (selectedFilter === 'filter-delivery' && status === 'ENVIADO') {
                showByStatus = true;
            } else if (selectedFilter === 'filter-delivered' && status === 'ENTREGUE') {
                showByStatus = true;
            }

            // filtro de busca
            const showBySearch = term === '' ||
                idText.includes(term) ||
                clientText.includes(term);

            row.style.display = (showByStatus && showBySearch) ? '' : 'none';
        });
    }

    // eventos de mudança nos radios
    radios.forEach(radio => {
        radio.addEventListener('change', applyFilters);
    });

    // busca ao clicar no botão
    searchButton.addEventListener('click', applyFilters);

    // busca ao digitar Enter
    searchInput.addEventListener('keyup', function (e) {
        if (e.key === 'Enter') applyFilters();
    });

    // aplica ao carregar
    applyFilters();
});
