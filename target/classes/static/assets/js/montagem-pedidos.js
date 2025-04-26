

// Chamar atualizarCarrinho quando a página carregar para mostrar os itens existentes
document.addEventListener('DOMContentLoaded', function () {
    atualizarCarrinho();
});


let carrinho = [];

function pegarItensDoPedido() {

    // Obter todos os itens do resumo
    const resumoItens = document.getElementById('resumo-pedido').children;

    // Verificar se há itens no resumo
    if (resumoItens.length === 0) {
        alert('Nenhum item no resumo para adicionar ao carrinho!');
        return;
    }

    // Criar um objeto para armazenar os itens do carrinho
    let carrinhoItens = JSON.parse(localStorage.getItem('carrinhoItens')) || [];

    const tamanhoPizza = document.getElementById('resumoTamanhoPizza').textContent;
    let valorPizza = document.getElementById('resumoValorPizza').textContent;
    valorPizza = parseFloat(valorPizza.replace('R$', '').replace('+ R$', '').replace(',', '.').trim());

    const bordaRecheada = document.getElementById('resumoAdcionalBordaRecheada').textContent;
    let valorBorda = document.getElementById('resumoAdicionalValorBordaRecheada').textContent;
    valorBorda = valorBorda.replace(/[^\d,.-]/g, '');
    valorBorda = valorBorda.replace(',', '.');
    valorBorda = parseFloat(valorBorda);

    const molhoExtra = document.getElementById('resumoAdcionalMolhoExtra').textContent;
    let valorMolho = document.getElementById('resumoAdicionalValorMolhoExtra').textContent;
    valorMolho = valorMolho.replace(/[^\d,.-]/g, '');
    valorMolho = valorMolho.replace(',', '.');
    valorMolho = parseFloat(valorMolho);

    const cocaCola = document.getElementById('resumoAdcionalCocaCola').textContent;
    let valorCoca = document.getElementById('resumoAdicionalValorCocaCola').textContent;
    valorCoca = valorCoca.replace(/[^\d,.-]/g, '');
    valorCoca = valorCoca.replace(',', '.');
    valorCoca = parseFloat(valorCoca);

    // Criar objeto do item principal (pizza)
    if (tamanhoPizza && valorPizza) {
        adicionarAoCarrinho(tamanhoPizza, valorPizza);
    }

    // Adicionar borda recheada se existir
    if (bordaRecheada && valorBorda) {
        adicionarAoCarrinho(bordaRecheada, valorBorda);
    }

    // Adicionar molho extra se existir
    if (molhoExtra && valorMolho) {
        adicionarAoCarrinho(molhoExtra, valorMolho);
    }

    // Adicionar Coca-Cola se existir
    if (cocaCola && valorCoca) {
        adicionarAoCarrinho(cocaCola, valorCoca);
    }

    // Salvar no localStorage
    localStorage.setItem('carrinhoItens', JSON.stringify(carrinhoItens));
}

function adicionarAoCarrinho(nome, preco) {
    // Verifica se o item já está no carrinho
    const itemExistente = carrinho.find(item => item.nome === nome);

    if (itemExistente) {
        // Se já existe, aumenta a quantidade
        itemExistente.quantidade++;
    } else {
        // Se não existe, adiciona novo item
        carrinho.push({
            nome: nome,
            preco: preco,
            quantidade: 1
        });
    }

    atualizarCarrinho();
}

function removerDoCarrinho(nome) {
    // Encontra o índice do item no carrinho
    const index = carrinho.findIndex(item => item.nome === nome);

    if (index !== -1) {
        if (carrinho[index].quantidade > 1) {
            // Se tiver mais de 1, diminui a quantidade
            carrinho[index].quantidade--;
        } else {
            // Se tiver apenas 1, remove o item
            carrinho.splice(index, 1);
        }
    }

    atualizarCarrinho();
}

function atualizarCarrinho() {
    const itensCarrinho = document.getElementById('itens-carrinho');
    const totalCarrinho = document.getElementById('total-carrinho');

    // Limpa o conteúdo atual
    itensCarrinho.innerHTML = '';

    if (carrinho.length === 0) {
        itensCarrinho.innerHTML = '<p>Carrinho vazio</p>';
        totalCarrinho.textContent = 'Total: R$ 0,00';
        return;
    }

    let total = 0;

    // Adiciona cada item ao carrinho
    carrinho.forEach(item => {
        const itemElement = document.createElement('div');
        itemElement.className = 'item-carrinho';

        const subtotal = item.preco * item.quantidade;
        total += subtotal;

        itemElement.innerHTML = `
            <span>${item.nome} (${item.quantidade}x) - R$ ${subtotal.toFixed(2)}</span>

            <div>
                <button class="btn btn-sm btn-outline-danger px-2 py-0" onclick="removerDoCarrinho('${item.nome}')">
                    <i class="fas fa-minus"></i>
                </button>
            
                <button class="btn btn-sm btn-outline-danger px-2 py-0" onclick="adicionarAoCarrinho('${item.nome}', '${item.preco}')">
                    <i class="fas fa-plus"></i>
                </button>
            </div>
                                                                        

        `;

        itensCarrinho.appendChild(itemElement);

        const itemHtml = document.getElementById('quantidadeItens')
        const quantidadeItens = document.querySelectorAll('#itens-carrinho .item-carrinho').length;
        console.log(`O carrinho tem ${quantidadeItens} itens.`);

        itemHtml.innerHTML = `

        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-warning" >
                           '${quantidadeItens}'
        </span>`
    });

    // Atualiza o total
    totalCarrinho.textContent = `R$ ${total.toFixed(2)}`;
}

