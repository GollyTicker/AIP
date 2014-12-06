package Persistenz;

import Utilities.NotFoundException;
import Utilities.TechnicalException;

import java.util.HashMap;
import java.util.Map;

import static Utilities.TechnicalException.throwNewTechnicalException;

public class DatabaseConnection implements IPersistenzService {

    public final static String ANGEBOT = "angebot";
    public final static String AUFTRAG = "auftrag";
    public final static String BAUTEIL = "bauteil";
    public final static String FERTIGUNGSAUFTRAG = "fertigungsauftrag";
    public final static String FERTIGUNGSPLAN = "fertigungsplan";

    Map<String, Map<Integer, String>> db;
    Map<String, Integer> maxKey = new HashMap();

	public DatabaseConnection() throws TechnicalException {
        db = new HashMap();
        db.putIfAbsent(ANGEBOT, new HashMap());
        maxKey.put(ANGEBOT, 0);
        db.putIfAbsent(AUFTRAG, new HashMap());
        maxKey.put(AUFTRAG, 0);
        db.putIfAbsent(BAUTEIL, new HashMap());
        maxKey.put(BAUTEIL, 0);
        db.putIfAbsent(FERTIGUNGSAUFTRAG, new HashMap());
        maxKey.put(FERTIGUNGSAUFTRAG, 0);
        db.putIfAbsent(FERTIGUNGSPLAN, new HashMap());
        maxKey.put(FERTIGUNGSPLAN, 0);
    }

    @Override
    synchronized public int create(String type, String typeRep) throws TechnicalException {
        Integer id = maxKey.get(type);
        db.get(type).put(id, typeRep);
        maxKey.put(type, maxKey.get(type)+1);
        return id.intValue();
    }

    @Override
    synchronized public String read(String type, int id) throws NotFoundException, TechnicalException {
        Integer i = Integer.valueOf(id);
        if (!db.get(type).containsKey(i)) {
            NotFoundException.throwNotFoundException();
        }
        return db.get(type).get(i);
    }

    @Override
    synchronized public void update(String type, int id, String typeRep) throws NotFoundException, TechnicalException {
        read(type, id);
        db.get(type).put(Integer.valueOf(id), typeRep);
    }

    @Override
    synchronized public void delete(String type, int id) throws TechnicalException {
        db.get(type).remove(Integer.valueOf(id));
    }
}