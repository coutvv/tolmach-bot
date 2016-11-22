package ru.coutvv.tolmach.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Word.getRu", query = "select g from Word g where g.ru = :ru"),
	 			@NamedQuery(name = "Word.getEn", query = "select g from Word g where g.en = :en") })
@Table(name = "WORDS")
public class Word {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_WORD", columnDefinition = "serial")
	private Long idWord;

	@Column(name = "RU")
	private String ru;

	@Column(name = "EN")
	private String en;
	
	@Column(name = "DESCR")
	private String descr;
	
	@Column(name = "CONTEXT")
	private String context;

	public Long getIdWord() {
		return idWord;
	}

	public void setIdWord(Long idWord) {
		this.idWord = idWord;
	}

	public String getRu() {
		return ru;
	}

	public void setRu(String ru) {
		this.ru = ru;
	}

	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String toString() {
		return idWord + "\t" + en + "\t" + ru + "\t" + descr + "\t" + context;
	}
	
	public void setLowerCase() {
		context = context == null ? null : context.toLowerCase();
		ru = ru == null ? null : ru.toLowerCase();
		en = en == null ? null : en.toLowerCase();
		descr = descr == null ? null : descr.toLowerCase();
	}
	
	public Word(String ru, String en) { 
		this.ru = ru;
		this.en = en;
	}
	
	public Word() {
		
	}
}
