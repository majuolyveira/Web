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

            exibirLocalizacao(data);
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
            buscarLocalizacoesCadastradas();
        })
        .catch(function (response) { console.log(response) });
}


function exibirLocalizacao(data) {
    let novaLocalizacao = "";
    data.forEach(localizacao => {
        novaLocalizacao += `<div id="loc-${localizacao.id}-localizacao" class="iconteudo-localizacao">
            <span>Prédio: ${localizacao.predio}, Piso: ${localizacao.piso}</span>
            <i id="loc-${localizacao.id}-edt" class='bx bx-edit-alt'></i>
            <i id="loc-${localizacao.id}-deletar" class='bx bx-trash'></i>
        </div>`;
    });


    localizacaoList.innerHTML = novaLocalizacao;

    data.forEach(localizacao => {

        const iconDeletar = document.querySelector(`#loc-${localizacao.id}-deletar`);
        const iconEditar = document.querySelector(`#loc-${localizacao.id}-edt`);
        const modalEditar = document.querySelector(".modal-edt");
        const formEditado = document.querySelector("#form-cad-edt");

        iconEditar.onclick = function () {
            modalEditar.style.display = "block";
            document.querySelector("#inputPredioEdt").value = localizacao.predio;
            document.querySelector("#inputPisoEdt").value = localizacao.piso;
            idLocalizacao = localizacao.id;

        };
        const fechaModal = document.querySelector("#fechar-modal");

        fechaModal.onclick = function () {
            modalEditar.style.display = "none";
            idLocalizacao = null;
        };


        iconDeletar.onclick = function () {
            idLocalizacao = localizacao.id;
            if (idLocalizacao !== null) {

                deletarLocalizacao(idLocalizacao);
                idLocalizacao = null;
            }
        };

        formEditado.addEventListener('submit', function (event) {
            event.preventDefault();
            if (idLocalizacao !== null) {
                const localizacaoEditada = {
                    predio: document.querySelector('#inputPredioEdt').value,
                    piso: document.querySelector('#inputPisoEdt').value
                }
                atualizarLocalizacao(idLocalizacao, localizacaoEditada);
                idLocalizacao = null;
            }
            modalEditar.style.display = "none";
        });
    });
}

function deletarLocalizacao(idLocalizacao) {
    fetch(`http://localhost:8080/localizacao/${idLocalizacao}`, {
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                console.log("Sala excluída com sucesso");
                buscarLocalizacoesCadastradas();

            } else {
                console.error("Falha ao excluir a sala");
                alert("Você não pode deletar essa localizacao, ela está associada a uma sala");
                buscarLocalizacoesCadastradas();
            }
        })
        .catch(error => {
            console.error("Erro ao fazer a solicitação DELETE:", error);
        });
}

function atualizarLocalizacao(idLocalizacao, localizacaoEditada) {
    fetch(`http://localhost:8080/localizacao/${idLocalizacao}`, {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(localizacaoEditada)
    })
        .then(response => {
            if (response.ok) {
                console.log("localizacao atualizada com sucesso");
                buscarLocalizacoesCadastradas();

            } else {
                console.error("Falha ao atualizar a localizacao");
                buscarLocalizacoesCadastradas();
            }
        })
        .catch(error => {
            console.error("Erro ao fazer a solicitação PUT:", error);
        });
}




