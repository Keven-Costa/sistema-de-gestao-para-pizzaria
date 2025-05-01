// Simulação: Mostrar detalhes do entregador quando o pedido "sai para entrega"
// Na implementação real, isso seria controlado pelo status real do pedido

alert("salve")
setTimeout(function () {
    document.getElementById('deliveryPerson').style.display = 'block';
    document.querySelector('.progress-step:nth-child(3)').classList.add('active');
    document.querySelector('.progress-bar-fill').style.width = '75%';
}, 5000); // Apenas para demonstração - remover em produção