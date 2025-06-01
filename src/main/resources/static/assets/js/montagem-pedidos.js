
// =============================================
// Variáveis Globais e Inicialização
// =============================================
let carrinho = JSON.parse(localStorage.getItem('carrinhoItens')) || [];
let descontoCupom = 0;

document.addEventListener('DOMContentLoaded', () => {
    // Limpar carrinho ao iniciar novo pedido
    carrinho = [];
    localStorage.removeItem('carrinho');
    localStorage.removeItem('carrinhoItens');
    atualizarCarrinho();
});

// =============================================
// Funções do Carrinho
// =============================================
function adicionarAoCarrinho(nome, preco) {
    const itemExistente = carrinho.find(item => item.nome === nome);
    if (itemExistente) {
        itemExistente.quantidade++;
    } else {
        carrinho.push({ nome, preco: Number(preco), quantidade: 1 });
    }
    salvarAtualizarCarrinho();
}

function removerDoCarrinho(nome) {
    const index = carrinho.findIndex(item => item.nome === nome);
    if (index !== -1) {
        if (carrinho[index].quantidade > 1) {
            carrinho[index].quantidade--;
        } else {
            carrinho.splice(index, 1);
        }
    }
    salvarAtualizarCarrinho();
}

function salvarAtualizarCarrinho() {
    localStorage.setItem('carrinhoItens', JSON.stringify(carrinho));
    atualizarCarrinho();
}

function atualizarCarrinho() {
    const itensCarrinho = document.getElementById('itens-carrinho');
    const totalCarrinho = document.getElementById('total-carrinho');
    let total = 0;
    itensCarrinho.innerHTML = carrinho.length === 0 ? '<p>Carrinho vazio</p>' : '';
    carrinho.forEach(item => {
        const subtotal = item.preco * item.quantidade;
        total += subtotal;
        const itemElement = document.createElement('div');
        itemElement.className = 'item-carrinho';
        itemElement.innerHTML = `
                <span>${item.nome} (${item.quantidade}x) - R$ ${subtotal.toFixed(2)}</span>
                <div>
                    <button class="btn btn-sm btn-outline-danger px-2 py-0" 
                            onclick="removerDoCarrinho('${item.nome.replace(/'/g, "\\'")}')">
                        <i class="fas fa-minus"></i>
                    </button>
                    <button class="btn btn-sm btn-outline-success px-2 py-0" 
                            onclick="adicionarAoCarrinho('${item.nome.replace(/'/g, "\\'")}', ${item.preco})">
                        <i class="fas fa-plus"></i>
                    </button>
                </div>
            `;
        itensCarrinho.appendChild(itemElement);
    });
    totalCarrinho.textContent = `R$ ${total.toFixed(2)}`;
    atualizarContadorItens();
}

function atualizarContadorItens() {
    const itemHtml = document.getElementById('quantidadeItens');
    if (itemHtml) {
        const totalItens = carrinho.reduce((sum, item) => sum + item.quantidade, 0);
        itemHtml.innerHTML = `
                <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-warning">
                    ${totalItens}
                </span>
            `;
    }
}

// =============================================
// Funções do Pedido
// =============================================
function adicionarAoPedido() {
    const sizeInput = document.querySelector('input[name="size"]:checked');
    const tamanho = sizeInput.getAttribute("data-size");
    const preco = parsearMoeda(sizeInput.getAttribute("data-price"));

    const nomePizza = document.getElementById("nomePizza").textContent.trim();

    document.getElementById("resumoTamanhoPizza").textContent = `${nomePizza} - ${tamanho}`;
    document.getElementById("resumoValorPizza").textContent = `R$ ${preco.toFixed(2).replace('.', ',')}`;
    calcularTotal();
}


function atualizarResumoPedido(checkbox, elementoResumoNome, elementoResumoValor, elementoLabelId, elementoValorId) {
    const labelProduto = document.getElementById(elementoLabelId);
    const nomeProduto = labelProduto.childNodes[0].nodeValue.trim();
    const valorTexto = document.getElementById(elementoValorId).textContent.trim();
    if (checkbox.checked) {
        document.getElementById(elementoResumoNome).textContent = nomeProduto;
        document.getElementById(elementoResumoValor).textContent = valorTexto;
    } else {
        document.getElementById(elementoResumoNome).textContent = "";
        document.getElementById(elementoResumoValor).textContent = "";
    }
    calcularTotal();
}

function atualizarBordaRecheada(checkbox) {
    atualizarResumoPedido(checkbox,
        'resumoAdcionalBordaRecheada',
        'resumoAdicionalValorBordaRecheada',
        'bordaRecheada',
        'valorBordaRecheada');
}

function atualizarMolhoExtra(checkbox) {
    atualizarResumoPedido(checkbox,
        'resumoAdcionalMolhoExtra',
        'resumoAdicionalValorMolhoExtra',
        'molhoExtra',
        'valorMolhoExtra');
}

function atualizarCocaCola(checkbox) {
    atualizarResumoPedido(checkbox,
        'resumoAdcionalCocaCola',
        'resumoAdicionalValorCocaCola',
        'CocaCola',
        'valorCocaCola');
}

