buscarCategoria();
buscarLocalizacao();

const modal = document.querySelector('#cad-sala-modal');
const openModal = document.querySelector('#openModalBtn');
const closeModalBtn = document.querySelector('.close-modal-btn');
const formularioAtualizar = document.querySelector("#formulario-atualizar");
const closeModalAtualiza = document.querySelector("#closeModalAtualiza");

openModal.onclick = function () {
    modal.style.display = "block";
}

closeModalBtn.onclick = function () {
    modal.style.display = "none";
}
closeModalAtualiza.onclick = function () {
    formularioAtualizar.style.display = "none";
}

buscarDadosDasSalas();

const formCadastro = document.querySelector('#cadastro-form');
const inputIdentificacao = document.querySelector('#identificacao');
const inputDescricao = document.querySelector('#descricao');
const inputCategoria = document.querySelector('#categoria');
const IdLocalizacao = document .querySelector(`${localizacao.id}-predio`);
const inputLocalizacao = document.querySelector('#localizacao');

const finalizarBtn = document.querySelector('.finalzar-btn');

function limpar() {
    inputIdentificacao.value = "";
    inputDescricao.value = "";
    inputCategoria.value = "";
    inputLocalizacao.value = "";
}

const confirmeCadastro = document.querySelector('#modal-confirm-cadastro');
const simBtn = document.querySelector('#button-confirm-cad');
const cancelaBtn = document.querySelector('#button-cancela-cad');

finalizarBtn.onclick = function () {

    if (inputIdentificacao.value.trim() == "" ||
        inputDescricao.value.trim() == "" ||
        inputCategoria.value.trim() == "" ||
        inputLocalizacao.value.trim() == "" ) {
        alert("o input está vazio, preechea-o com valor válido");
    } else {
        confirmeCadastro.style.display = "block";
    }
}

formCadastro.addEventListener('submit', function (event) {
    event.preventDefault();

})



simBtn.onclick = function () {
    const meuObjetoJSON = {
        identificacao: inputIdentificacao.value,
        descricao: inputDescricao.value,
        categoria: inputCategoria.value
        //id_localizacao: inputLocalizacao.value
    }
    

    confirmeCadastro.style.display = "none";
    cadastrar(meuObjetoJSON, inputLocalizacao.value);
    limpar();
}

cancelaBtn.onclick = function () {
    confirmeCadastro.style.display = "none";
}



