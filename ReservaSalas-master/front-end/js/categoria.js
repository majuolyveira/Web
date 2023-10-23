const formulario = document.querySelector('#form-cad');
const inputNome = document.querySelector('#inputNome');
const finalizarBtn = document.querySelector('#button-finalizar');


formulario.addEventListener("submit", function (event) {
    event.preventDefault();
    cadastrarCategoria();


});


function cadastrarCategoria() {
    fetch("http://localhost:8080/categoria",
        {
            headers: {
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                nome: inputNome.value
            })
        })
        .then(response => {
            console.log(response);
            return response.json();

        })
        .then(response => {
            console.log(response);
            buscarCategoria();
        })
        .catch(function (response) { console.log(response) })

}

function buscarCategoria() {
    fetch("http://localhost:8080/categoria")
        .then(response => response.json()
        )
        .then(data => {
            exibirCategoria(data);
        })
}


function exibirCategoria(data) {
    let novaCategoria = "";
    data.forEach(categoria => {
        novaCategoria += `<div id="categoria-${categoria.id}" class="item-categoria">
            <span>${categoria.nome}</span>
            <i id="categoriaEditar-${categoria.id}-edt" class='bx bx-edit-alt'></i>
            <i id="categoriaDeletar-${categoria.id}-del" class='bx bx-trash'></i>
        </div>`;
    });
    const listaDeCategoria = document.querySelector("#list-categoria");
    listaDeCategoria.innerHTML = novaCategoria;

    data.forEach(categoria => {

        const iconDeletar = document.querySelector(`#categoriaDeletar-${categoria.id}-del`);
        const iconEditar = document.querySelector(`#categoriaEditar-${categoria.id}-edt`);
        const modalEditar = document.querySelector(".modal-content");
        const finalizarEdt = document.querySelector("#form-cad-edt");

        iconEditar.onclick = function () {
            modalEditar.style.display = "block";
            document.querySelector("#inputNome-Edt").value = categoria.nome;
            idCategoria = categoria.id;
        };

        const fechaModal = document.querySelector("#fechar-modal");

        fechaModal.onclick = function () {
            modalEditar.style.display = "none";
        };

        iconDeletar.onclick = function () {
            idCategoria = categoria.id;
            if (idCategoria !== null) {
                deletarCategoria(idCategoria);
                idCategoria = null;
            }
        };
        finalizarEdt.addEventListener('submit', function (event) {
            event.preventDefault();
            if (idCategoria !== null) {
                const categoriaEditada = {
                    nome: document.querySelector("#inputNome-Edt").value
                }
                atualizarCategoria(idCategoria, categoriaEditada);
                idCategoria = null;
            }
            modalEditar.style.display = "none";
        });
    });
}
function atualizarCategoria(idCategoria, categoriaEditada) {
    fetch(`http://localhost:8080/categoria/${idCategoria}`, {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(categoriaEditada)
    })
        .then(response => {
            if (response.ok) {
                console.log("categoria atualizada com sucesso");
                buscarCategoria();

            } else {
                console.error("Falha ao atualizar a localizacao");
                buscarCategoria();
            }
        })
        .catch(error => {
            console.error("Erro ao fazer a solicitação PUT:", error);
        });
}

function deletarCategoria(idCategoria) {
    fetch(`http://localhost:8080/categoria/${idCategoria}`, {
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                console.log("Sala excluída com sucesso");
                buscarCategoria();

            } else {
                console.error("Falha ao excluir a sala");
                alert("Você não pode deletar essa localizacao, ela está associada a uma sala");
                buscarCategoria();
            }
        })
        .catch(error => {
            console.error("Erro ao fazer a solicitação DELETE:", error);
        });
}






buscarCategoria();