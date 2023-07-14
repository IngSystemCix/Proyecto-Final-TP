// procentajes (Creador: Juan Bladimir Romero Collazos)

const totalDatos = 22712;
const numeroCoincidencias = 1672;
const porcentaje = (100 * numeroCoincidencias) / totalDatos;
const porcentajeCelda = document.getElementById("porcentaje");

porcentajeCelda.textContent = `${porcentaje.toFixed(2)}%`
