const mascotas = [
    { nombre: "Luna", edad: 3, tipo: "Perro", peso: 10.5 },
    { nombre: "Max", edad: 5, tipo: "Gato", peso: 4.2 },
    { nombre: "Coco", edad: 1, tipo: "Conejo", peso: 2.3 },
    { nombre: "Rocky", edad: 7, tipo: "Perro", peso: 15.8 },
    { nombre: "Milo", edad: 2, tipo: "Gato", peso: 3.9 },
    { nombre: "Bella", edad: 4, tipo: "Perro", peso: 8.7 },
    { nombre: "Toby", edad: 6, tipo: "Conejo", peso: 2.8 },
    { nombre: "Simba", edad: 3, tipo: "Gato", peso: 5.1 },
    { nombre: "Nala", edad: 2, tipo: "Perro", peso: 12.4 },
    { nombre: "Oreo", edad: 5, tipo: "Gato", peso: 4.8 },
    { nombre: "Daisy", edad: 1, tipo: "Conejo", peso: 1.9 },
    { nombre: "Zeus", edad: 8, tipo: "Perro", peso: 20.1 },
    { nombre: "Mimi", edad: 4, tipo: "Gato", peso: 4.5 },
    { nombre: "Bunny", edad: 3, tipo: "Conejo", peso: 2.5 },
    { nombre: "Thor", edad: 6, tipo: "Perro", peso: 18.3 }
];

// Ordenar por edad ascendente

const porEdadAsc = mascotas.sort((a, b) => a.edad - b.edad);
console.log(porEdadAsc);


// Ordenar las mascotas por  de forma alfabética usando sort.
const porNombre = mascotas.sort((a, b) => a.nombre.localeCompare(b.nombre));
console.log(porNombre);

// Ordenar las mascotas por  de forma ascendente usando sort.
const porPesoDesc = mascotas.sort((a, b) => b.peso - a.peso);
console.log(porPesoDesc);
// Ordenar las mascotas por  de forma descendente usando sort.
const porTipoYEdad = mascotas.sort((a, b) => {
if (a.tipo === b.tipo) {
return a.edad
b.edad; // Si son del mismo tipo, ordenar por edad
}
return a.tipo.localeCompare(b.tipo); // Si no, ordenar por tipo
});
console.log(porTipoYEdad);
// Ordenar las mascotas por  alfabéticamente y, si tienen el mismo tipo, por  ascendente usando sort.
//Invertir el orden del arreglo de mascotas usando reverse.
//Crear un nuevo arreglo con los nombres de las mascotas en mayúsculas usando map.
//Calcular el peso total de todas las mascotas usando reduce.
// Crear un nuevo arreglo con solo las mascotas que son perros usando filter.ssss