function cadastrar(meuObjetoJSON, idCategoria) {
    fetch(`http://localhost:8080/salas/${idCategoria}`,
        {
            headers: {
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(meuObjetoJSON)
        })
        .then(response => {
            return response.json();
            
        })
        .then(response => {
            buscarDadosDasSalas();

            console.log('Sala cadastrada com sucesso:', response);
            
        })
        .catch(function (response) { console.log(response, "  ->esse foi o erro") })
}

function criarListaDeSalas(data) {
    let listaCompleta = '';
    data.forEach(sala => { 
        const predio = sala.localizacao.predio;
        const piso = sala.localizacao.piso;

        listaCompleta += `<ul class="lista-sala-cadastrada orientacao-colunas"> 
                                    <div class="conteudo-identificacao">
                                        <li class="text-container">${sala.identificacao}</li>
                                    </div>

                                    <div class="conteudo-descricao">
                                        <li class="text-container">${sala.descricao}</li>
                                    </div>

                                    <div class="conteudo-categoria">
                                        <li class="conteudo-list text-container">${sala.categoria}</li>
                                    </div>

                                    <div class="conteudo-predio">
                                        <li class="conteudo-list text-container">${predio}</li>
                                    </div>

                                    <div class="conteudo-piso">
                                        <li class="conteudo-list text-container">${piso}</li>
                                    </div>
                                    
                                    <div class="conteudo-crud">
                                        <button class="button-edt" type="button"  id="button-edt${sala.id}">EDITAR</button>
                                        <button class="button-del" type="button"  id="button-del${sala.id}">DELETAR</button>
                                    </div>
                                </ul>`;

    });

    const listaContent = document.querySelector('#lista-salas');
    listaContent.innerHTML = listaCompleta;

    data.forEach(sala => {
        
        const deletarBtn = document.querySelector(`#button-del${sala.id}`);
        const openModalDelete = document.querySelector('#modal-deletar');
        const cancelaDeleteBtn = openModalDelete.querySelector('#button-deletar-sala-cancelar');
        const simDeleteBtn = openModalDelete.querySelector('#button-deletar-sala-sim');
        deletarBtn.onclick = function () {
            openModalDelete.style.display = "block";
            salaIdParaExcluir = sala.id;
        };
        simDeleteBtn.addEventListener("click", function () {
            openModalDelete.style.display = "none";
            if (salaIdParaExcluir !== null) {

                deletarSala(salaIdParaExcluir);
                salaIdParaExcluir = null;
            }
        });
        cancelaDeleteBtn.onclick = function () {
            openModalDelete.style.display = "none"
            salaIdParaExcluir = null;
        }
    });

    data.forEach(sala => {
       
        const formularioAtualizar = document.querySelector("#formulario-atualizar");
        const editarBtn = document.querySelector(`#button-edt${sala.id}`);
        editarBtn.onclick = function () {
            salaIdParaEditar = sala.id;
            formularioAtualizar.style.display = "block";
            document.querySelector('#identificacao-edt').value = sala.identificacao;
            document.querySelector('#descricao-edt').value = sala.descricao;
            document.querySelector('#categoria-edt').value = sala.categoria;
            document.querySelector('#localizacao-edt').value =  sala.localizacao.predio;
            
            
            
        };

        formularioAtualizar.addEventListener('submit', function (event) {
            
            event.preventDefault();
            if (salaIdParaEditar != null) {
                const salaEditada = {
                    identificacao: document.querySelector('#identificacao-edt').value,
                    descricao: document.querySelector('#descricao-edt').value,
                    categoria: dropCategoriaEdt.value,
                    localizacao: dropLocalizacaoEdt.value  
                }
                atualizarSala(salaIdParaEditar, salaEditada);
                salaIdParaEditar = null;
            }
        });
    });
}

function atualizarSala(salaId, salaEditada) {

    
    fetch(`http://localhost:8080/salas/${salaId}`, {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(salaEditada)
    })
        .then(response => {
            if (response.ok) {
                console.log("Sala atualizada com sucesso");
                buscarDadosDasSalas();
                formularioAtualizar.style.display = "none";
            } else {
                console.error("Falha ao atualizar a sala");
            }
        })
        .catch(error => {
            console.error("Erro ao fazer a solicitação PUT:", error);
        });
}

function buscarDadosDasSalas() {
    fetch('http://localhost:8080/salas')
        .then(response => response.json()
        )
        .then(data => {
            criarListaDeSalas(data);
        })
        .catch(error => {
            console.log('Erro ao buscar os dados das salas:', error);
        });
}

function deletarSala(salaId) {
    fetch(`http://localhost:8080/salas/${salaId}`, {
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                console.log("Sala excluída com sucesso");
                buscarDadosDasSalas();
                
            } else {
                console.error("Falha ao excluir a sala");
            }
        })
        .catch(error => {
            console.error("Erro ao fazer a solicitação DELETE:", error);
        });
}

const dropCategoria = document.querySelector("#categoria");

function buscarCategoria() {
    fetch("http://localhost:8080/categoria")
        .then(response => response.json()
        )
        .then(data => {
            exibirCategoria(data);
        })
}

const dropCategoriaEdt = document.querySelector('#categoria-edt');
function exibirCategoria(data) {
    let todasCategorias = "";

    todasCategorias += `<option value="" disabled selected hidden>Selecione uma opção</option>`;

    data.forEach(categoria => {
        todasCategorias += `<option id="${categoria.id}" value="${categoria.nome}">${categoria.nome}</option>`
    });

    dropCategoria.innerHTML = todasCategorias;
    dropCategoriaEdt.innerHTML = todasCategorias;
};

function buscarLocalizacao() {
    fetch("http://localhost:8080/localizacao")
        .then(response => response.json()
        )
        .then(data => {
            exibirLocalizacao(data);
        })
}

const dropLocalizacao = document.querySelector("#localizacao");


const dropLocalizacaoEdt = document.querySelector("#localizacao-edt");

function exibirLocalizacao(data) {
    let todosLocalizacao = "";
    
    todosLocalizacao += `<option value="" disabled selected hidden>Selecione uma opção</option>`;
    
    data.forEach(localizacao => {

        todosLocalizacao += `<option id="${localizacao.id}-localizacao" value="${localizacao.id}">${localizacao.predio} / ${localizacao.piso} </option>`;
    
    });

    dropLocalizacao.innerHTML = todosLocalizacao;
    dropLocalizacaoEdt.innerHTML = todosLocalizacao;
    

};

