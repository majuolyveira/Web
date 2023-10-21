// localizacao.js

const formulario = document.querySelector('#form-cad');
const inputPredio = document.querySelector('#inputPredio');
const inputPiso = document.querySelector('#inputPiso');
const finalizarBtn = document.querySelector('#button-finalizar');
const localizacaoList = document.querySelector('#localizacao-lista'); // Elemento para exibir dados

// Selecionar o modal de confirmação
const modalConfirmacao = document.getElementById('modal-confirmacao');
const confirmarBtn = document.getElementById('confirmar-cadastro');
const cancelarBtn = document.getElementById('cancelar-cadastro');

// Observar o botão "Finalizar" para mostrar o modal
finalizarBtn.addEventListener('click', function () {
    modalConfirmacao.style.display = 'block';
});

// Observar o botão "Sim" para confirmar o cadastro
confirmarBtn.addEventListener('click', function () {
    cadastrarLocalizacao(); // Chame a função para cadastrar a localização
    modalConfirmacao.style.display = 'none'; // Feche o modal
});

// Observar o botão "Não" para cancelar o cadastro
cancelarBtn.addEventListener('click', function () {
    modalConfirmacao.style.display = 'none'; // Feche o modal
});

formulario.addEventListener("submit", function (event) {
    event.preventDefault();
});

// Função para buscar e exibir as localizações já cadastradas
function buscarLocalizacoesCadastradas() {
    fetch("http://localhost:8080/localizacao")
        .then(response => response.json())
        .then(data => {
            data.forEach(localizacao => {
                const novoItem = document.createElement('div');
                novoItem.textContent = `Prédio: ${localizacao.predio}, Piso: ${localizacao.piso}`;
                localizacaoList.appendChild(novoItem);
            });
        })
        .catch(error => {
            console.log('Erro ao buscar as localizações:', error);
        });
}

// Chame a função para buscar localizações quando a página carregar
document.addEventListener('DOMContentLoaded', buscarLocalizacoesCadastradas);

function cadastrarLocalizacao() {
    fetch("http://localhost:8080/localizacao", {
        headers: {
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            predio: inputPredio.value,
            piso: inputPiso.value
        })
    })
    .then(response => response.json())
    .then(response => {
        console.log(response); // Verifique a resposta do servidor
        const novoItem = document.createElement('div');
        novoItem.textContent = `Prédio: ${response.predio}, Piso: ${response.piso}`;
        localizacaoList.appendChild(novoItem);
    })
    .catch(function (response) { console.log(response) });
}
