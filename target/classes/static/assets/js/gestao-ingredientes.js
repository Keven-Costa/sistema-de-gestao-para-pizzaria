
document.getElementById('editIngredientModal').addEventListener('show.bs.modal', function (event) {
    const button = event.relatedTarget;

    // Captura os dados do botão que abriu o modal
    const id = button.getAttribute('data-id');
    const nome = button.getAttribute('data-nome');
    const quantidade = button.getAttribute('data-quantidade');
    const estoque = button.getAttribute('data-estoque');
    const unidade = button.getAttribute('data-unidade');

    // Preenche o formulário no modal
    document.getElementById('ingredienteId').value = id;
    document.getElementById('ingredienteIdText').textContent = id;
    document.getElementById('editIngredientName').value = nome;
    document.getElementById('editCurrentQuantity').value = quantidade;
    document.getElementById('editMinQuantity').value = estoque;
    document.getElementById('editUnit').value = unidade;

    // Atualiza a action do formulário com o ID correto
    document.getElementById('formEditarIngrediente').action = '/admin/gestao-ingredientes/salvar';
});

// Opcional: Validação antes de enviar
document.getElementById('formEditarIngrediente').addEventListener('submit', function (e) {
    if (!this.checkValidity()) {
        e.preventDefault();
        e.stopPropagation();
    }
    this.classList.add('was-validated');
});

document.getElementById('confirmarExclusaoModal').addEventListener('show.bs.modal', function (event) {
    const button = event.relatedTarget;
    const id = button.getAttribute('data-id');
    const nome = button.getAttribute('data-nome');

    // Preenche o nome no texto do modal
    document.getElementById('nomeIngredienteExcluir').textContent = nome;
    // Preenche o campo hidden do form
    document.getElementById('ingredienteIdExcluir').value = id;
});

// Quando clicar em “Excluir”, envia o form via POST
document.getElementById('btnConfirmarExclusao').addEventListener('click', function () {
    document.getElementById('formExcluirIngrediente').submit();
});
