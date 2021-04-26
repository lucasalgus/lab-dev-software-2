export const getUser = () => {
	const tipo = localStorage.getItem("tipo");

	return tipo;
};

export const clearUser = () => {
	localStorage.removeItem("tipo");
};
