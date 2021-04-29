package com.highradius.training.struts.managerimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.highradius.training.struts.dao.Dao;
import com.highradius.training.struts.daoimpl.DaoImpl;
import com.highradius.training.struts.manager.Manager;
import com.highradius.training.struts.model.Movie;
import com.highradius.training.struts.model.RatingPOJO;
import com.highradius.training.struts.model.languagePOJO;
import com.highradius.training.struts.model.SpecialPJOJO;

public class ManagerImpl implements Manager{
	Dao dao;
//	= new Dao();

	public List<Movie> getMovies(int start, int limit, String filter, String sort) {

//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		Dao dao = (Dao)context.getBean("dao");

		//obj = dao.getMovies
		// business logic
		
		//return obj
		return dao.getMovies(start, limit, filter, sort);
	}

	public int getMoviesCount(int start, int limit, String filter) {
		return dao.getMoviesCount(start, limit, filter);
	}

	public List<languagePOJO> getLanguage() {
		return dao.getLanguage();
	}

	public List<RatingPOJO> getRating() {
		return dao.getRating();
	}

	public ArrayList<SpecialPJOJO> getSpecial() {
		return dao.getSpecial();
	}

	public boolean addMovie(Movie movie) {
		return dao.addMovie(movie);
	}

	public boolean editMovie(Movie movie) {
		return dao.editMovie(movie);
	}

	public boolean deleteMovie(String items) {
		return dao.deleteMovie(items);
	}

	public boolean softDelete(String items) {
		return dao.softDelete(items);
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

}