function adicionarAoPedido() {
    // Pega o label clicado
    const labelClicado = event.currentTarget;

    // Extrai o nome (texto antes do <br>)
    const tamanhoPizza = labelClicado.childNodes[0].textContent.trim(); // "Broto", "Média", "Grande"
    const valorPizza = labelClicado.querySelector("small").textContent;



    //const valorNumerico = parseFloat(valorTexto.replace("R$", "").replace(",", ".")); 
    // Atualiza o resumo do pedido
    document.getElementById("resumoTamanhoPizza").textContent = `1 Pizza ${tamanhoPizza}`;
    document.getElementById("resumoValorPizza").textContent = valorPizza;
    calcularTotal();
}

function handleStuffedCrust(checkbox) {
    // Pega o elemento <label> pelo ID
    const labelProduto = document.getElementById('bordaRecheada');

    // Extrai o nome do produto (texto direto do label, ignorando o <span>)
    const nomeProduto = labelProduto.childNodes[0].nodeValue.trim(); // "Coca-Cola 2L"

    // Pega o valor do <span> dentro do label
    const valorSpan = document.getElementById('valorBordaRecheada');
    const valorTexto = valorSpan.textContent.trim();

    console.log('Nome:', nomeProduto);
    console.log('Valor:', valorTexto);

    if (checkbox.checked) {
        document.getElementById("resumoAdcionalBordaRecheada").textContent = nomeProduto;
        document.getElementById("resumoAdicionalValorBordaRecheada").textContent = valorTexto;
    } else {
        document.getElementById("resumoAdcionalBordaRecheada").textContent = "";
        document.getElementById("resumoAdicionalValorBordaRecheada").textContent = "";
    }
    calcularTotal();
}

function handleExtraSauce(checkbox) {
    // Pega o elemento <label> pelo ID
    const labelProduto = document.getElementById('molhoExtra');

    // Extrai o nome do produto (texto direto do label, ignorando o <span>)
    const nomeProduto = labelProduto.childNodes[0].nodeValue.trim(); // "Coca-Cola 2L"

    // Pega o valor do <span> dentro do label
    const valorSpan = document.getElementById('valorMolhoExtra');
    const valorTexto = valorSpan.textContent.trim(); // "+ R$ 12,00"

    console.log('Nome:', nomeProduto); // "Coca-Cola 2L"
    console.log('Valor:', valorTexto); // "R$ 12,00"

    if (checkbox.checked) {
        document.getElementById("resumoAdcionalMolhoExtra").textContent = nomeProduto;
        document.getElementById("resumoAdicionalValorMolhoExtra").textContent = valorTexto;
    } else {
        document.getElementById("resumoAdcionalMolhoExtra").textContent = "";
        document.getElementById("resumoAdicionalValorMolhoExtra").textContent = "";
    }
    calcularTotal();
}

function handleCoke(checkbox) {
    // Pega o elemento <label> pelo ID
    const labelProduto = document.getElementById('CocaCola');

    // Extrai o nome do produto (texto direto do label, ignorando o <span>)
    const nomeProduto = labelProduto.childNodes[0].nodeValue.trim(); // "Coca-Cola 2L"

    // Pega o valor do <span> dentro do label
    const valorSpan = document.getElementById('valorCocaCola');
    const valorTexto = valorSpan.textContent.trim(); // "+ R$ 12,00"

    console.log('Nome:', nomeProduto); // "Coca-Cola 2L"
    console.log('Valor:', valorTexto); // "R$ 12,00"

    if (checkbox.checked) {
        document.getElementById("resumoAdcionalCocaCola").textContent = nomeProduto;
        document.getElementById("resumoAdicionalValorCocaCola").textContent = valorTexto;
    } else {
        document.getElementById("resumoAdcionalCocaCola").textContent = "";
        document.getElementById("resumoAdicionalValorCocaCola").textContent = "";
    }
    calcularTotal();

}

function calcularTotal() {
    // Seleciona todos os elementos de valor dentro da lista
    const elementosValor = document.querySelectorAll('#resumo-pedido span[id^="resumoAdicionalValor"], #resumoValorPizza');

    let total = 0;

    elementosValor.forEach((elemento) => {
        if (elemento.textContent.trim() !== '') {
            // Extrai o valor numérico (ex: "R$ 12,00" → 12.00)
            const valorTexto = elemento.textContent.replace('+ R$', '').replace('R$', '').replace(',', '.').trim();
            const valorNumerico = parseFloat(valorTexto);

            if (!isNaN(valorNumerico)) {
                total += valorNumerico;
            }
        }
    });

    // Atualiza o elemento de total no HTML
    const elementoTotal = document.getElementById('resumo-total');
    elementoTotal.textContent = `R$ ${total.toFixed(2).replace('.', ',')}`;

    return total;
}

// Seleciona os elementos
const addToCartBtn = document.getElementById('botaoAdicionarAoCarrinho');
const cartNotification = document.getElementById('cartNotification');
const closeNotification = document.getElementById('closeNotification');

// Mostra a notificação quando clica no botão
addToCartBtn.addEventListener('click', () => {
    cartNotification.classList.add('show');

    // Esconde automaticamente após 3 segundos
    setTimeout(() => {
        cartNotification.classList.remove('show');
    }, 3000);

});

// Fecha a notificação quando clica no botão de fechar
closeNotification.addEventListener('click', () => {
    cartNotification.classList.remove('show');
});

