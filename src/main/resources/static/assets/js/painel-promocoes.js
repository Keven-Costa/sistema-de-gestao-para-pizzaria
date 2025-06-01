// Troca de abas Bootstrap
document.querySelectorAll('.list-group-item').forEach(function (item) {
    item.addEventListener('click', function () {
        document.querySelectorAll('.tab-pane').forEach(function (tab) {
            tab.classList.remove('show', 'active');
        });
        let target = document.querySelector(this.getAttribute('href'));
        target.classList.add('show', 'active');
        document.querySelectorAll('.list-group-item').forEach(function (i) {
            i.classList.remove('active');
        });
        this.classList.add('active');
    });
});

// Confirmação antes de excluir
document.querySelectorAll('form[action*="excluir"]').forEach(form => {
    form.addEventListener('submit', function(e) {
        if (!confirm('Tem certeza que deseja EXCLUIR PERMANENTEMENTE este cupom?')) {
            e.preventDefault();
        }
    });
});