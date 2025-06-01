document.addEventListener("DOMContentLoaded", () => {
    const ctx = document.getElementById('flavorsChart');
    if (!ctx) return;

    try {
        // Processa os dados de forma segura
        const sabores = ctx.dataset.sabores ? 
            ctx.dataset.sabores.split(',').filter(s => !s.toLowerCase().includes('taxa')) : [];
        
        const quantidades = ctx.dataset.quantidades ? 
            ctx.dataset.quantidades.split(',').map(Number).filter((_, i) => {
                const sabor = ctx.dataset.sabores.split(',')[i];
                return !sabor.toLowerCase().includes('taxa');
            }) : [];

        if (sabores.length === 0 || quantidades.length === 0) {
            console.warn('Dados insuficientes para o gráfico');
            return;
        }

        new Chart(ctx.getContext('2d'), {
            type: 'pie',
            data: {
                labels: sabores,
                datasets: [{
                    data: quantidades,
                    backgroundColor: [
                        '#dc3545', '#fd7e14', '#ffc107',
                        '#28a745', '#17a2b8', '#6c757d'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: { position: 'bottom' },
                    tooltip: {
                        callbacks: {
                            label: context => `${context.label}: ${context.raw} vendas`
                        }
                    }
                }
            }
        });
    } catch (error) {
        console.error('Erro ao renderizar gráfico:', error);
    }
});