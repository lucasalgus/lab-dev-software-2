import { FormEvent, useEffect, useState } from "react";
import { Button, Form, Modal, Table } from "react-bootstrap";
import { deleteReq, getReq, postReq, putReq } from "../../api/api";

const ClienteAluguelPage = () => {
	const [aluguelOperation, setAluguelOperation] = useState<
		"editar" | "cadastrar"
	>();
	const [vehicleID, setVehicleID] = useState(0);
	const [aluguelID, setAluguelID] = useState(0);
	const [modal, setModal] = useState(false);
	const [vehicles, setVehicles] = useState<any[]>([]);
	const [userAlugueis, setUserAlugueis] = useState<any[]>([]);

	const getData = async () => {
		const alugueis = await getReq("aluguel");
		const vehicles = await getReq("automovel");
		const clienteID = +(localStorage.getItem("id") || 0);

		const userAlugueis = alugueis.filter(
			(aluguel: any) => aluguel.clienteID === clienteID
		);

		const availableVehicles = vehicles.filter((vehicle: any) => {
			const hasAluguel = alugueis.find(
				(aluguel: any) => aluguel.automovelID === vehicle.id
			);

			return !hasAluguel;
		});

		setUserAlugueis(userAlugueis);
		setVehicles(availableVehicles);
	};

	const addAluguel = async (event: FormEvent) => {
		event.preventDefault();
		const target = event.target as any;

		const ativo = false;
		const dataInicio = target.elements.dataInicio.value;
		const dataFim = target.elements.dataInicio.value;
		const automovelID = vehicleID;
		const clienteID = +(localStorage.getItem("id") || 0);

		await postReq("aluguel/add", {
			ativo,
			dataInicio,
			dataFim,
			automovelID,
			clienteID,
		});

		setModal(false);
		await getData();
	};

	const editAluguel = async (event: FormEvent) => {
		event.preventDefault();
		const target = event.target as any;

		const ativo = false;
		const dataInicio = target.elements.dataInicio.value;
		const dataFim = target.elements.dataInicio.value;
		const automovelID = vehicleID;
		const clienteID = +(localStorage.getItem("id") || 0);

		await putReq("aluguel/" + aluguelID, {
			ativo,
			dataInicio,
			dataFim,
			automovelID,
			clienteID,
		});

		setModal(false);
		await getData();
	};

	const deleteAluguel = async (id: number, ativo: boolean) => {
		if (!ativo) {
			await deleteReq("aluguel/" + id);
			await getData();
			alert("Aluguel deletado com sucesso!");

			return;
		}

		alert("Não é possível deletar um aluguel ativo");
	};

	useEffect(() => {
		getData().then();
	}, []);

	return (
		<>
			<h1>Meus Alugueis</h1>
			<Table striped bordered hover>
				<thead>
					<tr>
						<th>#</th>
						<th>Data Início</th>
						<th>Data Fim</th>
						<th>Contrato</th>
						<th>Status</th>
						<th>-</th>
						<th>-</th>
					</tr>
				</thead>
				<tbody>
					{userAlugueis.map((aluguel) => (
						<tr>
							<td>{aluguel.id}</td>
							<td>{aluguel.dataInicio}</td>
							<td>{aluguel.dataFim}</td>
							<td>{aluguel.contrato || "Nenhum"}</td>
							<td>{aluguel.ativo ? "Ativo" : "Pendente"}</td>
							<td>
								<Button
									onClick={() => {
										setAluguelOperation("editar");
										setAluguelID(aluguel.id);
										setModal(true);
									}}
								>
									Editar
								</Button>
							</td>
							<td>
								<Button
									onClick={() => deleteAluguel(aluguel.id, aluguel.ativo)}
									variant="danger"
								>
									Apagar
								</Button>
							</td>
						</tr>
					))}
				</tbody>
			</Table>

			<h1>Automóveis Disponíveis para Aluguel</h1>
			<Table striped bordered hover>
				<thead>
					<tr>
						<th>#</th>
						<th>Placa</th>
						<th>Marca</th>
						<th>Modelo</th>
						<th>Ano</th>
						<th>-</th>
					</tr>
				</thead>
				<tbody>
					{vehicles.map((vehicle) => (
						<tr key={vehicle.id}>
							<td>{vehicle.id}</td>
							<td>{vehicle.placa}</td>
							<td>{vehicle.marca}</td>
							<td>{vehicle.modelo}</td>
							<td>{vehicle.ano}</td>
							<td>
								<Button
									onClick={() => {
										setVehicleID(vehicle.id);
										setAluguelOperation("cadastrar");
										setModal(true);
									}}
								>
									Alugar
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
				<Form
					onSubmit={aluguelOperation === "cadastrar" ? addAluguel : editAluguel}
				>
					<Modal.Body>
						<Form.Group controlId="dataInicio">
							<Form.Label>Data Início</Form.Label>
							<Form.Control type="date" placeholder="Nome" />
						</Form.Group>

						<Form.Group controlId="dataFim">
							<Form.Label>Data Fim</Form.Label>
							<Form.Control type="date" placeholder="Email" />
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
							{aluguelOperation === "cadastrar"
								? "Fazer Pedido"
								: "Alterar Pedido"}
						</Button>
					</Modal.Footer>
				</Form>
			</Modal>
		</>
	);
};

export default ClienteAluguelPage;
