import { FormEvent, useEffect, useState } from "react";
import { Button, Form, Modal, Table } from "react-bootstrap";
import { getReq, putReq } from "../../api/api";

const AgenteAluguelPage = () => {
	const [modal, setModal] = useState(false);
	const [aluguelID, setAluguelID] = useState(0);
	const [alugueis, setAlugueis] = useState<any[]>([]);

	const getData = async () => {
		const alugueis = await getReq("aluguel");
		const clientes = await getReq("cliente");

		const pendingAlugueis = alugueis
			.filter((aluguel: any) => !aluguel.ativo)
			.map((aluguel: any) => ({
				...aluguel,
				cliente: clientes.find(
					(cliente: any) => cliente.id === aluguel.clienteID
				),
			}));

		setAlugueis(pendingAlugueis);
	};

	const updateAluguel = async (event: FormEvent) => {
		event.preventDefault();
		const target = event.target as any;

		const agenteID = +(localStorage.getItem("id") || 0);
		const contrato = target.elements.contrato.value;
		const proprietario = target.elements.proprietario.value;

		const aluguel = await getReq("aluguel/" + aluguelID);
		const cliente = await getReq("cliente/" + aluguel.clienteID);
		const agente = await getReq("agente/" + agenteID);

		console.log(cliente);
		console.log(agente);

		await putReq("aluguel/" + aluguelID, {
			...aluguel,
			ativo: true,
			dataInicio: new Date(aluguel.dataInicio).toISOString(),
			dataFim: new Date(aluguel.dataFim).toISOString(),
			agenteID,
			contrato,
			proprietarioID:
				proprietario === "Agente" ? agente.usuarioID : cliente.usuarioID,
		});

		setModal(false);
		await getData();
	};

	useEffect(() => {
		getData().then();
	}, []);

	return (
		<>
			<h1>Alugueis Pendentes</h1>
			<Table striped bordered hover>
				<thead>
					<tr>
						<th>#</th>
						<th>Data Início</th>
						<th>Data Fim</th>
						<th>CPF Cliente</th>
						<th>-</th>
					</tr>
				</thead>
				<tbody>
					{alugueis.map((aluguel) => (
						<tr key={aluguel.id}>
							<td>{aluguel.id}</td>
							<td>{aluguel.dataInicio}</td>
							<td>{aluguel.dataFim}</td>
							<td>{aluguel.cliente.cpf}</td>
							<td>
								<Button
									onClick={() => {
										setAluguelID(aluguel.id);
										setModal(true);
									}}
								>
									Aprovar
								</Button>
							</td>
						</tr>
					))}
				</tbody>
			</Table>

			<Modal show={modal} onHide={() => setModal(false)}>
				<Modal.Header>
					<Modal.Title>Pedido de Aluguel</Modal.Title>
				</Modal.Header>
				<Form onSubmit={updateAluguel}>
					<Modal.Body>
						<Form.Group controlId="contrato">
							<Form.Label>Contrato</Form.Label>
							<Form.Control type="text" placeholder="Contrato" />
						</Form.Group>

						<Form.Group controlId="proprietario">
							<Form.Label>Proprietário</Form.Label>
							<Form.Control as="select" custom>
								<option>Agente</option>
								<option>Cliente</option>
							</Form.Control>
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
							Fazer Pedido
						</Button>
					</Modal.Footer>
				</Form>
			</Modal>
		</>
	);
};

export default AgenteAluguelPage;
