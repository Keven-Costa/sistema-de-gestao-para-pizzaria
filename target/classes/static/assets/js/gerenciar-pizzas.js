

document.addEventListener("DOMContentLoaded", () => {
    // ----- Modal de Edição -----
    const editarModal = document.getElementById("editarModal");
    if (editarModal) {
        editarModal.addEventListener("show.bs.modal", (event) => {
            const button = event.relatedTarget;
            const id = button.getAttribute("data-id");
            const nome = button.getAttribute("data-nome");
            const descricao = button.getAttribute("data-descricao");
            const tipo = button.getAttribute("data-tipo");
            const imagem = button.getAttribute("data-imagem");

            // Debug: verifique os valores no console
            console.log("Dados da pizza:", {id, nome, descricao, tipo, imagem}); // ← Corrigido

            // Preenche os campos do modal
            document.getElementById("pizzaId").value = id;
            document.getElementById("pizzaNome").value = nome;
            document.getElementById("pizzaDescricao").value = descricao;
            document.getElementById("pizzaTipo").value = tipo;
            document.getElementById("pizzaImagem").value = imagem;

            // Atualiza a action do form
            const formEdit = document.getElementById("formEditarPizza");
            formEdit.action = `/admin/editar-pizza/${id}`;
        });
    }

    // ----- Modal de Exclusão -----
    const excluirModal = document.getElementById("excluirModal");
    if (excluirModal) {
        excluirModal.addEventListener("show.bs.modal", (event) => {
            const button = event.relatedTarget;
            const id = button.getAttribute("data-id");
            const nome = button.getAttribute("data-nome");

            document.getElementById("pizzaExcluirId").value = id;
            document.getElementById("nomePizzaExcluir").textContent = nome;

            const formDel = document.getElementById("formExcluirPizza");
            formDel.action = `/admin/excluir-pizza/${id}`;
        });
    }
});
