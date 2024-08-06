package org.example.domain.model;

import java.util.List;

/**
 * 
 * Class that contains information about the pagination of a list of elements
 * with the list of elements and the total number of them
 * 
 * @param <T> Class of the elements contained in the List
 */
public class DomainList<T> {

	List<T> lista;

	Integer total;

	public DomainList() {
		super();
	}

	public DomainList(List<T> lista, Integer total) {
		super();
		this.lista = lista;
		this.total = total;
	}

	public List<T> getLista() {
		return lista;
	}

	public void setLista(List<T> lista) {
		this.lista = lista;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
