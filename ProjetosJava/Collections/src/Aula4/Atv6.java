package Aula4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Atv6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 System.out.println("--\tOrdem aleat�ria\t--");
	        Map<Integer, Contato> agenda = new HashMap<>() {{
	           put(1, new Contato("Simba", 5555));
	           put(4, new Contato("Cami", 1111));
	           put(3, new Contato("Jon", 2222));
	        }};
	        System.out.println(agenda);
	        for (Map.Entry<Integer, Contato> entry: agenda.entrySet()) {
	            System.out.println(entry.getKey() + " - " + entry.getValue().getNome());
	        }

	        System.out.println("--\tOrdem Inser��o\t--");
	        Map<Integer, Contato> agenda1 = new LinkedHashMap<>() {{
	            put(1, new Contato("Simba", 5555));
	            put(4, new Contato("Cami", 1111));
	            put(3, new Contato("Jon", 2222));
	        }};
	        System.out.println(agenda1);
	        for (Map.Entry<Integer, Contato> entry: agenda1.entrySet()) {
	            System.out.println(entry.getKey() + " - " + entry.getValue().getNome());
	        }

	        System.out.println("--\tOrdem id\t--");
	        Map<Integer, Contato> agenda2 = new TreeMap<>(agenda);
	        System.out.println(agenda2);
	        for (Map.Entry<Integer, Contato> entry: agenda2.entrySet()) {
	            System.out.println(entry.getKey() + " - " + entry.getValue().getNome());
	        }

	        System.out.println("--\tOrdem n�mero telefone\t--");
	        //precisamos organizar os valores. Logo:
	        Set<Map.Entry<Integer, Contato>> set = new TreeSet<>(new ComparatorOrdemNumerica());
	        set.addAll(agenda.entrySet());
	        for (Map.Entry<Integer, Contato> entry: set) {
	            System.out.println(entry.getKey() + " - " + entry.getValue().getNumero() +
	                    ": " +entry.getValue().getNome());
	        }

	        System.out.println("--\tOrdem nome contato\t--");
	        //precisamos organizar os valores. Logo:
	        Set<Map.Entry<Integer, Contato>> set1 = new TreeSet<>(new ComparatorOrdemNomeContato());
	        set1.addAll(agenda.entrySet());
	        for (Map.Entry<Integer, Contato> entry: set1) {
	            System.out.println(entry.getKey() + " - " + entry.getValue().getNome());
	        }
	    }

	}

class Contato {
    private String nome;
    private Integer numero;

    public Contato(String nome, Integer numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public Integer getNumero() {
        return numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return nome.equals(contato.nome) && numero.equals(contato.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, numero);
    }

    @Override
    public String toString() {
        return "Contato{" +
                "nome='" + nome + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}

class ComparatorOrdemNumerica implements Comparator<Map.Entry<Integer, Contato>> {
    @Override
    public int compare(Map.Entry<Integer, Contato> cont1, Map.Entry<Integer, Contato> cont2) {
        return Integer.compare(cont1.getValue().getNumero(), cont2.getValue().getNumero());
    }
}

class ComparatorOrdemNomeContato implements Comparator<Map.Entry<Integer, Contato>> {
    @Override
    public int compare(Map.Entry<Integer, Contato> cont1, Map.Entry<Integer, Contato> cont2) {
        return cont1.getValue().getNome().compareToIgnoreCase(cont2.getValue().getNome());
    }
}
