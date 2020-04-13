package com.example.criarExcel;

public class Tarefa {

    private String nome, data, horaInicio, horaFim, tipoAtividade, nomeProjeto, cliente, descricao;

    @Override
    public String toString() {
        return "Tarefa{" +
                "nome='" + nome + '\'' +
                ", data='" + data + '\'' +
                ", horaInicio='" + horaInicio + '\'' +
                ", horaFim='" + horaFim + '\'' +
                ", tipoAtividade='" + tipoAtividade + '\'' +
                ", nomeProjeto='" + nomeProjeto + '\'' +
                ", cliente='" + cliente + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getData() { return data; }

    public void setData(String data) { this.data = data; }

    public String getHoraInicio() { return horaInicio; }

    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }

    public String getHoraFim() { return horaFim; }

    public void setHoraFim(String horaFim) { this.horaFim = horaFim; }

    public String getTipoAtividade() { return tipoAtividade; }

    public void setTipoAtividade(String tipoAtividade) { this.tipoAtividade = tipoAtividade; }

    public String getNomeProjeto() { return nomeProjeto; }

    public void setNomeProjeto(String nomeProjeto) { this.nomeProjeto = nomeProjeto; }

    public String getCliente() { return cliente; }

    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }
}
