package br.com.fiapride.model;

public class Veiculo {

	private String nomeProprietario;
	private String placa;
	private double nivelCombustivel;

	public Veiculo(String nomeProprietario, String placa) {
		this.setNomeProprietario(nomeProprietario);
		this.setPlaca(placa);
		this.setNivelCombustivel(0.0);
	}

	public void abastecer(double litros) {

		if (litros <= 0) {
			System.out.println("Erro: o valor do abastecimento deve ser maior que zero.");
			return;
		}

		this.setNivelCombustivel(this.nivelCombustivel + litros);
		System.out.println("Abastecimento realizado. Nivel atual: " + this.nivelCombustivel);
	}

	public void consumirCombustivel(double litros) {

		if (litros <= 0) {
			System.out.println("Erro: o consumo informado e invalido.");
			return;
		}

		if (litros > this.nivelCombustivel) {
			System.out.println("Erro: combustivel insuficiente para realizar a viagem.");
			return;
		}

		this.setNivelCombustivel(this.nivelCombustivel - litros);
		System.out.println("Viagem realizada. Combustivel restante: " + this.nivelCombustivel);
	}

	public String getNomeProprietario() {
		return this.nomeProprietario;
	}

	private void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public String getPlaca() {
		return this.placa;
	}

	private void setPlaca(String placa) {
		this.placa = placa;
	}

	public double getNivelCombustivel() {
		return this.nivelCombustivel;
	}

	private void setNivelCombustivel(double nivelCombustivel) {

		if (nivelCombustivel < 0) {
			System.out.println("Erro de seguranca: nivel de combustivel negativo bloqueado!");
			return;
		}

		this.nivelCombustivel = nivelCombustivel;
	}
}
