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
    console.log(itensSalvos)

    if (itensSalvos) {
        const itens = JSON.parse(itensSalvos);
        console.log(itens)
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