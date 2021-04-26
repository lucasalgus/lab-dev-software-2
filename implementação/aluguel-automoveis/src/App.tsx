import { Nav, Navbar } from "react-bootstrap";
import { Route, Switch } from "react-router";
import { BrowserRouter, Link } from "react-router-dom";
import { getUser } from "./api/auth";
import AdminAgentePage from "./pages/AdminAgentePage/AdminAgentePage";
import AdminAutomovelPage from "./pages/AdminAutomovelPage/AdminAutomovelPage";
import AgenteAluguelPage from "./pages/AgenteAluguelPage/AgenteAluguelPage";
import CadastroPage from "./pages/CadastroPage/CadastroPage";
import ClienteAluguelPage from "./pages/ClienteAluguelPage/ClienteAluguelPage";
import LoginPage from "./pages/LoginPage/LoginPage";

const App = () => {
	const logout = () => {
		localStorage.removeItem("tipo");
		localStorage.removeItem("id");

		window.location.reload();
		window.location.href = "";
	};

	return (
		<div>
			<BrowserRouter>
				<Navbar bg="light">
					<Navbar.Brand href="/">Aluguel de Automóveis</Navbar.Brand>
					<Nav>
						<Link className="nav-link" to="/">
							Início
						</Link>
						{!getUser() ? (
							<>
								<Link className="nav-link" to="/login">
									Login
								</Link>
								<Link className="nav-link" to="/cadastro">
									Cadastro
								</Link>
							</>
						) : null}
						{getUser() === "cliente" ? (
							<Link className="nav-link" to="/cliente/alugar">
								Alugar Carros
							</Link>
						) : null}
						{getUser() === "agente" ? (
							<Link className="nav-link" to="/agente/alugueis">
								Gerenciar Alugueis
							</Link>
						) : null}
						{getUser() === "administrador" ? (
							<>
								<Link className="nav-link" to="/admin/automoveis">
									Gerenciar Automóveis
								</Link>
								<Link className="nav-link" to="/admin/agentes">
									Gerenciar Agentes
								</Link>
							</>
						) : null}
						{getUser() ? (
							<Link className="nav-link" to="#" onClick={logout}>
								Sair
							</Link>
						) : null}
					</Nav>
				</Navbar>
				<div style={{ margin: "20px 40px" }}>
					<Switch>
						<Route exact path="/">
							<h1>Home do Aluguel de Automóveis</h1>
						</Route>
						<Route exact path="/login">
							<LoginPage />
						</Route>
						<Route exact path="/cadastro">
							<CadastroPage />
						</Route>
						<Route exact path="/cliente/alugar">
							<ClienteAluguelPage />
						</Route>
						<Route exact path="/agente/alugueis">
							<AgenteAluguelPage />
						</Route>
						<Route exact path="/admin/automoveis">
							<AdminAutomovelPage />
						</Route>
						<Route exact path="/admin/agentes">
							<AdminAgentePage />
						</Route>
					</Switch>
				</div>
			</BrowserRouter>
		</div>
	);
};

export default App;
