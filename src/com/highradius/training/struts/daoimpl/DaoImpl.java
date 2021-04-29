	package com.highradius.training.struts.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.highradius.training.struts.dao.Dao;
import com.highradius.training.struts.model.Movie;
import com.highradius.training.struts.model.RatingPOJO;
import com.highradius.training.struts.model.SpecialPJOJO;
import com.highradius.training.struts.model.languagePOJO;

public class DaoImpl implements Dao{
	
	private SessionFactory sessionFactory;
//	private static ServiceRegistry serviceRegistry;
	
	public SessionFactory createSessionFactory() {
		try {
			
			Configuration config = new Configuration();
			config.configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			sessionFactory = config.buildSessionFactory(serviceRegistry);
			
		}catch(Throwable ex) {
			System.err.println("Failed to create sessionFactory object" + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		return sessionFactory;
	}


	
	static final String JDBC_Driver = "com.mysql.jdbc.Driver";
	static final String DB_Url = "jdbc:mysql://localhost/sakila";

	static final String username = "root";
	static final String password = "root";

	public List<Movie> getMovies(int start, int limit, String filter, String sort) {
		List<Movie> movies = new ArrayList();
//		try {
//
//			String sql2 = "SELECT film_id AS id,IFNULL(`title`,'unknown') title, IFNULL(`description`,'unknown') `desc`,IFNULL(`release_year`,'0') `releaseyear`,NAME `language`,IFNULL(`rating`,'unknown') `rating`,IFNULL(`special_features`,'unknown') `special`,IFNULL(`director`,'Unknown') `director`FROM `film` ff JOIN LANGUAGE ON ff.language_id =  LANGUAGE.language_id WHERE is_deleted = 0 ";
//			String sql3 = " LIMIT " + start + "," + limit;
//
//			if (filter != null) {
//				String filterQuery = " and ";
//				JsonParser parser1 = new JsonParser();
//				JsonElement json1 = parser1.parse(filter);
//				if (json1.isJsonArray()) {
//					JsonArray jarr1 = json1.getAsJsonArray();
//					int i = 1;
//					for (JsonElement je1 : jarr1) {
//						JsonObject jsonobj1 = je1.getAsJsonObject();
//						if (i == 1) {
//							filterQuery += jsonobj1.get("property").getAsString() + " LIKE '%"
//									+ jsonobj1.get("value").getAsString() + "%'";
//							i++;
//						} else {
//							filterQuery += " and " + jsonobj1.get("property").getAsString() + " LIKE '%"
//									+ jsonobj1.get("value").getAsString() + "%'";
//						}
//
//					}
//				}
//				filterQuery = filterQuery.replaceAll("language", "language.name");
//				filterQuery = filterQuery.replaceAll("releaseyear", "release_year");
//				sql2 = sql2 + filterQuery;
//
//			}
//
//			if (sort != null) {
//				String sortQuery = " ORDER BY ";
//				JsonParser parser = new JsonParser();
//				JsonElement json = parser.parse(sort);
//				if (json.isJsonArray()) {
//					JsonArray jarr = json.getAsJsonArray();
//					for (JsonElement je : jarr) {
//						JsonObject jsonobj = je.getAsJsonObject();
//						sortQuery += jsonobj.get("property").getAsString() + " "
//								+ jsonobj.get("direction").getAsString();
//
//					}
//				}
//				sortQuery = sortQuery.replaceAll("desc", "description");
//				sql2 = sql2 + sortQuery;
//			}
//
//			sql2 = sql2 + sql3;
//
//			Class.forName("com.mysql.jdbc.Driver"); // Register the JDBC Driver
//			Connection conn = DriverManager.getConnection(DB_Url, username, password); // Open a connection
//
//			// Executing a query
//			Statement stm = conn.createStatement();
//			Statement stm1 = conn.createStatement();
//			String sql;
//
//			sql = sql2;
//
//			ResultSet rs = stm.executeQuery(sql);
//
//			while (rs.next()) {
//				Movie movie = new Movie();
//				movie.setId(rs.getInt("id"));
//				movie.setTitle(rs.getString("title"));
//				movie.setDesc(rs.getString("desc"));
//				movie.setReleaseyear(rs.getInt("releaseyear"));
//				//movie.setLanguage(rs.getString("language"));
//				movie.setDirector(rs.getString("director"));
//				movie.setRating(rs.getString("rating"));
//				movie.setSpecial(rs.getString("special"));
//
//				movies.add(movie);
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		Session session = createSessionFactory().openSession();
		Transaction tx = null;

		try {
		   tx = session.beginTransaction();
		   
		   String hql = "FROM Movie m WHERE m.isDeleted = 0";
		   
		   if (filter != null) {
				String filterQuery = " and ";
				JsonParser parser1 = new JsonParser();
				JsonElement json1 = parser1.parse(filter);
				if (json1.isJsonArray()) {
					JsonArray jarr1 = json1.getAsJsonArray();
					int i = 1;
					for (JsonElement je1 : jarr1) {
						JsonObject jsonobj1 = je1.getAsJsonObject();
						if (i == 1) {
							filterQuery += jsonobj1.get("property").getAsString() + " LIKE '%"
									+ jsonobj1.get("value").getAsString() + "%'";
							i++;
						} else {
							filterQuery += " and " + jsonobj1.get("property").getAsString() + " LIKE '%"
									+ jsonobj1.get("value").getAsString() + "%'";
						}

					}
				}
				filterQuery = filterQuery.replaceAll("title", "m.title");
				filterQuery = filterQuery.replaceAll("director", "m.director");
				filterQuery = filterQuery.replaceAll("language", "m.language.name");
				filterQuery = filterQuery.replaceAll("releaseyear", "m.releaseyear");
				hql = hql + filterQuery;

			}
		   
		   if (sort != null) {
				String sortQuery = " ORDER BY ";
				JsonParser parser = new JsonParser();
				JsonElement json = parser.parse(sort);
				if (json.isJsonArray()) {
					JsonArray jarr = json.getAsJsonArray();
					for (JsonElement je : jarr) {
						JsonObject jsonobj = je.getAsJsonObject();
						sortQuery += jsonobj.get("property").getAsString() + " "
								+ jsonobj.get("direction").getAsString();

					}
				}
				
				sortQuery = sortQuery.replaceAll("title", "m.title");
				sortQuery = sortQuery.replaceAll("releaseyear", "m.releaseyear");
				sortQuery = sortQuery.replaceAll("language", "m.language.name");
				sortQuery = sortQuery.replaceAll("director", "m.director");
				sortQuery = sortQuery.replaceAll("rating", "m.rating");
				sortQuery = sortQuery.replaceAll("special", "m.special");
				sortQuery = sortQuery.replaceAll("desc", "m.description");
				hql = hql + sortQuery;
			}
		   
		   Query query = session.createQuery(hql);
		   query.setFirstResult(start);
		   query.setMaxResults(limit);
		   
		   movies = query.list();
		   
		   tx.commit();
		}

		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		} finally {
		   session.close();
		}
		
		return movies;

	}

	public int getMoviesCount(int start, int limit, String filter) {

		int total = 0;

		try {

			String sql4 = "SELECT COUNT(*) AS COUNT FROM film JOIN LANGUAGE ON film.language_id = language.language_id WHERE is_deleted = 0";

			if (filter != null) {
				String filterQuery = " and ";
				JsonParser parser1 = new JsonParser();
				JsonElement json1 = parser1.parse(filter);
				if (json1.isJsonArray()) {
					JsonArray jarr1 = json1.getAsJsonArray();
					int i = 1;
					for (JsonElement je1 : jarr1) {
						JsonObject jsonobj1 = je1.getAsJsonObject();
						if (i == 1) {
							filterQuery += jsonobj1.get("property").getAsString() + " LIKE '%"
									+ jsonobj1.get("value").getAsString() + "%'";
							i++;
						} else {
							filterQuery += " and " + jsonobj1.get("property").getAsString() + " LIKE '%"
									+ jsonobj1.get("value").getAsString() + "%'";
						}

					}
				}
				filterQuery = filterQuery.replaceAll("language", "language.name");
				filterQuery = filterQuery.replaceAll("releaseyear", "release_year");
				sql4 = sql4 + filterQuery;
			}

			Class.forName("com.mysql.jdbc.Driver"); // Register the JDBC Driver
			Connection conn = DriverManager.getConnection(DB_Url, username, password); // Open a connection

			// Executing a query

			Statement stm1 = conn.createStatement();

			ResultSet rs1 = stm1.executeQuery(sql4);

			if (rs1.next()) {
				total = rs1.getInt("count");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;

	}

	public List<languagePOJO> getLanguage() {
		List<languagePOJO> languages = new ArrayList();
//		try {
//			Class.forName("com.mysql.jdbc.Driver"); // Register the JDBC Driver
//			Connection conn = DriverManager.getConnection(DB_Url, username, password); // Open a connection
//
//			// Executing a query
//			Statement stm = conn.createStatement();
//			String sql;
//			sql = "SELECT NAME FROM LANGUAGE";
//			ResultSet rs = stm.executeQuery(sql);
//
//			while (rs.next()) {
//				languagePOJO language = new languagePOJO();
////				language.setId(rs.getInt("id"));
//				language.setName(rs.getString("NAME"));
//
//				languages.add(language);
//
//			}
//		}
//
//		catch (Exception e) {
//			e.printStackTrace();
//		}
		
		Session session = createSessionFactory().openSession();
		Transaction tx = null;

		try {
		   tx = session.beginTransaction();
		   
		   String hql = "FROM languagePOJO";
		   Query query = session.createQuery(hql);
		   languages = query.list();
		   
		   tx.commit();
		}

		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		} finally {
		   session.close();
		}
		
		return languages;
	}

	public List<RatingPOJO> getRating() {
		List<RatingPOJO> ratings = new ArrayList();

		try {
			Class.forName("com.mysql.jdbc.Driver"); // Register the JDBC Driver
			Connection conn = DriverManager.getConnection(DB_Url, username, password); // Open a connection

			// Executing a query
			Statement stm = conn.createStatement();
			String sql;
			sql = "SELECT DISTINCT(rating) AS rating FROM film";
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				RatingPOJO rating = new RatingPOJO();
				rating.setRating(rs.getString("rating"));

				ratings.add(rating);

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return ratings;
	}

	public ArrayList<SpecialPJOJO> getSpecial() {

		ArrayList<SpecialPJOJO> Sp = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Register the JDBC Driver
			Connection conn = DriverManager.getConnection(DB_Url, username, password); // Open a connection

			// if(id==1) {
			Statement stm3 = conn.createStatement();
			String sql3;

			sql3 = "SELECT LEFT(SUBSTRING(COLUMN_TYPE,6),LENGTH(SUBSTRING(COLUMN_TYPE,5))-3) as enumType FROM information_schema.COLUMNS WHERE TABLE_SCHEMA='sakila' AND TABLE_NAME='film' AND COLUMN_NAME='special_features';";
			ResultSet rs3 = stm3.executeQuery(sql3);

			rs3.next();

			String str = rs3.getString("enumType");
			String[] tokens = str.split("','");
			int i = 0;
			while (i < tokens.length) {
				SpecialPJOJO spe = new SpecialPJOJO();
				spe.setSpecial(tokens[i]);
				Sp.add(spe);
				i++;
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return Sp;
	}

	public boolean addMovie(Movie movie) {
//		try {
//			Class.forName("com.mysql.jdbc.Driver"); // Register the JDBC Driver
//			Connection conn = DriverManager.getConnection(DB_Url, username, password); // Open a connection
//
//			// Executing a query
//			Statement stm = conn.createStatement();
//			String sql = "INSERT INTO film (title,description,release_year,language_id,rating,director,special_features) VALUES ('"
//					+ title + "','" + desc + "'," + Integer.parseInt(releaseyear) + ","
//					+ "(SELECT language_id FROM language WHERE name LIKE '%" + language + "%'),'" + rating + "','"
//					+ director + "','" + sp + "')";
//
//			stm.executeUpdate(sql);
//		} catch (Exception e) {
//			e.printStackTrace();
//
//			return false;
//		}
		
		Session session = createSessionFactory().openSession();
		Transaction tx = null;

		try {
		   tx = session.beginTransaction();
		   
		   String hql = "FROM languagePOJO where name like '%" + movie.getLanguage().getName()+ "%'" ;
		   languagePOJO l  = (languagePOJO) session.createQuery(hql).list().get(0);
		   
		   movie.setLanguage(l);
		   session.save(movie);
		   
		   tx.commit();
		}

		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace();
		   return false;
		} finally {
		   session.close();
		}
		
		
		
		return true;
	}

	public boolean editMovie(Movie movie) {
//		try {
//			Class.forName("com.mysql.jdbc.Driver"); // Register the JDBC Driver
//			Connection conn = DriverManager.getConnection(DB_Url, username, password); // Open a connection
//			
//			System.out.println(Integer.parseInt(id));
//			// Executing a query
//			Statement stm = conn.createStatement();
//			String sql = "UPDATE film SET title = '" + title + "',DESCRIPTION = '" + desc + "',release_year = "
//					+ releaseyear + ",language_id = (SELECT language_id FROM LANGUAGE WHERE NAME LIKE '%" + language
//					+ "%'),rating = '" + rating + "',director = '" + director + "',special_features = '" + sp
//					+ "' WHERE film_id = " + Integer.parseInt(id);
////		ResultSet rs = 
//			stm.executeUpdate(sql);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("ERROR");
//			return false;
//		}
		Session session = createSessionFactory().openSession();
		Transaction tx = null;

		try {
		   tx = session.beginTransaction();
		   
		   String hql = "FROM languagePOJO where name like '%" + movie.getLanguage().getName()+ "%'" ;
		   languagePOJO l  = (languagePOJO) session.createQuery(hql).list().get(0);
		   
		   movie.setLanguage(l);
		   session.update(movie);
		   
		   tx.commit();
		}

		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace();
		   return false;
		} finally {
		   session.close();
		}
		
		return true;
	}

	public boolean deleteMovie(String items) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Register the JDBC Driver
			Connection conn = DriverManager.getConnection(DB_Url, username, password); // Open a connection

			// Executing a query
//			Statement stm = conn.createStatement();
//	        String sql = "DELETE FROM film WHERE film_id IN ("+s+")";

			Statement stm1 = conn.createStatement();
			Statement stm2 = conn.createStatement();
			Statement stm3 = conn.createStatement();
			Statement stm4 = conn.createStatement();
			Statement stm5 = conn.createStatement();
			String sql1 = "DELETE FROM `film_actor` WHERE film_id IN (" + items + ");";
			String sql2 = "DELETE FROM `film_category` WHERE film_id IN (" + items + ");";
			String sql3 = "DELETE FROM `rental` WHERE `inventory_id` IN (SELECT `inventory_id` FROM `inventory` WHERE film_id IN ("
					+ items + "));";
			String sql4 = "DELETE FROM `inventory` WHERE film_id IN (" + items + ");";
			String sql5 = "DELETE FROM film WHERE film_id IN (" + items + ");";
			stm1.executeUpdate(sql1);
			stm1.close();
			stm2.executeUpdate(sql2);
			stm2.close();
			stm3.executeUpdate(sql3);
			stm3.close();
			stm4.executeUpdate(sql4);
			stm4.close();
			stm5.executeUpdate(sql5);
			stm5.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
			return false;
		}

		return true;
	}

	public boolean softDelete(String items) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Register the JDBC Driver
			Connection conn = DriverManager.getConnection(DB_Url, username, password); // Open a connection

			// Executing a query
			Statement stm = conn.createStatement();
			String sql = "update film set is_deleted = 1 WHERE film_id IN (" + items + ")";

//			ResultSet rs = 
			stm.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
			return false;
		}
		return true;
	}
}
