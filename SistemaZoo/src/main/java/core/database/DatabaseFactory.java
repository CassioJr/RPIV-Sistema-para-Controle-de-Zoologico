package core.database;

import core.dao.AnimalDao;

public class DatabaseFactory {
private static DatabaseFactory instance;
	
	public static DatabaseFactory getInstance(){
		if(instance == null){
			instance = new DatabaseFactory();
		}
		return instance;
	}
	
    public  Database getDatabase(String nome){
        if(nome.equals("postgresql")){
            return new DatabasePostgreSQL();
        }else if(nome.equals("mysql")){
            return new DatabaseMySQL();
        }
        return null;
    }
    private DatabaseFactory() {
    	
    }
}
