package ru.coutvv.tolmach.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "USER_WORD")
@NamedQueries({ @NamedQuery(name = "UserWord.getByUserId", query = "select g from UserWord g where g.user.idUser = :idUser"),
				@NamedQuery(name = "UserWord.getByUserName", query = "select g from UserWord g where g.user.username = :username"),
				@NamedQuery(name = "UserWord.getByEnWord", query = "select g from UserWord g where g.word.en = :en"),
				@NamedQuery(name = "UserWord.getByRuWord", query = "select g from UserWord g where g.word.ru = :ru"),
				@NamedQuery(name = "UserWord.getByEnWordAndUserName", query = "select g from UserWord g where g.word.en = :en and g.username = :username"),
				@NamedQuery(name = "UserWord.getByRuWordAndUserName", query = "select g from UserWord g where g.word.ru = :ru and g.username = :username")})
public class UserWord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", columnDefinition = "serial")
	private Long id;

	@Column(name = "ID_USER")
	private User user;
	@Column(name = "ID_WORD")
	private Word word;

	@Column(name = "ADD_DATE")
	private Date addDate;
	@Column(name = "LAST_DATE")
	private Date lastDate;
	
	@Column(name = "RATING")
	private Long rating;

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
		this.word = word;
	}
}