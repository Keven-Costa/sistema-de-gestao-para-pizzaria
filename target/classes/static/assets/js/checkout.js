// =============================================
// Variáveis Globais e Inicialização
// =============================================
document.addEventListener("DOMContentLoaded", () => {
    selectPayment('dinheiro');
    carregarItensCarrinho();

    // Configurar evento para troco
    const needChangeCheckbox = document.getElementById('need-change');
    if (needChangeCheckbox) {
        needChangeCheckbox.addEventListener('change', function () {
            const input = document.getElementById('change-input');
            input.style.display = this.checked ? 'flex' : 'none';
        });
    }
});

// =============================================
// Funções de Pagamento
// =============================================
function selectPayment(method) {
    // Remover seleção de todos os métodos
    document.querySelectorAll('.payment-method').forEach(el => {
        el.classList.remove('selected');
    });

    // Adicionar seleção ao método clicado
    const metodoDiv = document.getElementById(`${method}-payment`);
    if (metodoDiv) {
        metodoDiv.classList.add('selected');
    }

    // Marcar o radio button correspondente
    const radio = document.getElementById(`${method}`);
    if (radio) {
        radio.checked = true;
    }

    // Mostrar/ocultar seção de troco
    const changeSection = document.getElementById('change-section');
    if (changeSection) {
        changeSection.style.display = method === 'dinheiro' ? 'block' : 'none';
    }
}

// =============================================
// Funções de CEP
// =============================================
function limparEndereco() {
    document.getElementById('rua').value = "";
    document.getElementById('bairro').value = "";
}

function callbackCEP(conteudo) {
    if (!("erro" in conteudo)) {
        document.getElementById('rua').value = conteudo.logradouro;
        document.getElementById('bairro').value = conteudo.bairro;
    } else {
        limparEndereco();
        alert("CEP não encontrado.");
    }
}

function pesquisarCEP(valor) {
    const cep = valor.replace(/\D/g, '');

    if (cep) {
        const validacep = /^[0-9]{8}$/;

        if (validacep.test(cep)) {
            document.getElementById('rua').value = "...";
            document.getElementById('bairro').value = "...";

            const script = document.createElement('script');
            script.src = `https://viacep.com.br/ws/${cep}/json/?callback=callbackCEP`;
            document.body.appendChild(script);
        } else {
            limparEndereco();
            alert("Formato de CEP inválido.");
        }
    } else {
        limparEndereco();
    }
}

// =============================================
// Funções do Carrinho
// =============================================
function carregarItensCarrinho() {
    const itensSalvos = localStorage.getItem("carrinho");
    if (!itensSalvos) return;

    // Obter desconto do cupom se existir
    const descontoSalvo = localStorage.getItem("descontoCupom");
    const descontoCupom = descontoSalvo ? parseFloat(descontoSalvo) : 0;

    const itens = JSON.parse(itensSalvos);
    const listaCheckout = document.getElementById("lista-checkout");
    let total = 0;

    listaCheckout.innerHTML = ""; // Limpa a lista

    itens.forEach((item) => {
        const precoUnitario = item.preco;
        const subtotal = item.quantidade * precoUnitario;
        total += subtotal;

        listaCheckout.innerHTML += `
            <li class="list-group-item d-flex justify-content-between align-items-center">
                <div class="item">
                    <h6 class="m-0">${item.nome} (${item.quantidade}x)</h6>
                </div>
                <span>R$ ${precoUnitario.toFixed(2)} = R$ ${subtotal.toFixed(2)}</span>
            </li>
        `;
    });

    // Adicionar linha de desconto se houver cupom aplicado
    if (descontoCupom > 0) {
        listaCheckout.innerHTML += `
            <li class="list-group-item d-flex justify-content-between align-items-center text-success">
                <div class="item">
                    <h6 class="m-0">Desconto (cupom)</h6>
                </div>
                <span>- R$ ${descontoCupom.toFixed(2)}</span>
            </li>
        `;
    }

    // Adiciona taxa de entrega
    const taxaEntrega = 5.00;
    listaCheckout.innerHTML += `
        <li class="list-group-item d-flex justify-content-between align-items-center">
            <div class="item">
                <h6 class="m-0">Taxa de Entrega</h6>
            </div>
            <span>R$ ${taxaEntrega.toFixed(2)}</span>
        </li>
    `;

    // Calcular total final com desconto
    const totalFinal = Math.max(0, total + taxaEntrega - descontoCupom);
    document.getElementById("total").textContent = `R$ ${totalFinal.toFixed(2)}`;
}

// =============================================
// Envio do Formulário
// =============================================

async function enviarFormulario() {

    // Obter desconto do cupom
    const descontoSalvo = localStorage.getItem("descontoCupom");
    const descontoCupom = descontoSalvo ? parseFloat(descontoSalvo) : 0;

    // Extrair valor total do pedido
    const totalText = document.getElementById("total").textContent;
    const precoTotal = parseFloat(
        totalText.replace("R$", "").trim().replace(',', '.')
    );

    // Coletar dados do formulário
    const formData = {
        nome: document.getElementById("nome").value,
        cep: document.getElementById("cep").value,
        rua: document.getElementById("rua").value,
        numero: document.getElementById("numero").value,
        bairro: document.getElementById("bairro").value,
        telefone: document.getElementById("telefone").value,
        referencia: document.getElementById("referencia").value || null,
        observacoes: document.getElementById("observacoes").value || null,
        desconto: descontoCupom,
        preco_total: precoTotal
    };

    // Processar pagamento
    const metodoPagamento = document.querySelector('input[name="payment"]:checked')?.id;
    formData.pagamento = { tipo: metodoPagamento };

    if (metodoPagamento === 'dinheiro' && document.getElementById('need-change').checked) {
        const trocoInput = document.querySelector('#change-input input').value;
        formData.pagamento.trocoPara = trocoInput ? parseFloat(trocoInput) : 0;
    }

    // Processar itens do carrinho
    const carrinho = JSON.parse(localStorage.getItem("carrinho")) || [];
    formData.itens = carrinho.map(item => ({
        nome: item.nome,
        quantidade: item.quantidade,
        preco: item.preco
    }));

    // Adicionar taxa de entrega
    formData.itens.push({
        nome: "Taxa de Entrega",
        quantidade: 1,
        preco: 5.00
    });

    try {
        // Enviar para o backend
        const response = await fetch("/checkout/salvar", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formData)
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.error || "Erro ao enviar pedido.");
        }

        const data = await response.json();
        alert(data.message || "Pedido enviado com sucesso!");

        // Limpar carrinho e pedido anterior
        localStorage.removeItem("carrinho");
        localStorage.removeItem("ultimoPedido");

        // Salvar resumo, se houver
        if (data.resumo) {
            localStorage.setItem("resumoPedido", JSON.stringify(data.resumo));
        }

        // Redirecionar, se houver URL
        if (data.redirectUrl) {
            window.location.href = data.redirectUrl;
        }

    } catch (error) {
        console.error("Erro:", error);
        alert("Falha ao enviar pedido: " + error.message);
    }
}
