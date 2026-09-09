# Prova Prática - Missão Refatoração (Clean Code & OO)

João Vitor Anunciação Oliveira - RM 567539
Programação Orientada a Objetos - FiapRide, módulo de frota

## O que é esse projeto

O enunciado deu duas classes prontas escritas por um estagiário e mandou consertar. A classe `veiculos` guardava os dados de um carro da frota e deixava fazer besteira: dava pra colocar gasolina negativa e dava pra gastar mais combustível do que tinha no tanque.

Só que o código estava pior do que o enunciado dizia. Quando copiei ele pro Eclipse, nem compilou:

```
incompatible types: possible lossy conversion from double to int
```

O erro é no `gasta(double v)`, que fazia `gas = gas - v` com `gas` declarado como `int`. Java não deixa. Então aquele código nunca chegou a rodar de verdade. Aquela gasolina em -60 que o enunciado descreve só aconteceria depois de arrumar o tipo do atributo.

## O que o objeto representa

Cada `Veiculo` é um carro cadastrado na frota do FiapRide, desses que vão buscar o passageiro. O `nomeProprietario` é o motorista dono do carro, a `placa` é a identificação dele no trânsito, e o `nivelCombustivel` é quanto ainda tem no tanque, em litros.

O `new Veiculo("Carlos", "ABC-1234")` é a frota cadastrando um carro. Entra com dono e placa, e com o tanque em zero porque ninguém abasteceu ainda. Abastecer e rodar são as duas coisas que acontecem com esse carro no dia a dia, e são os dois métodos que eu fiz.

## O que eu mudei

| Antes | Depois |
| --- | --- |
| `class veiculos` | `class Veiculo` |
| `class principal` | `class SistemaPrincipal` |
| `public String individuo` | `private String nomeProprietario` |
| `public String pl` | `private String placa` |
| `public int gas` | `private double nivelCombustivel` |
| `adicionar(int v)` | `abastecer(double litros)` |
| `gasta(double v)` | `consumirCombustivel(double litros)` |

Nome de classe no singular e com maiúscula, porque a classe é o molde de um veículo só. "individuo" e "pl" não dizem nada num sistema de frota. O `gas` era `int`, mas combustível tem casas decimais. Os nomes dos métodos viraram verbo no infinitivo, e os parâmetros deixaram de se chamar `v`.

Os atributos todos viraram `private`. Coloquei também um construtor `Veiculo(nomeProprietario, placa)`, então os dados essenciais entram na hora de criar o objeto, em vez do main preencher campo por campo depois.

Sobre os getters e setters, no rascunho do diagrama estava invertido: o `get_individuo()` era privado e o `setGas()` era público. Fiz o contrário. Os getters são públicos porque só leem, não estragam nada. Os setters são privados, então de fora da classe o combustível só muda passando por `abastecer` e `consumirCombustivel`, que é onde estão as regras. Dono e placa entram no construtor e depois não mudam mais.

## Como usar os métodos

```
Veiculo veiculo1 = new Veiculo("Carlos", "ABC-1234"); // tanque começa em 0.0

veiculo1.abastecer(50.0);            // soma litros no tanque
veiculo1.consumirCombustivel(20.0);  // desconta litros do tanque

veiculo1.getNomeProprietario();  // "Carlos"
veiculo1.getPlaca();             // "ABC-1234"
veiculo1.getNivelCombustivel();  // 30.0
```

Os dois métodos não devolvem nada. Eles alteram o estado do objeto e avisam no console o que aconteceu. Pra ler os dados é pelos getters.

## As regras que eu coloquei

1. O `abastecer` recusa valor zero ou negativo.
2. O `consumirCombustivel` recusa valor zero ou negativo. Sem isso, passar `-50` faria a conta virar `nivel - (-50)` e o carro ganharia combustível em vez de gastar.
3. O `consumirCombustivel` recusa a viagem se não tiver combustível suficiente no tanque.

Fora essas três, o `setNivelCombustivel` é privado e bloqueia valor negativo antes dele encostar no atributo. Botei essa checagem por garantia, caso eu mexa na classe depois e esqueça de validar.

A regra 1 e a regra 3 são os dois perigos que o enunciado apontou.

## Rodando

Testei os dois métodos com valor válido e com valor inválido, que é o que a Aula 02 pede:

```
Erro: o valor do abastecimento deve ser maior que zero.
Abastecimento realizado. Nivel atual: 50.0
Viagem realizada. Combustivel restante: 30.0
Erro: combustivel insuficiente para realizar a viagem.
Dono: Carlos | Placa: ABC-1234 | Gasolina: 30.0
```

O `abastecer` recusou os -10 e aceitou os 50. O `consumirCombustivel` aceitou os 20 e descontou do tanque, mas recusou os 100 porque só restavam 30 litros.

Pra rodar depois de clonar: no Eclipse, `File > New > Java Project` apontando pra essa pasta (o código já está em `src/`), abre o `SistemaPrincipal.java` e aperta `Ctrl + F11`. Pelo terminal:

```
javac -d bin src/br/com/fiapride/model/Veiculo.java src/br/com/fiapride/main/SistemaPrincipal.java
java -cp bin br.com.fiapride.main.SistemaPrincipal
```

## Testando a proteção

Se colocar essa linha no main:

```
veiculo1.nivelCombustivel = 999.0;
```

o projeto não compila: `nivelCombustivel has private access in Veiculo`. Não dá mais pra mexer no carro por fora da classe.

## Arquivos

```
diagrama-veiculo-refatorado.png   diagrama do Astah, na raiz porque o enunciado pede assim
docs/veiculo.asta                 o arquivo que abre no Astah
src/br/com/fiapride/model/Veiculo.java
src/br/com/fiapride/main/SistemaPrincipal.java
```

A pasta `bin/` e os arquivos de configuração do Eclipse não sobem pro GitHub, estão no `.gitignore`.
