async function carregarAlunos(){

    try{
        const response = await fetch("http://localhost:8080/alunos");
        if (!response.ok){
            throw new Error("Erro ao buscar alunos");
        }


    const data = await response.json();

    console.log(data);

    renderizarAlunos(data.data);

    }catch (error){
        console.error("Erro: ", error)
    }
}

function renderizarAlunos(alunos){

    const lista = document.getElementById("lista-alunos");

    lista.innerHTML = "";

    alunos.forEach(aluno => {

    const div = document.createElement("div");
    div.classList.add("aluno-item");

    div.innerHTML = `
    <div class="aluno-info">
        <span class="aluno-nome">${aluno.nome}</span>
        <span class="aluno-detalhe">
            Nota: ${aluno.nota} - Curso ID: ${aluno.cursoId}
        </span>
    </div>
    <span>#${aluno.id}</span>
    `;

    lista.appendChild(div);

    });
}

    async function cadastrarAluno() {
        
        const nome = document.getElementById("nome").value;
        const nota = parseFloat(document.getElementById("nota").value);
        const cursoId = parseInt(document.getElementById("cursoId").value);

        const aluno = {
            nome: nome,
            nota: nota,
            cursoId: cursoId
        };

        console.log(JSON.stringify(aluno));

        await fetch("http://localhost:8080/alunos", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(aluno)
        });

        carregarAlunos();
    }
