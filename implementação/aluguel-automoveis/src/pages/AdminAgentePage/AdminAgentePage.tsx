import { FormEvent, useEffect, useState } from "react";
import { Button, Form, Modal, Table } from "react-bootstrap";
import { getReq, postReq } from "../../api/api";

const AdminAgentePage = () => {
	const [modal, setModal] = useState(false);
	const [agents, setAgents] = useState<any[]>([]);

	const getAgents = async () => {
		const users = await getReq("usuario");
		const agents = await getReq("agente");

		console.log(users);

		const formattedAgents = agents.map((agent: any) => ({
			...agent,
			user: users.find((user: any) => user.id === agent.usuarioID),
		}));

		setAgents(formattedAgents);
		console.log(formattedAgents);
	};

	const addAgent = async (event: FormEvent) => {
		event.preventDefault();
		const target = event.target as any;

		const nome = target.elements.nome.value;
		const email = target.elements.email.value;
		const senha = target.elements.senha.value;
		const endereco = target.elements.endereco.value;
		const cnpj = target.elements.cnpj.value;

		const usuario = await postReq("usuario/add", {
			nome,
			email,
			senha,
			endereco,
		});
		const usuarioJSON = await usuario.json();

		await postReq("agente/add", {
			cnpj,
			usuarioID: usuarioJSON.id,
		});

		await getAgents();
		setModal(false);
	};

	useEffect(() => {
		getAgents().then();
	}, []);

	return (
		<>
			<h1>Agentes</h1>
			<Button onClick={() => setModal(true)}>Adicionar novo</Button>
			<div style={{ marginBottom: "20px" }} />
			<Table striped bordered hover>
				<thead>
					<tr>
						<th>#</th>
						<th>CNPJ</th>
						<th>Nome</th>
						<th>Email</th>
						<th>Endereço</th>
					</tr>
				</thead>
				<tbody>
					{agents.map((agent) => (
						<tr key={agent.id}>
							<td>{agent.id}</td>
							<td>{agent.cnpj}</td>
							<td>{agent.user.nome}</td>
							<td>{agent.user.email}</td>
							<td>{agent.user.endereco}</td>
						</tr>
					))}
				</tbody>
			</Table>
			<Modal show={modal} onHide={() => setModal(false)}>
				<Modal.Header>
					<Modal.Title>Cadastrar Agente</Modal.Title>
				</Modal.Header>
				<Form onSubmit={addAgent}>
					<Modal.Body>
						<Form.Group controlId="nome">
							<Form.Label>Nome</Form.Label>
							<Form.Control type="text" placeholder="Nome" />
						</Form.Group>

						<Form.Group controlId="email">
							<Form.Label>Email</Form.Label>
							<Form.Control type="email" placeholder="Email" />
						</Form.Group>

						<Form.Group controlId="senha">
							<Form.Label>Senha</Form.Label>
							<Form.Control type="password" placeholder="Senha" />
						</Form.Group>

						<Form.Group controlId="endereco">
							<Form.Label>Endereço</Form.Label>
							<Form.Control type="text" placeholder="Endereço" />
						</Form.Group>

						<Form.Group controlId="cnpj">
							<Form.Label>CNPJ</Form.Label>
							<Form.Control type="text" placeholder="CNPJ" />
						</Form.Group>
					</Modal.Body>
					<Modal.Footer>
						<Button
							variant="secondary"
							type="button"
							onClick={() => setModal(false)}
						>
							Fechar
						</Button>
						<Button variant="primary" type="submit">
							Cadastrar
						</Button>
					</Modal.Footer>
				</Form>
			</Modal>
		</>
	);
};

export default AdminAgentePage;
