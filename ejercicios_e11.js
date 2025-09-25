

// Arreglo de números
let numeros = [5, 2, 9, 1, 7];

// Orden ascendente
numeros.sort((a, b) => a - b);
console.log(numeros);

// Orden descendente
numeros.sort((a, b) => b - a);
console.log(numeros);

// Arreglo de cadenas
let frutas = ["banana", "manzana", "naranja", "uva"];
frutas.sort(); // Orden alfabético por defecto
console.log(frutas);

// Orden alfabético inverso
frutas.sort((a, b) => b.localeCompare(a));
console.log(frutas);

 


let personas = [
    { nombre: "Ana", edad: 28 },
    { nombre: "Luis", edad: 22 },
    { nombre: "Carlos", edad: 35 },
    { nombre: "Marta", edad: 30 }
];

// Ordenar por edad ascendente
personas.sort((a, b) => a.edad - b.edad);
console.log(personas);


// Ordenar por nombre alfabéticamente
personas.sort((a, b) => a.nombre.localeCompare(b.nombre));
console.log(personas);

let numero = [1, 2, 3, 4, 5];
numero.reverse();
console.log(numero);

let palabras = ["Hola", "Mundo", "JavaScript"];
palabras.reverse();
console.log(palabras);

let persona = [
    { nombre: "Ana", edad: 28 },
    { nombre: "Luis", edad: 22 },
    { nombre: "Carlos", edad: 35 },
    { nombre: "Marta", edad: 30 }
];

persona.reverse();
console.log(persona);


// Ejercicos  


let arreglo1 = [10, 3, 8, 1, 6];
arreglo1.sort((a, b) => a - b);
console.log(arreglo1); // [1, 3, 6, 8, 10]


let arreglo2 = ["perro", "gato", "elefante", "ardilla"];
arreglo2.sort();
console.log(arreglo2);

// 3 

let arreglo3 = [
    { nombre: "Luis", puntaje: 85 },
    { nombre: "Marta", puntaje: 92 },
    { nombre: "Sofía", puntaje: 78 }
];
arreglo3.sort((a, b) => b.puntaje - a.puntaje);
console.log(arreglo3);

// 4 

let arreglo4 = [4, 9, 2, 7, 5];
arreglo4.reverse();
console.log(arreglo4); // [5, 7, 2, 9, 4]


let arreglo5 = [
    { producto: "Laptop", precio: 1200 },
    { producto: "Teléfono", precio: 800 },
    { producto: "Tableta", precio: 600 }
];
arreglo5.sort((a, b) => a.precio - b.precio);
console.log(arreglo5);


let arreglo6 = ["manzana", "banana", "kiwi", "fresa"];
arreglo6.reverse(); // cambiar el reverse a sort
console.log(arreglo6);