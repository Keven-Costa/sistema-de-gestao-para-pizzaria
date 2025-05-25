// Selecionar método de pagamento
function selectPayment(method) {
    // Remover classe active de todos
    document.querySelectorAll('.payment-method').forEach(el => {
        el.classList.remove('active');
    });

    // Adicionar classe active no selecionado
    document.getElementById(`${method}-payment`).classList.add('active');

    // Marcar o radio button correspondente
    document.getElementById(`${method}`).checked = true;

    // Mostrar/ocultar seção de troco
    if (method === 'cash') {
        document.getElementById('change-section').style.display = 'block';
    } else {
        document.getElementById('change-section').style.display = 'none';
    }
}

// Mostrar campo de troco quando necessário
document.getElementById('need-change').addEventListener('change', function () {
    const input = document.getElementById('change-input');
    input.style.display = this.checked ? 'flex' : 'none';
});

// Selecionar dinheiro como padrão
window.onload = function () {
    selectPayment('cash');
};

function limpa_formulário_cep() {
    //Limpa valores do formulário de cep.
    document.getElementById('rua').value = ("");
    document.getElementById('bairro').value = ("");

}

function meu_callback(conteudo) {
    if (!("erro" in conteudo)) {
        //Atualiza os campos com os valores.
        document.getElementById('rua').value = (conteudo.logradouro);
        document.getElementById('bairro').value = (conteudo.bairro);

    } //end if.
    else {
        //CEP não Encontrado.
        limpa_formulário_cep();
        alert("CEP não encontrado.");
    }
}

function pesquisacep(valor) {

    //Nova variável "cep" somente com dígitos.
    var cep = valor.replace(/\D/g, '');

    //Verifica se campo cep possui valor informado.
    if (cep != "") {

        //Expressão regular para validar o CEP.
        var validacep = /^[0-9]{8}$/;

        //Valida o formato do CEP.
        if (validacep.test(cep)) {

            //Preenche os campos com "..." enquanto consulta webservice.
            document.getElementById('rua').value = "...";
            document.getElementById('bairro').value = "...";

            //Cria um elemento javascript.
            var script = document.createElement('script');

            //Sincroniza com o callback.
            script.src = 'https://viacep.com.br/ws/' + cep + '/json/?callback=meu_callback';

            //Insere script no documento e carrega o conteúdo.
            document.body.appendChild(script);

        } //end if.
        else {
            //cep é inválido.
            limpa_formulário_cep();
            alert("Formato de CEP inválido.");
        }
    } //end if.
    else {
        //cep sem valor, limpa formulário.
        limpa_formulário_cep();
    }
};

document.addEventListener("DOMContentLoaded", () => {
    const itensSalvos = localStorage.getItem("carrinho");
    //console.log(itensSalvos)

    if (itensSalvos) {
        const itens = JSON.parse(itensSalvos);
        //console.log(itens)
        const listaCheckout = document.getElementById("lista-checkout");
        let total = 0;

        itens.forEach((item) => {
            const subtotal = item.quantidade * item.precoUnitario;
            total += subtotal;

            listaCheckout.innerHTML += `
        <li class="list-group-item d-flex justify-content-between align-items-center">
            <div class="item">
                <h6 class="m-0"> ${item.nome} (${item.quantidade}x) </h6>
            </div>
            <span>R$ ${item.precoUnitario} = R$ ${subtotal.toFixed(2)}</span>
        </li>
        `;
        });

        document.getElementById("total").textContent = `Total: R$ ${total.toFixed(2)}`;
    }
});








// async function enviarFormulario() {
//     const precoTotalTexto = document.getElementById("total").textContent; // "Total: R$ 59.90"
//     const precoTotalNumero = parseFloat(precoTotalTexto.replace(/[^\d,.-]/g, '').replace(',', '.'));

//     // 1. Coleta dados do formulário
//     const formData = {
//         nome: document.getElementById("nome").value,
//         cep: document.getElementById("cep").value,
//         rua: document.getElementById("rua").value,
//         numero: document.getElementById("numero").value,
//         bairro: document.getElementById("bairro").value,
//         telefone: document.getElementById("telefone").value,
//         referencia: document.getElementById("referencia").value || null,
//         observacoes: document.getElementById("observacoes").value || null,
//         preco_total: precoTotalNumero
//     };

