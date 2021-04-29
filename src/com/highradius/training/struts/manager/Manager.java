package com.highradius.training.struts.manager;

import java.util.ArrayList;
import java.util.List;

import com.highradius.training.struts.model.Movie;
import com.highradius.training.struts.model.RatingPOJO;
import com.highradius.training.struts.model.SpecialPJOJO;
import com.highradius.training.struts.model.languagePOJO;

public interface Manager {
	
	public List<Movie> getMovies(int start, int limit, String filter, String sort);

	public int getMoviesCount(int start, int limit, String filter);

	public List<languagePOJO> getLanguage();

	public List<RatingPOJO> getRating();

	public ArrayList<SpecialPJOJO> getSpecial();
	public boolean addMovie(Movie movie);

	public boolean editMovie(Movie movie);

	public boolean deleteMovie(String items);

	public boolean softDelete(String items) ;

}
