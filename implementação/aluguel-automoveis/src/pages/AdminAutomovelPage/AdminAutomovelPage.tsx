import { FormEvent, useEffect, useState } from "react";
import { Button, Form, Modal, Table } from "react-bootstrap";
import { getReq, postReq } from "../../api/api";

const AdminAutomovelPage = () => {
	const [modal, setModal] = useState(false);
	const [vehicles, setVehicles] = useState<any[]>([]);

	const getVehicles = async () => {
		const vehicles = await getReq("automovel");

		setVehicles(vehicles);
	};

	const addVehicle = async (event: FormEvent) => {
		event.preventDefault();
		const target = event.target as any;

		const ano = target.elements.year.value;
		const marca = target.elements.brand.value;
		const modelo = target.elements.model.value;
		const placa = target.elements.license.value;

		await postReq("automovel/add", {
			ano,
			marca,
			modelo,
			placa,
		});

		await getVehicles();
		setModal(false);
	};

	useEffect(() => {
		getVehicles().then();
	}, []);

	return (
		<>
			<h1>Automóveis</h1>
			<Button onClick={() => setModal(true)}>Adicionar novo</Button>
			<div style={{ marginBottom: "20px" }} />
			<Table striped bordered hover>
				<thead>
					<tr>
						<th>#</th>
						<th>Placa</th>
						<th>Marca</th>
						<th>Modelo</th>
						<th>Ano</th>
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
						</tr>
					))}
				</tbody>
			</Table>
			<Modal show={modal} onHide={() => setModal(false)}>
				<Modal.Header>
					<Modal.Title>Cadastrar Automóvel</Modal.Title>
				</Modal.Header>
				<Form onSubmit={addVehicle}>
					<Modal.Body>
						<Form.Group controlId="year">
							<Form.Label>Ano</Form.Label>
							<Form.Control type="text" placeholder="Ano" />
						</Form.Group>

						<Form.Group controlId="brand">
							<Form.Label>Marca</Form.Label>
							<Form.Control type="text" placeholder="Marca" />
						</Form.Group>

						<Form.Group controlId="model">
							<Form.Label>Modelo</Form.Label>
							<Form.Control type="text" placeholder="Modelo" />
						</Form.Group>

						<Form.Group controlId="license">
							<Form.Label>Placa</Form.Label>
							<Form.Control type="text" placeholder="Placa" />
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

export default AdminAutomovelPage;