//     // 2. Processa pagamento
//     const metodoPagamento = document.querySelector('input[name="payment"]:checked')?.id;
//     formData.pagamento = { tipo: metodoPagamento };

//     if (metodoPagamento === 'cash' && document.getElementById('need-change').checked) {
//         formData.pagamento.trocoPara = parseFloat(
//             document.querySelector('#change-input input').value
//         );
//     }

//     // 3. Processa itens
//     const carrinho = JSON.parse(localStorage.getItem("carrinho")) || [];
//     formData.itens = carrinho.map(item => ({
//         nome: item.nome,
//         quantidade: item.quantidade,
//         preco: item.precoUnitario
//     }));

//     formData.itens.push({
//         nome: "Taxa de Entrega",
//         quantidade: 1,
//         preco: 5.00
//     });

//     // 4. Envia JSON para o backend e trata resposta
//     try {
//         const response = await fetch("/checkout/salvar", {
//             method: "POST",
//             headers: {
//                 "Content-Type": "application/json"
//             },
//             body: JSON.stringify(formData)
//         });

//         if (!response.ok) {
//             const errorData = await response.json();
//             throw new Error(errorData.error || "Erro ao enviar pedido.");
//         }

//         const data = await response.json();

//         alert(data.message || "Pedido enviado com sucesso!");
//         localStorage.removeItem("carrinho");

//         // Redireciona conforme o backend mandou
//         if (data.redirectUrl) {
//             window.location.href = data.redirectUrl;
//         }

//     } catch (error) {
//         console.error("Erro:", error);
//         alert("Falha ao enviar pedido: " + error.message);
//     }
// }


async function enviarFormulario() {
    const precoTotalTexto = document.getElementById("total").textContent; // Ex: "Total: R$ 59.90"
    // Remove tudo que não é número, vírgula ou ponto e converte para número decimal
    const precoTotalNumero = parseFloat(precoTotalTexto.replace(/[^\d,.-]/g, '').replace(',', '.'));

    // 1. Coleta dados do formulário
    const formData = {
        nome: document.getElementById("nome").value,
        cep: document.getElementById("cep").value,
        rua: document.getElementById("rua").value,
        numero: document.getElementById("numero").value,
        bairro: document.getElementById("bairro").value,
        telefone: document.getElementById("telefone").value,
        referencia: document.getElementById("referencia").value || null,
        observacoes: document.getElementById("observacoes").value || null,
        preco_total: precoTotalNumero
    };

    // 2. Processa pagamento
    const metodoPagamento = document.querySelector('input[name="payment"]:checked')?.id;
    formData.pagamento = { tipo: metodoPagamento };

    if (metodoPagamento === 'cash' && document.getElementById('need-change').checked) {
        const trocoInput = document.querySelector('#change-input input').value;
        formData.pagamento.trocoPara = trocoInput ? parseFloat(trocoInput) : 0;
    }

    // 3. Processa itens do carrinho
    const carrinho = JSON.parse(localStorage.getItem("carrinho")) || [];
    formData.itens = carrinho.map(item => ({
        nome: item.nome,
        quantidade: item.quantidade,
        preco: item.precoUnitario
    }));

    // Adiciona taxa de entrega
    formData.itens.push({
        nome: "Taxa de Entrega",
        quantidade: 1,
        preco: 5.00
    });

    // 4. Envia para o backend e trata resposta
    try {
        const response = await fetch("/checkout/salvar", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData)
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.error || "Erro ao enviar pedido.");
        }

        const data = await response.json();

        alert(data.message || "Pedido enviado com sucesso!");
        localStorage.removeItem("carrinho");

        // SALVA resumo para a próxima página
        if (data.resumo) {
            localStorage.setItem("resumoPedido", JSON.stringify(data.resumo));
        }

        // Redireciona para a página de resumo
        if (data.redirectUrl) {
            window.location.href = data.redirectUrl;
        }

    } catch (error) {
        console.error("Erro:", error);
        alert("Falha ao enviar pedido: " + error.message);
    }
}


