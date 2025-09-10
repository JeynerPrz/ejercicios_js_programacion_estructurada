

let agregarElemento = (arreglo, elementos)  => {
    arreglo.push(elementos);
    return arreglo;
}
 let numeros =[1,2,3]
 console.log(agregarElemento(numeros, 8 ));

 // 2

let agregarElementoInicio = (arreglo, elementos)  => {
    arreglo.unshif(elementos);
    return arreglo;
}
 let numeros2 =[1,2,3];
 console.log(agregarElementoInicio(numeros2, 0 ));



 // 3

 let    EliminarElementoFinal = (arreglo)  => {
    let ultimo = arreglo.pop();
    return ultimo;
}

let numeros3 = [24,32,89,51]
console.log(EliminarElementoFinal(numeros3));

// 4

let    EliminarElementoInicio = (arreglo)  => {
    let primerElemento = arreglo.shift();
    return primerElemento;
}

let frutas =['manzan','banana', 'pera'];
console.log(frutas);