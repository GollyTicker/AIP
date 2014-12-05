package Persistenz;

import java.sql.ResultSet;

import Utilities.AlreadyExistsException;
import Utilities.NotFoundException;
import Utilities.TechnicalException;

public interface IPersistenzService {
    int create(String type, String typeRep) throws TechnicalException;
    String read(String type, int id) throws NotFoundException, TechnicalException;
    void update(String type, int id, String typeRep) throws NotFoundException, TechnicalException;
    void delete(String type, int id) throws TechnicalException;
}
