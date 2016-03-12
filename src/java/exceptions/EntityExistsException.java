/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author I320736
 */
public class EntityExistsException extends Exception {
    
    public EntityExistsException(String msg) {
        super(msg);
    }
}
