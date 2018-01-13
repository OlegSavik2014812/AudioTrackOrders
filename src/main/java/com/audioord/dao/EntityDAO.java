package com.audioord.dao;

import com.audioord.model.Entity;

import java.io.Serializable;

public interface EntityDAO<E extends Entity<K>, K extends Serializable> {

  E getById(K id) throws DAOException;

  E update(E entity);

  boolean delete(K id);

  boolean create(E entity);
}
