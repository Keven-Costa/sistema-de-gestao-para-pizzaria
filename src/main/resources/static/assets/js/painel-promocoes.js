document.addEventListener('DOMContentLoaded', function () {
    // Esconde todas as seções de conteúdo
    function hideAllSections() {
        document.querySelectorAll('.content-section').forEach(section => {
            section.classList.add('d-none');
        });
    }

    // Mostra a seção clicada
    document.querySelectorAll('.list-group-item').forEach(item => {
        item.addEventListener('click', function (e) {
            e.preventDefault();
            hideAllSections();

            // Remove a classe 'active' de todos os itens
            document.querySelectorAll('.list-group-item').forEach(i => {
                i.classList.remove('active');
            });

            // Adiciona 'active' ao item clicado
            this.classList.add('active');

            // Mostra a seção correspondente
            const targetId = this.getAttribute('href').substring(1);
            document.getElementById(targetId)?.classList.remove('d-none');
        });
    });

    // Mostra a primeira seção por padrão
    document.querySelector('.list-group-item.active')?.click();
});


// function invalidar(button) {
//     // Obtém os atributos data- do botão passado como parâmetro
//     const id = button.dataset.id;
//     const codigo = button.dataset.nome;

//     if (confirm(`Tem certeza que deseja invalidar a promoção ${codigo}?`)) {
//         window.location.href = `/admin/painel-promocoes/invalidar/${id}`;
//     }
// }

document.getElementById('confirmarExclusaoModal').addEventListener('show.bs.modal', function (event) {
    const button = event.relatedTarget;
    const id = button.getAttribute('data-id');
    const nome = button.getAttribute('data-nome');

    document.getElementById('nomeIngredienteExcluir').textContent = nome;
    document.getElementById('btnConfirmarExclusao').href = '/admin/novo-gestao-ingredientes/excluir/' + id;
});