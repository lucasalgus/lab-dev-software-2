import { FormEvent } from "react";
import { Button, Form } from "react-bootstrap";
import { postReq } from "../../api/api";

const CadastroPage = () => {
	const createUser = async (event: FormEvent) => {
		event.preventDefault();
		const target = event.target as any;

		const nome = target.elements.name.value;
		const email = target.elements.email.value;
		const senha = target.elements.password.value;
		const endereco = target.elements.address.value;
		const rg = target.elements.rg.value;
		const cpf = target.elements.cpf.value;
		const profissao = target.elements.profession.value;
		const empregadores = target.elements.employers.value.split(",");
		const rendimentos = target.elements.earnings.value.split(",");

		const usuario = await postReq("usuario/add", {
			nome,
			email,
			senha,
			endereco,
		});
		const usuarioJSON = await usuario.json();

		const cliente = await postReq("cliente/add", {
			rg,
			cpf,
			profissao,
			usuarioID: usuarioJSON.id,
		});
		const clienteJSON = await cliente.json();

		console.log(clienteJSON);

		for (const empregador of empregadores) {
			await postReq("empregador/add", {
				nome: empregador,
				clienteID: clienteJSON.id,
			});
		}

		for (const rendimento of rendimentos) {
			await postReq("rendimento/add", {
				valor: parseFloat(rendimento),
				clienteID: clienteJSON.id,
			});
		}

		alert("Cadastro realizado com sucesso!");
		window.location.href = "/login";
	};

	return (
		<Form onSubmit={createUser}>
			<Form.Group controlId="email">
				<Form.Label>Email</Form.Label>
				<Form.Control type="email" placeholder="Email" />
			</Form.Group>

			<Form.Group controlId="password">
				<Form.Label>Senha</Form.Label>
				<Form.Control type="password" placeholder="Senha" />
			</Form.Group>

			<Form.Group controlId="name">
				<Form.Label>Nome</Form.Label>
				<Form.Control type="text" placeholder="Nome" />
			</Form.Group>

			<Form.Group controlId="address">
				<Form.Label>Endereço</Form.Label>
				<Form.Control type="text" placeholder="Endereço" />
			</Form.Group>

			<Form.Group controlId="rg">
				<Form.Label>RG</Form.Label>
				<Form.Control type="text" placeholder="RG" />
			</Form.Group>

			<Form.Group controlId="cpf">
				<Form.Label>CPF</Form.Label>
				<Form.Control type="text" placeholder="CPF" />
			</Form.Group>

			<Form.Group controlId="profession">
				<Form.Label>Profissão</Form.Label>
				<Form.Control type="text" placeholder="Profissão" />
			</Form.Group>

			<Form.Group controlId="employers">
				<Form.Label>Empregadores (separados por ,)</Form.Label>
				<Form.Control type="text" placeholder="Profissão" />
			</Form.Group>

			<Form.Group controlId="earnings">
				<Form.Label>Rendimentos (separados por ,)</Form.Label>
				<Form.Control type="text" placeholder="Rendimentos" />
			</Form.Group>

			<Button variant="primary" type="submit">
				Cadastrar
			</Button>
		</Form>
	);
};

export default CadastroPage;
