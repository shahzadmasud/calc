/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.net.calc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

/**
 *
 * @author shahzadmasud
 */
public class Base {

    public HttpStatus status = HttpStatus.ACCEPTED;
    public String message = "OK";
    @JsonIgnore
    public Throwable cause = null;

}
