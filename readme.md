## Usuario

### GET /usuario
Exibe a lista de usuarios no sistema.

**Response 200**
json
[
    {
        "id": 1,
        "nome": "Novo Usuario",
        "cpf": "095.365.140-10",
        "email": "email@teste.com",
        "dataCadastro": "18/02/2020 06:31:21"
    },
    {
        "id": 2,
        "nome": "Novo Usuario 2",
        "cpf": "095.365.140-10",
        "email": "email2@teste.com",
        "dataCadastro": "18/02/2020 06:32:04"
    }
]

### GET /usuario/{id}
Exibe os dados do usuário por id

**Response 200**
[
    {
        "id": 1,
        "nome": "Novo Usuario",
        "cpf": "095.365.140-10",
        "email": "email@teste.com",
        "dataCadastro": "18/02/2020 06:31:21"
    }
]

### POST /usuario
Cadastra um novo usuario no sistema.

**Request Body**
json
{
	"nome":"Novo Usuario",
	"cpf":"095.365.140-10",
	"email":"email@teste.com"
}

### PATCH /usuario/{id}
Altera dados de um usuario no sistema.

**Request Body**
json
{
	"nome":"Novo Usuario 2",
	"cpf":"095.365.140-10",
	"email":"email2@teste.com"
}

## Registro de Ponto

### GET /registro/{id}
Lista os pontos batidos por um usuário

**Response 200**
json
{
    "registros": [
        {
            "id": 1,
            "usuario": {
                "id": 1,
                "nome": "Novo Usuario",
                "cpf": "095.365.140-10",
                "email": "email@teste.com",
                "dataCadastro": "18/02/2020 06:31:21"
            },
            "tipoRegistro": "ENTRADA",
            "dataHora": "19/02/2020 04:24:36"
        },
        {
            "id": 2,
            "usuario": {
                "id": 1,
                "nome": "Novo Usuario",
                "cpf": "095.365.140-10",
                "email": "email@teste.com",
                "dataCadastro": "18/02/2020 06:31:21"
            },
            "tipoRegistro": "SAIDA",
            "dataHora": "19/02/2020 05:43:15"
        },
        {
            "id": 3,
            "usuario": {
                "id": 1,
                "nome": "Novo Usuario",
                "cpf": "095.365.140-10",
                "email": "email@teste.com",
                "dataCadastro": "18/02/2020 06:31:21"
            },
            "tipoRegistro": "ENTRADA",
            "dataHora": "19/02/2020 06:11:09"
        },
        {
            "id": 4,
            "usuario": {
                "id": 1,
                "nome": "Novo Usuario",
                "cpf": "095.365.140-10",
                "email": "email@teste.com",
                "dataCadastro": "18/02/2020 06:31:21"
            },
            "tipoRegistro": "SAIDA",
            "dataHora": "20/02/2020 11:38:15"
        }
    ],
    "totalHorasTrabalhadas": 18
}

### POST /registro
Registra um ponto para o usuario no sistema.

**Request Body**
json
{
	"usuarioId":1,
	"tipoRegistro":"ENTRADA"
}

json
{
	"usuarioId":1,
	"tipoRegistro":"SAIDA"
}