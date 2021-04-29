package com.highradius.training.struts.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.highradius.training.struts.model.Movie;
import com.highradius.training.struts.model.RatingPOJO;
import com.highradius.training.struts.model.SpecialPJOJO;
import com.highradius.training.struts.model.languagePOJO;
import com.highradius.training.struts.manager.Manager;
import com.highradius.training.struts.managerimpl.ManagerImpl;

public class Action {

	private String start;
	private String limit;
	private String filter;
	private String sort;
	private String result;
	private String title;
	private String desc;
	private String director;
	private String items;
	private String id;
	private String rating;
	private String releaseyear;
	private String language;
	private String sp;
	private boolean succ;

//	Maneger manager = new Maneger();

	static final String JDBC_Driver = "com.mysql.jdbc.Driver";
	static final String DB_Url = "jdbc:mysql://localhost/sakila";

	static final String username = "root";
	static final String password = "root";

	public String getData() {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		Manager manager = (Manager) context.getBean("manager");

		List<Movie> movies = manager.getMovies(Integer.parseInt(getStart()), Integer.parseInt(getLimit()), getFilter(),
				getSort());
		int total = manager.getMoviesCount(Integer.parseInt(getStart()), Integer.parseInt(getLimit()), getFilter());

		HashMap result1 = new HashMap();
		result1.put("success", true);

		result1.put("total", total);
		List<Object> temp = new ArrayList();
		for(Movie movie : movies) {
			HashMap m = new HashMap();
			m.put("id",movie.getId());
			m.put("title",movie.getTitle());
			m.put("language", movie.getLanguage().getName());
			m.put("desc",movie.getDesc());
			m.put("releaseyear",movie.getReleaseyear());
			m.put("director",movie.getDirector());
			m.put("rating",movie.getRating());
			m.put("special",movie.getSpecial());
			
			
			temp.add(m);
		}
		result1.put("movies", temp);

		Gson gson = new Gson();
		setResult(gson.toJson(result1));
		return "Success";
			
	
	}

	public String getLanguage() {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		Manager manager = (Manager) context.getBean("manager");

		List<languagePOJO> languages = manager.getLanguage();
		HashMap result1 = new HashMap();
		result1.put("language", languages);
		Gson gson = new Gson();
		setResult(gson.toJson(result1));
		return "Success";

	}

	public String addMovie() {

		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		Manager manager = (Manager) context.getBean("manager");
		Movie movie = new Movie();
		languagePOJO lang = new languagePOJO();
		lang.setName(language);
		movie.setTitle(title);
		movie.setDesc(desc);
		movie.setReleaseyear(Integer.parseInt(releaseyear));
		movie.setDirector(director);
		movie.setIsDeleted(0);
		movie.setLanguage(lang);
		movie.setRating(rating);
		movie.setSpecial(sp);
		
		
		setSucc(manager.addMovie(movie));
		return "Success";
	}

	public String editMovie() {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		Manager manager = (Manager) context.getBean("manager");
		
		Movie movie = new Movie();
		languagePOJO lang = new languagePOJO();
		lang.setName(language);
		movie.setId(Integer.parseInt(id));
		movie.setTitle(title);
		movie.setDesc(desc);
		movie.setReleaseyear(Integer.parseInt(releaseyear));
		movie.setDirector(director);
		movie.setIsDeleted(0);
		movie.setLanguage(lang);
		movie.setRating(rating);
		movie.setSpecial(sp);
		
		setSucc(manager.editMovie(movie));
		return "Success";
	}

	public String deleteMovie() {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		Manager manager = (Manager) context.getBean("manager");

		setSucc(manager.deleteMovie(items));
		return "Success";
	}

	public String softDelete() {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		Manager manager = (Manager) context.getBean("manager");

		setSucc(manager.softDelete(items));
		return "Success";
	}

	public String getMovieRating() {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		Manager manager = (Manager) context.getBean("manager");

		List<RatingPOJO> ratings = manager.getRating();

		HashMap result1 = new HashMap();
		result1.put("Rating", ratings);

		Gson gson = new Gson();
		setResult(gson.toJson(result1));
		return "Success";
	}

	public String getSpecial() {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		Manager manager = (Manager) context.getBean("manager");
		ArrayList<SpecialPJOJO> Sp = manager.getSpecial();

		HashMap result1 = new HashMap();
		result1.put("special", Sp);
		Gson gson = new Gson();
		setResult(gson.toJson(result1));
		return "Success";

	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReleaseyear() {
		return releaseyear;
	}

	public void setReleaseyear(String releaseyear) {
		this.releaseyear = releaseyear;
	}

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public boolean isSucc() {
		return succ;
	}

	public void setSucc(boolean succ) {
		this.succ = succ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

}
