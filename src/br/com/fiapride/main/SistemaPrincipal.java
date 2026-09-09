package br.com.fiapride.main;

import br.com.fiapride.model.Veiculo;

public class SistemaPrincipal {

	public static void main(String[] args) {

		Veiculo veiculo1 = new Veiculo("Carlos", "ABC-1234");

		veiculo1.abastecer(-10.0);
		veiculo1.abastecer(50.0);
		veiculo1.consumirCombustivel(20.0);
		veiculo1.consumirCombustivel(100.0);

		System.out.println("Dono: " + veiculo1.getNomeProprietario()
				+ " | Placa: " + veiculo1.getPlaca()
				+ " | Gasolina: " + veiculo1.getNivelCombustivel());
	}
}