// =============================================
// Cálculo de Preços
// =============================================
function parsearMoeda(value) {
    const parsed = parseFloat(
        value.replace('R$', '')
            .replace('+ R$', '')
            .replace(/[^\d,.-]/g, '')
            .replace(',', '.')
    );
    return isNaN(parsed) ? 0 : parsed;
}


function calcularTotal() {
    const elementosValor = document.querySelectorAll(`
        #resumoValorPizza,
        #resumoAdicionalValorBordaRecheada,
        #resumoAdicionalValorMolhoExtra,
        #resumoAdicionalValorCocaCola
    `);

    let total = [...elementosValor].reduce((sum, el) => {
        return el.textContent ? sum + parsearMoeda(el.textContent) : sum;
    }, 0);

    // Aplicar desconto do cupom se existir
    total = Math.max(0, total - descontoCupom);

    const textoFinal = total > 0 ? `R$ ${total.toFixed(2).replace('.', ',')}` : "R$ 0,00";
    document.getElementById('resumo-total').textContent = textoFinal;
    document.getElementById('total-carrinho').textContent = textoFinal;
    
    return total; // Retornamos o total para uso em outras funções
}


// =============================================
// Enviar Itens para o Carrinho
// =============================================
function pegarItensDoPedido() {
    // Pizza principal
    const nomePizza = document.getElementById("nomePizza").textContent.trim();
    const sizeInput = document.querySelector('input[name="size"]:checked');

    const tamanho = sizeInput.getAttribute("data-size");
    const preco = parsearMoeda(sizeInput.getAttribute("data-price"));

    adicionarAoCarrinho(`${nomePizza} - ${tamanho}`, preco);

    // Adicionais
    const adicionais = [
        { cb: 'stuffed-crust', label: 'bordaRecheada', valor: 'valorBordaRecheada' },
        { cb: 'extra-sauce', label: 'molhoExtra', valor: 'valorMolhoExtra' },
        { cb: 'coke', label: 'CocaCola', valor: 'valorCocaCola' }
    ];

    adicionais.forEach(item => {
        const checkbox = document.getElementById(item.cb);
        if (checkbox.checked) {
            const nome = document.getElementById(item.label).childNodes[0].nodeValue.trim();
            const valor = parsearMoeda(document.getElementById(item.valor).textContent);
            adicionarAoCarrinho(nome, valor);
        }
    });
}


// =============================================
// Notificações e Checkout
// =============================================
const addToCartBtn = document.getElementById('botaoAdicionarAoCarrinho');
const cartNotification = document.getElementById('cartNotification');
const closeNotification = document.getElementById('closeNotification');

if (addToCartBtn && cartNotification && closeNotification) {
    addToCartBtn.addEventListener('click', () => {
        cartNotification.classList.add('show');
        setTimeout(() => cartNotification.classList.remove('show'), 3000);
    });

    closeNotification.addEventListener('click', () => {
        cartNotification.classList.remove('show');
    });
}

function irParaCheckout() {
    // Salvar carrinho e desconto
    localStorage.setItem("carrinho", JSON.stringify(carrinho));
    localStorage.setItem("descontoCupom", descontoCupom.toString());
    window.location.href = "checkout";
}

// =============================================
// Associa os eventos de mudança de tamanho
// =============================================
document.querySelectorAll('input[name="size"]').forEach(radio => {
    radio.addEventListener('change', adicionarAoPedido);
});

// Também chama a função inicialmente para preencher o valor padrão
document.addEventListener('DOMContentLoaded', () => {
    adicionarAoPedido();
});




async function aplicarCupom() {
    const codigo = document.getElementById("input-cupom").value;

    if (!codigo) {
        alert("Digite um código de cupom.");
        return;
    }

    try {
        const response = await fetch(`/api/cupom/${codigo}`);
        if (!response.ok) throw new Error("Cupom inválido ou expirado");

        const data = await response.json();
        const totalSemDesconto = calcularTotalSemDesconto(); // Nova função para calcular sem desconto

        if (data.tipo === "fixo") {
            descontoCupom = data.valor;
        } else if (data.tipo === "porcentagem") {
            descontoCupom = totalSemDesconto * (data.valor / 100);
        }

        calcularTotal(); // Atualiza a exibição com o desconto
        alert(`Cupom aplicado com sucesso! Desconto: R$ ${descontoCupom.toFixed(2)}`);

    } catch (error) {
        alert("Cupom inválido ou expirado.");
        descontoCupom = 0;
        calcularTotal(); // Reseta o desconto
    }
}

// Nova função para calcular o total sem considerar desconto
function calcularTotalSemDesconto() {
    const elementosValor = document.querySelectorAll(`
        #resumoValorPizza,
        #resumoAdicionalValorBordaRecheada,
        #resumoAdicionalValorMolhoExtra,
        #resumoAdicionalValorCocaCola
    `);

    return [...elementosValor].reduce((sum, el) => {
        return el.textContent ? sum + parsearMoeda(el.textContent) : sum;
    }, 0);
}