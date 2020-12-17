/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dao;

import java.util.List;

/**
 *
 * @author alanc
 */
public interface Dao<T> {
    public T Create(T DTO);
    public List<T> ReadAll();
    public T ReadById(int id);
    public void Update(T DTO);
    public void Delete(int id);
    
}
