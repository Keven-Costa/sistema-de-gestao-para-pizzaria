// Gráfico de Pizza - Sabores mais vendidos
const ctx = document.getElementById('flavorsChart').getContext('2d');
const flavorsChart = new Chart(ctx, {
    type: 'pie',
    data: {
        labels: ['Calabresa', 'Margherita', 'Portuguesa', 'Quatro Queijos', 'Pepperoni', 'Outros'],
        datasets: [{
            data: [30, 25, 15, 12, 10, 8],
            backgroundColor: [
                '#dc3545',
                '#fd7e14',
                '#ffc107',
                '#28a745',
                '#17a2b8',
                '#6c757d'
            ],
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                position: 'bottom',
            }
        }
    }
});